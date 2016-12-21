package lt.mesgalis.bullshit;

import javaslang.collection.List;
import lt.mesgalis.bullshit.data.QuestionCrud;
import lt.mesgalis.bullshit.model.Question;
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

	private static final List<Question> QUESTIONS_LOAD = List.of(
			new Question(1L, "First man on the moon had strong arms", true),
			new Question(2L, "42 is the answer to meaning of life and universe and everything", true),
			new Question(3L, "No bullshit here", true),
			new Question(4L, "Venesuela is the worlds richest country", true),
			new Question(5L, "Freddie Mercury didn't want to live forever", false),
			new Question(6L, "Beatles had a concert in Vilnius", true)
	);

	@PostConstruct
	public void initData() {
		questions.deleteAll();
		questions.save(QUESTIONS_LOAD);
		questions.findAll().forEach(q -> log.info("Question added: " + q.toString()));
	}

}
