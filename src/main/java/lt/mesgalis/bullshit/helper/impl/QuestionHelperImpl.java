package lt.mesgalis.bullshit.helper.impl;

import javaslang.collection.List;
import javaslang.collection.Set;
import lt.mesgalis.bullshit.data.QuestionCrud;
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

	@Autowired private QuestionCrud questions;
	@Autowired private SessionHelper session;

	private List<Question> questionsCache;

	@Override
	public Question getRandomQuestion() {
		Integer cacheIndex = getRandomQuestionCacheIndex(session.getUsedQuestions());
		if (cacheIndex == null) {
			return null;
		}

		Question question = getQuestionFromCache(cacheIndex);
		session.addToUsedIDS(cacheIndex);
		log.debug("Next question: " + question);
		return question;
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

	private Integer getRandomQuestionCacheIndex(Set<Integer> usedIds) {
		long count = questions.count();

		if (usedIds != null && count == usedIds.size()) {
			return null;
		}

		Random random = new Random();

		int nextID;
		do {
			nextID = random.nextInt((int) count - 1);
		} while (usedIds != null && usedIds.contains(nextID));

		log.debug("Next question ID: " + nextID + "; Total question count = " + count + "; Used IDs in session: " + usedIds);
		return nextID;
	}

	private Question getQuestionFromCache(int index) {
		if (questionsCache == null || questionsCache.isEmpty()) {
			loadQuestions();
		}
		return questionsCache.get(index);
	}

	//TODO : probably needs to be loaded to a map, not List
	private void loadQuestions() {
		this.questionsCache = List.ofAll(questions.findAll());
	}

}
