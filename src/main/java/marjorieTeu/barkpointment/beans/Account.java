package marjorieTeu.barkpointment.beans;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {		    
	private int acctID;
	private String username;
	private String password;
	private String authority="ROLE_USER";
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String province;

}
