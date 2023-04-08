package ca.marjorieteu.beans;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
	private int ownerId;
	private String fname;
	private String lname;
	private String address;
	private String city;
	private String province;
	private String postalCode;
	private String email;
	private String phone ;
}
