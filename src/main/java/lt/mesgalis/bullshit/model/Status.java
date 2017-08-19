package lt.mesgalis.bullshit.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

public class Status {

	public enum StatusKey {
		TEST_DATA_LOADED
	}

	private StatusKey key;
	private String value;

	public Status(StatusKey key, String value) {
		this.key = key;
		this.value = value;
	}

	@Id
	@Column(unique = true)
	@Enumerated(EnumType.STRING)
	public StatusKey getKey() {
		return key;
	}

	public void setKey(StatusKey key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
