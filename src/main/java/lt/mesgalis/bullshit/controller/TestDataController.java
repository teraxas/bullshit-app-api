package lt.mesgalis.bullshit.controller;

import javaslang.collection.List;
import lt.mesgalis.bullshit.data.QuestionCrud;
import lt.mesgalis.bullshit.data.StatusCrud;
import lt.mesgalis.bullshit.data.UserCrud;
import lt.mesgalis.bullshit.model.Question;
import lt.mesgalis.bullshit.model.Status;
import lt.mesgalis.bullshit.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/test")
public class TestDataController {

	private static final Logger log = LoggerFactory.getLogger(TestDataController.class);

	private QuestionCrud questions;
	private UserCrud users;
	private StatusCrud status;

	private static final User USER;

	private static final List<Question> QUESTIONS_LOAD;

	static {
		USER = new User();
		USER.setName("System");
		USER.setCountry("Lithuania");

		QUESTIONS_LOAD = List.of(
				new Question("42 is the answer to meaning of life and universe and everything", "It's actually a result of a failure in computer code.", true),
				new Question("Star Trek is a movie about rebel group, fighting evil Empire", "That's star wars, dude.", true),
				new Question("No bullshit here", "It's bullshit!", true),
				new Question("Venesuela is the worlds richest country", "Actually it's kinda opposite", true),
				new Question("Earth is flat", "If you are a 'flat-earther', it's hopeless to explain this to you.", true),
				new Question("Beatles had a concert in Vilnius", "They never went there", true),
				new Question("Sun revolves around Earth", "It doesn't. It just doesn't.", true),
				new Question("Star Wars is a movie about rebel group, fighting evil Empire", "That's star wars, dude.", false),
				new Question("Earth revolves around Sun", "It does. That's astrophysics.", false),
				new Question("Earth revolves around Sun", "It does. That's astrophysics.", false),
				new Question("Fanta soft drink was created in Nazi", "During WW2, Coca-Cola operations in Germany and ocupied countries couldn't get needed supplies, so a new drink was invented.", false)
		);
	}

	public TestDataController(QuestionCrud questions, UserCrud users, StatusCrud status) {
		this.questions = questions;
		this.users = users;
		this.status = status;
	}

	@Transactional
	@RequestMapping(value = "/loadData", method = RequestMethod.GET)
	public void loadData() {
		Optional<Status> loaded = status.findById(Status.StatusKey.TEST_DATA_LOADED);

		if (loaded.isPresent()) {
			throw new TestLoadException("Test data is already loaded");
		}

		log.info("SAVING NEW QUESTIONS");
		User user = users.save(USER);
		QUESTIONS_LOAD.forEach(q -> q.setCreator(user));
		questions.saveAll(QUESTIONS_LOAD);
		questions.findAll().forEach(q -> log.info("Question added: " + q.toString()));
		status.save(new Status(Status.StatusKey.TEST_DATA_LOADED, "loaded"));
	}

	private class TestLoadException extends RuntimeException {
		public TestLoadException(String s) {
			super(s);
		}
	}
}
