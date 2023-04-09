package ca.marjorieteu.beans;

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
	private String birthday;
	private String breed;
	private int ownerID;


	public Dog(String name, String gender, String birthday, String breed, int ownerId) {
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.breed = breed;
		this.ownerID = ownerId;
	}

}
