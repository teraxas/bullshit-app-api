package lt.mesgalis.bullshit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lt.mesgalis.bullshit.model.Question;
import lt.mesgalis.bullshit.helper.QuestionHelper;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/question")
public class QuestionsController {

	@Autowired
	private QuestionHelper questions;

	@RequestMapping("/get")
	public Question getQuestion() {
		return questions.getRandomQuestion();
	}

	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public boolean answerQuestion(@RequestBody Answer request) {
		return questions.checkAnswer(request.id, request.answer);
	}

	private static class Answer {
		public Long id;
		public Boolean answer;
	}

}
