package lt.mesgalis.bullshit.helper;

import lt.mesgalis.bullshit.model.Question;

public interface QuestionHelper {
	Question getRandomQuestion();
	Question getRandomQuestionForUser(long userId);
	boolean checkAnswer(long id, boolean answer);

	void clearQuestionsCache();

	void addQuestionIfAllowed(Question question);
}
