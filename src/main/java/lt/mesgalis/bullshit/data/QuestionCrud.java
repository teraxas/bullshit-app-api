package lt.mesgalis.bullshit.data;

import lt.mesgalis.bullshit.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionCrud extends CrudRepository<Question, Long> {

}
