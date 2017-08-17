package lt.mesgalis.bullshit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BS_QUESTION")
public class Question {

	private Long id;

	private String question;
	private String explanation;

	private User creator;

	private boolean bullshit;

	public Question() {}

	public Question(String question, String explanation, boolean bullshit) {
		this.setQuestion(question);
		this.setExplanation(explanation);
		this.setBullshit(bullshit);
	}

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
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

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(nullable = false, updatable = false)
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
