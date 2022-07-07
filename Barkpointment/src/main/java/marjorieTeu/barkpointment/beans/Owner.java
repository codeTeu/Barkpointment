package marjorieTeu.barkpointment.beans;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Data
@AllArgsConstructor
public class Owner {
	private int acctID;
	private String fname;
	private String lname;
	private int phone;
	private String email;
	private String address;
	private String city;
	private String province;

	public Owner() {

	}
}
