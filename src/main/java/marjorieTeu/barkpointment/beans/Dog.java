package marjorieTeu.barkpointment.beans;

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
	private char gender;
	private String breed;
	private Date birthday;
	private int ownerID;

	public Dog(String name, char gender, String breed, Date birthday, int ownerID) {
		this.name=name;
		this.gender=gender;
		this.breed=breed;
		this.birthday=birthday;
		this.ownerID=ownerID;
	}
}
