package lt.mesgalis.bullshit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.mesgalis.bullshit.data.QuestionDTO;
import lt.mesgalis.bullshit.model.Question;

@RestController
@RequestMapping("/question")
public class QuestionsController {

	@Autowired private QuestionDTO questions;

	@RequestMapping("/get")
	public Question getQuestion() {
		return questions.getQuestion();
	}

	@RequestMapping("/answer")
	public boolean answerQuestion(Long id, Boolean answer) {
		return questions.checkAnswer(id, answer);
	}

}
