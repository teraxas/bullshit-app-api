package lt.mesgalis.bullshit.helper.impl;

import javaslang.collection.Set;
import javaslang.collection.TreeSet;
import javaslang.control.Option;
import lt.mesgalis.bullshit.data.QuestionCrud;
import lt.mesgalis.bullshit.helper.QuestionHelper;
import lt.mesgalis.bullshit.helper.SessionHelper;
import lt.mesgalis.bullshit.model.Question;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Component
public class QuestionHelperImpl implements QuestionHelper {

	private static final Logger log = LogManager.getLogger(QuestionHelperImpl.class.getName());

	@Autowired private QuestionCrud questions;
	@Autowired private SessionHelper session;

	@Override
	public Question getRandomQuestion() {
		Long id = getRandomQuestionID(session.getUsedQuestions());
		if (id == null) {
			return null;
		}

		Question question = questions.findOne(id);
		session.addToUsedIDS(id.intValue());
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

	private Long getRandomQuestionID(Set<Integer> usedIds) {
		long count = questions.count();

		if (usedIds != null && count == usedIds.size()) {
			return null;
		}

		Random random = new Random();

		int nextID;
		do {
			nextID = random.nextInt((int) count--);
			nextID++;
		} while (usedIds != null && usedIds.contains(nextID));

		log.debug("Next question ID: " + nextID);
		return Long.valueOf(nextID);
	}

}
