package by.grsu.dbobovik.phonestat.web.dto;

import java.sql.Timestamp;

public class UserDto {
    @Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", role="
				+ role + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Timestamp getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getRole() {
		return role;
	}

	public void setRole(Boolean role) {
		this.role = role;
	}

	private Integer id;
	private String name;
	private String surname;
	private Timestamp birthDate;
	private Boolean role;

}
