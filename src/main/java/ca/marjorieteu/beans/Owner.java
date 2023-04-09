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
	private int ownerID;
	private String fname;
	private String lname;
	private String address;
	private String city;
	private String province;
	private String postalCode;
	private String email;
	private String phone;

	public Owner(String fname, String lname, String address, String city, String province, String postalCode,
			String email, String phone) {

		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.email = email;
		this.phone = phone;
	}

}
