package lt.mesgalis.bullshit.data.impl;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import lt.mesgalis.bullshit.data.QuestionDTO;
import lt.mesgalis.bullshit.model.Question;

@Component
public class QuestionDTOMockImpl implements QuestionDTO {
	
	private static final List<Question> questions = Lists.newArrayList(
			new Question(0, "First man on the moon had strong arms", true),
			new Question(1, "42 is the answer to meaning of life and universe and everything", true),
			new Question(2, "No bullshit here", true),
			new Question(3, "Venesuela is the worlds richest country", true),
			new Question(4, "Freddie Mercury didn't want to live forever", false),
			new Question(5, "Beatles had a concert in Vilnius", true)
			);
	
	@Override
	public Question getQuestion() {
		Random random = new Random();
		int nextInt = random.nextInt(questions.size()-1);
		return questions.get(nextInt);
	}

	@Override
	public Question getQuestionForUser(long userId) {
		return getQuestion();
	}

	@Override
	public boolean checkAnswer(long id, boolean answer) {
		return questions.get(Math.toIntExact(id)).isBullshit() == answer;
	}

}
