package lt.mesgalis.bullshit.helper.impl;

import java.util.Random;

import org.springframework.stereotype.Component;

import javaslang.collection.List;
import lt.mesgalis.bullshit.model.Question;
import lt.mesgalis.bullshit.helper.QuestionHelper;

//@Component
@Deprecated
public class QuestionHelperMockImpl implements QuestionHelper {
	
	private List<Question> questions = List.of(
			new Question(0L, "First man on the moon had strong arms", true),
			new Question(1L, "42 is the answer to meaning of life and universe and everything", true),
			new Question(2L, "No bullshit here", true),
			new Question(3L, "Venesuela is the worlds richest country", true),
			new Question(4L, "Freddie Mercury didn't want to live forever", false),
			new Question(5L, "Beatles had a concert in Vilnius", true)
			);
	
	@Override
	public Question getRandomQuestion() {
		Random random = new Random();
		int nextInt = random.nextInt(questions.size()-1);
		return questions.get(nextInt);
	}

	@Override
	public Question getRandomQuestionForUser(long userId) {
		return getRandomQuestion();
	}

	@Override
	public boolean checkAnswer(long id, boolean answer) {
		return questions.get(Math.toIntExact(id)).isBullshit() == answer;
	}

}
