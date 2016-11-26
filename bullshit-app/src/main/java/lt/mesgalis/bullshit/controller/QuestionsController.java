package lt.mesgalis.bullshit.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.mesgalis.bullshit.data.QuestionDTO;
import lt.mesgalis.bullshit.model.Question;

@RestController
@RequestMapping("/question")
public class QuestionsController {

	@Autowired private QuestionDTO questions;
	
    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping("/get")
    public Question getQuestion() {
        return questions.getQuestion();
    }
    
    @RequestMapping("/answer")
    public boolean answerQuestion(long id, boolean answer) {
    	return questions.checkAnswer(id, answer);
    }

}
