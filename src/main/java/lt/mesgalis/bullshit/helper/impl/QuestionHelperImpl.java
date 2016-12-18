package lt.mesgalis.bullshit.helper.impl;

import javaslang.collection.List;
import lt.mesgalis.bullshit.data.QuestionCrud;
import lt.mesgalis.bullshit.helper.QuestionHelper;
import lt.mesgalis.bullshit.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.Set;
import java.util.stream.LongStream;

@Component
public class QuestionHelperImpl implements QuestionHelper {

	@Autowired private HttpSession session;
	@Autowired private QuestionCrud questions;

	private static final List<Question> MOCK_QUESTIONS = List.of(
			new Question(null, "First man on the moon had strong arms", true),
			new Question(null, "42 is the answer to meaning of life and universe and everything", true),
			new Question(null, "No bullshit here", true),
			new Question(null, "Venesuela is the worlds richest country", true),
			new Question(null, "Freddie Mercury didn't want to live forever", false),
			new Question(null, "Beatles had a concert in Vilnius", true)
	);

	@Override
	public Question getRandomQuestion() {
		questions.save(MOCK_QUESTIONS);
		return questions.findOne(getRandomQuestionID(null));
	}

	@Override
	public Question getRandomQuestionForUser(long userId) {
		return null;
	}

	@Override
	public boolean checkAnswer(long id, boolean answer) {
		return false;
	}

	private long getRandomQuestionID(Set<Integer> usedIds) {
		long count = questions.count();

		Random random = new Random();

		int nextID;
		do {
			nextID = random.nextInt((int) count);
		} while (usedIds != null && usedIds.contains(nextID));

		return nextID;
	}

}
