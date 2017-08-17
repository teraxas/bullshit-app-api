package lt.mesgalis.bullshit.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BS_USER")
public class User {

	private Long id;

	private String name;
	private String country;

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
