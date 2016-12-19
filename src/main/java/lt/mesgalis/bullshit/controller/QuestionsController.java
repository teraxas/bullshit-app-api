package lt.mesgalis.bullshit.controller;

import lt.mesgalis.bullshit.helper.QuestionHelper;
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

	@Autowired
	private QuestionHelper questions;

	@RequestMapping("/get")
	public Question getQuestion() {
		log.debug("Request /question/get");
		return questions.getRandomQuestion();
	}

	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public boolean answerQuestion(@RequestBody Answer request) {
		log.debug("Request /question/answer");
		return questions.checkAnswer(request.id, request.answer);
	}

	private static class Answer {
		public Long id;
		public Boolean answer;
	}

}
