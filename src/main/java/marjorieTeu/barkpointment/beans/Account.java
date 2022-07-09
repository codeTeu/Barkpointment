package marjorieTeu.barkpointment.beans;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Data
@AllArgsConstructor
public class Account {
	private int acctID;
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String province;
	private String password;
	private String isAdmin;

	public Account() {

	}
}
