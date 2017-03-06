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

	private QuestionHelper questions;
	private SessionHelper session;

	@Autowired
	public QuestionsController(QuestionHelper questions, SessionHelper session) {
		this.questions = questions;
		this.session = session;
	}

	@RequestMapping("/get")
	public Question getQuestion() {
		log.info("Get question request");
		return questions.getRandomQuestion();
	}

	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public ResultsResponse answerQuestion(@RequestBody Answer request) {
		log.info("Answer question request: " + request.toString());
		boolean result = questions.checkAnswer(request.id, request.answer);
		return buildResultsResponse(result);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createQuestion(@RequestBody Question question) {
		log.info("Create question request: " + question.toString());
		questions.addQuestionIfAllowed(question);
	}

	private ResultsResponse buildResultsResponse(boolean result) {
		return new ResultsResponse(result, session.getTotalTries(), session.getSuccessTries());
	}

	private static class Answer {
		public Long id;
		public Boolean answer;

		@Override
		public String toString() {
			return String.format("Answer{id=%d, answer=%s}", id, answer);
		}
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
