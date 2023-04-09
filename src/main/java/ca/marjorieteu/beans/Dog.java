package ca.marjorieteu.beans;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
	private int dogID;
	private String name;
	private String gender;
	private Date birthday;
	private String breed;
	private int ownerID;

	public Dog(int dogId, int ownerId) {
		this.dogID = dogId;
		this.ownerID = ownerId;
	}

	public Dog(String name, String gender, Date birthday, String breed, int ownerId) {
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.breed = breed;
		this.ownerID = ownerId;
	}

}
