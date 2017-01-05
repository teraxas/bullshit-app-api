package lt.mesgalis.bullshit.helper.impl;

import static javaslang.API.*;
import javaslang.collection.List;
import javaslang.collection.Set;
import javaslang.collection.TreeSet;
import javaslang.control.Option;
import lt.mesgalis.bullshit.data.QuestionCrud;
import lt.mesgalis.bullshit.exception.UnworthyException;
import lt.mesgalis.bullshit.helper.QuestionHelper;
import lt.mesgalis.bullshit.helper.SessionHelper;
import lt.mesgalis.bullshit.model.Question;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class QuestionHelperImpl implements QuestionHelper {

	private static final Logger log = LogManager.getLogger(QuestionHelperImpl.class.getName());

	private QuestionCrud questions;
	private SessionHelper session;

	private List<Question> questionsCache;

	@Autowired
	public QuestionHelperImpl(QuestionCrud questions, SessionHelper session) {
		this.questions = questions;
		this.session = session;
	}

	@Override
	public Question getRandomQuestion() {
		return Option.of(getRandomQuestionFromCache(session.getUsedQuestions()))
				.peek(q -> session.addToUsedIDS(q.getId()))
				.getOrElse((Question) null);
	}

	@Override
	public Question getRandomQuestionForUser(long userId) {
		return null;
	}

	@Override
	public boolean checkAnswer(long id, boolean answer) {
		Question question = questions.findOne(id);
		return question.isBullshit() == answer;
	}

	@Override
	public void clearQuestionsCache() {
		this.questionsCache = null;
	}

	@Override
	public void addQuestionIfAllowed(Question question) {
		if (session.isWorthyToAddQuestion(questionsCache.size())) {
			questions.save(question);
		} else {
			throw new UnworthyException("User isn't worthy to add questions");
		}

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
