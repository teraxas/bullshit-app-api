package lt.mesgalis.bullshit.data;

import lt.mesgalis.bullshit.model.Question;

public interface QuestionDTO {
	Question getQuestion();
	Question getQuestionForUser(long userId);
	boolean checkAnswer(long id, boolean answer);
}
