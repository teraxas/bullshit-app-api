package lt.mesgalis.bullshit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "QUESTION")
public class Question {

	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String question;
	private String explanation;

	@JsonIgnore
	private boolean bullshit;

	public Question() {}

	public Question(Long id, String question, String explanation, boolean bullshit) {
		this.id = id;
		this.question = question;
		this.explanation = explanation;
		this.bullshit = bullshit;
	}

	public Long getId() {
		return id;
	}
	public boolean isBullshit() {
		return bullshit;
	}
	public String getQuestion() {
		return question;
	}
	public String getExplanation() {
		return explanation;
	}

	@Override
	public String toString() {
		return String.format("Question{id=%d, question='%s', bullshit=%s}", id, question, bullshit);
	}
}
