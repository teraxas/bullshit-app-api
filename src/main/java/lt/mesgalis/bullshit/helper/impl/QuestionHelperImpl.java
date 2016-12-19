package lt.mesgalis.bullshit.helper.impl;

import javaslang.collection.List;
import lt.mesgalis.bullshit.data.QuestionCrud;
import lt.mesgalis.bullshit.helper.QuestionHelper;
import lt.mesgalis.bullshit.model.Question;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.Set;
import java.util.stream.LongStream;

@Component
public class QuestionHelperImpl implements QuestionHelper {

	private static final Logger log = LogManager.getLogger(QuestionHelperImpl.class);

	@Autowired private HttpSession session;
	@Autowired private QuestionCrud questions;

	@Override
	public Question getRandomQuestion() {
		long id = getRandomQuestionID(null);
		Question question = questions.findOne(id);
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

	private long getRandomQuestionID(Set<Integer> usedIds) {
		long count = questions.count();

		Random random = new Random();

		int nextID;
		do {
			nextID = random.nextInt((int) count);
		} while (usedIds != null && usedIds.contains(nextID));

		log.debug("Next question ID: " + nextID);
		return nextID;
	}

}
