package lt.mesgalis.bullshit;

import javaslang.collection.List;
import lt.mesgalis.bullshit.data.QuestionCrud;
import lt.mesgalis.bullshit.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class TestDataLoader {

	@Autowired private QuestionCrud questions;

	private static final List<Question> QUESTIONS_LOAD = List.of(
			new Question(null, "First man on the moon had strong arms", true),
			new Question(null, "42 is the answer to meaning of life and universe and everything", true),
			new Question(null, "No bullshit here", true),
			new Question(null, "Venesuela is the worlds richest country", true),
			new Question(null, "Freddie Mercury didn't want to live forever", false),
			new Question(null, "Beatles had a concert in Vilnius", true)
	);

	@PostConstruct
	public void initData() {
		questions.save(QUESTIONS_LOAD);
	}

}
