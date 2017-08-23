package lt.mesgalis.bullshit.helper.impl;

import javaslang.collection.List;
import javaslang.collection.Set;
import javaslang.collection.TreeSet;
import javaslang.control.Option;
import lt.mesgalis.bullshit.data.QuestionCrud;
import lt.mesgalis.bullshit.data.UserCrud;
import lt.mesgalis.bullshit.exception.UnworthyException;
import lt.mesgalis.bullshit.helper.QuestionHelper;
import lt.mesgalis.bullshit.helper.SessionHelper;
import lt.mesgalis.bullshit.model.Question;
import lt.mesgalis.bullshit.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Component
public class QuestionHelperImpl implements QuestionHelper {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private QuestionCrud questions;
	private UserCrud users;
	private SessionHelper session;

	private List<Question> questionsCache;

	@Autowired
	public QuestionHelperImpl(QuestionCrud questions, UserCrud users, SessionHelper session) {
		this.questions = questions;
		this.users = users;
		this.session = session;
	}

	@Override
	public Question getRandomOrCurrentQuestion() {
		Option<Long> currentQuestionId = Option.of(session.getCurrentQuestion());
		if (currentQuestionId.isDefined()) {
			return Option.of(questions.findOne(currentQuestionId.get())).getOrElse(getRandomQuestion());
		} else {
			return getRandomQuestion();
		}
	}

	private Question getRandomQuestion() {
		return Option.of(getRandomQuestionFromCache(session.getUsedQuestions()))
				.peek(q -> {
					session.addToUsedIDS(q.getId());
					session.setCurrentQuestion(q.getId());
				})
				.getOrElse((Question) null);
	}

	@Override
	public Question getRandomQuestionForUser(long userId) {
		return null;
	}

	@Override
	public boolean checkAnswerAndMarkSuccess(long id, boolean answer) {
		Question question = questions.findOne(id);
		boolean success = question.isBullshit() == answer;
		session.addQuestionTry(success);
		session.clearCurrentQuestion();
		return success;
	}

	@Override
	public void clearQuestionsCache() {
		this.questionsCache = null;
	}

	@Override
	@Transactional
	public void addQuestionIfAllowed(Question question) {
		if (isWorthyToAddQuestion()) {
			User savedUser = users.save(question.getCreator()); // TODO : in future, require registration
			question.setCreator(savedUser);
			questions.save(question);
			clearQuestionsCache();
		} else {
			throw new UnworthyException("User isn't worthy to add questions");
		}
	}

	public boolean isWorthyToAddQuestion() {
		return session.isWorthyToAddQuestion(getQuestionCache().size());
	}

	private Question getRandomQuestionFromCache(Set<Long> usedIds) {
		List<Question> unusedQuestions = getQuestionCache().filter(
				q -> !Option.of(usedIds).getOrElse(TreeSet.empty())
						.contains(q.getId()));

		switch (unusedQuestions.length()) {
			case 0:
				return null;
			case 1:
				return unusedQuestions.get();
			default:
				int nextID = new Random().nextInt(unusedQuestions.length() - 1);
				return unusedQuestions.get(nextID);
		}
	}

	private Question getQuestionFromCache(int index) {
		return getQuestionCache().get(index);
	}

	private List<Question> getQuestionCache() {
		if (questionsCache == null || questionsCache.isEmpty()) {
			this.questionsCache = List.ofAll(questions.findAll());
		}
		return questionsCache;
	}

}
