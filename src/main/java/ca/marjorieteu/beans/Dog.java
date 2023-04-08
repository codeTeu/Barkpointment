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
	private int dogId;
	private String name;
	private String gender;
	private String bday;
	private String breed;
	private int ownerId;

	public Dog(int dogId, int ownerId) {
		this.dogId = dogId;
		this.ownerId = ownerId;
	}

}
