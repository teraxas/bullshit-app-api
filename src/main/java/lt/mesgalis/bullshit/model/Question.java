package lt.mesgalis.bullshit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION")
public class Question {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String question;
	@JsonIgnore private boolean bullshit;

	public Question(Long id, String question, boolean bullshit) {
		this.id = id;
		this.question = question;
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
}
