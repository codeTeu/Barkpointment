package marjorieTeu.barkpointment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import marjorieTeu.barkpointment.beans.Account;
import marjorieTeu.barkpointment.beans.Dog;
import marjorieTeu.barkpointment.database.DatabaseAccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDatabase {

	@Autowired
	private DatabaseAccess db;

	/**
	 * test success add
	 */
	@Test
	public void testAddDogSuccess() {
		Dog dog = new Dog();
		dog.setName("ShibaName");
		dog.setGender('F');
		dog.setBreed("Shiba Inu");
		dog.setOwnerID(1);

		int origSize = db.getDogs().size();
		db.addDog(dog);
		int newSize = db.getDogs().size();
		
		assertThat(newSize).isEqualTo(origSize + 1);
	}
	
	
	@Test
	public void testAddAccount() {
		Account acct = new Account();
		acct.setFname("John");
		acct.setLname("Cena");
		acct.setPhone("");
		acct.setAddress("nowhere");
		acct.setCity("Toronto");
		acct.setProvince("ON");
		acct.setEmail("j.cena@gmail.com");

		int origSize = db.getAccounts().size();
		db.addAcct(acct);
		int newSize = db.getAccounts().size();
		assertThat(newSize).isEqualTo(origSize + 1);
	}

}
