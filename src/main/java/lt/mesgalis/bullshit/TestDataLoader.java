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
			new Question(1L, "First man on the moon had strong arms", null, true, null),
			new Question(2L, "42 is the answer to meaning of life and universe and everything", null, true, null),
			new Question(3L, "No bullshit here", null, true, null),
			new Question(4L, "Venesuela is the worlds richest country", null, true, null),
			new Question(5L, "Freddie Mercury didn't want to live forever", null, false, null),
			new Question(6L, "Beatles had a concert in Vilnius", null, true, null)
	);

	@PostConstruct
	public void initData() {
		log.info("DELETING ALL QUESTIONS");
		questions.deleteAll();
		log.info("SAVING NEW QUESTIONS");
		questions.save(QUESTIONS_LOAD);
		questions.findAll().forEach(q -> log.info("Question added: " + q.toString()));
	}

}
