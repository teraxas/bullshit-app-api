package lt.mesgalis.bullshit;

import javaslang.collection.List;
import lt.mesgalis.bullshit.data.QuestionCrud;
import lt.mesgalis.bullshit.data.UserCrud;
import lt.mesgalis.bullshit.model.Question;
import lt.mesgalis.bullshit.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class TestDataLoader {

	private static final Logger log = LogManager.getLogger(TestDataLoader.class);

	@Autowired private QuestionCrud questions;
	@Autowired private UserCrud users;

	private static final User USER;

	private static final List<Question> QUESTIONS_LOAD;

	static {
		USER = new User();
		USER.setName("System");
		USER.setCountry("Lithuania");

		QUESTIONS_LOAD = List.of(
				new Question(1L, "42 is the answer to meaning of life and universe and everything", "It's actually a result of a failure in computer code.", true, USER),
				new Question(2L, "Star Trek is a movie about rebel group, fighting evil Empire", "That's star wars, dude.", true, USER),
				new Question(3L, "No bullshit here", "It's bullshit!", true, USER),
				new Question(4L, "Venesuela is the worlds richest country", "Actually it's kinda opposite", true, USER),
				new Question(5L, "Earth is flat", "If you are a 'flat-earther', it's hopeless to explain this to you.", true, USER),
				new Question(6L, "Beatles had a concert in Vilnius", "They never went there", true, USER),
				new Question(7L, "Sun revolves around Earth", "It doesn't. It just doesn't.", true, USER),
				new Question(8L, "Star Wars is a movie about rebel group, fighting evil Empire", "That's star wars, dude.", false, USER),
				new Question(9L, "Earth revolves around Sun", "It does. That's astrophysics.", false, USER),
				new Question(10L, "Earth revolves around Sun", "It does. That's astrophysics.", false, USER),
				new Question(11L, "Fanta soft drink was created in Nazi", "During WW2, Coca-Cola operations in Germany and ocupied countries couldn't get needed supplies, so a new drink was invented.", false, USER)
		);
	}

	@PostConstruct
	public void initData() {
		log.info("DELETING ALL QUESTIONS");
		questions.deleteAll();
		log.info("SAVING NEW QUESTIONS");
		User user = users.save(USER);
		QUESTIONS_LOAD.forEach(q -> q.setCreator(user));
		questions.save(QUESTIONS_LOAD);
		questions.findAll().forEach(q -> log.info("Question added: " + q.toString()));
	}

}
