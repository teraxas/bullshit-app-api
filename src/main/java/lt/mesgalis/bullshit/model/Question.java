package lt.mesgalis.bullshit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "QUESTION")
public class Question {

	private Long id;

	private String question;
	private String explanation;

	private User creator;

	private boolean bullshit;

	public Question() {}

	public Question(Long id, String question, String explanation, boolean bullshit, User creator) {
		this.id = id;
		this.question = question;
		this.explanation = explanation;
		this.bullshit = bullshit;
		this.creator = creator;
	}

	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@NotNull
	public String getQuestion() {
		return question;
	}

	public String getExplanation() {
		return explanation;
	}

	@ManyToOne
	public User getCreator() {
		return creator;
	}

	@JsonIgnore
	public boolean isBullshit() {
		return bullshit;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@JsonIgnore(false)
	public void setBullshit(boolean bullshit) {
		this.bullshit = bullshit;
	}

	@Override
	public String toString() {
		return String.format("Question{id=%d, question='%s', bullshit=%s}", id, question, bullshit);
	}
}
