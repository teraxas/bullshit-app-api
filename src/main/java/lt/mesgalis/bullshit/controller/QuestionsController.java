package lt.mesgalis.bullshit.controller;

import lt.mesgalis.bullshit.helper.QuestionHelper;
import lt.mesgalis.bullshit.helper.SessionHelper;
import lt.mesgalis.bullshit.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionsController {

	private static final Logger log = LoggerFactory.getLogger(QuestionsController.class);

	@Autowired private QuestionHelper questions;
	@Autowired private SessionHelper session;

	@RequestMapping("/get")
	public Question getQuestion() {
		return questions.getRandomQuestion();
	}

	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public ResultsResponse answerQuestion(@RequestBody Answer request) {
		boolean result = questions.checkAnswer(request.id, request.answer);
		return buildResultsResponse(result);
	}

	private ResultsResponse buildResultsResponse(boolean result) {
		return new ResultsResponse(result, session.getTotalTries(), session.getSuccessTries());
	}

	private static class Answer {
		public Long id;
		public Boolean answer;
	}

	private static class ResultsResponse {
		public boolean lastResult = false;
		public int totalAnswers = 0;
		public int successfulAnswers = 0;

		public ResultsResponse(boolean lastResult, int totalAnswers, int successfulAnswers) {
			this.lastResult = lastResult;
			this.totalAnswers = totalAnswers;
			this.successfulAnswers = successfulAnswers;
		}
	}

}
