package lt.mesgalis.bullshit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Question {
	public final long id;
	public final String question;

	@JsonIgnore
	private boolean bullshit;
	
	public Question(long id, String question, boolean bullshit) {
		this.id = id;
		this.question = question;
		this.bullshit = bullshit;
	}
	
	public boolean isBullshit() {
		return bullshit;
	}
}
