package marjorieTeu.barkpointment.database;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import marjorieTeu.barkpointment.beans.Dog;
import marjorieTeu.barkpointment.beans.Account;
import marjorieTeu.barkpointment.beans.Appointment;

@Repository
public class DatabaseAccess {

	private NamedParameterJdbcTemplate jdbc;

	BeanPropertyRowMapper<Dog> dogMapper = new BeanPropertyRowMapper<Dog>(Dog.class);
	BeanPropertyRowMapper<Account> acctMapper = new BeanPropertyRowMapper<Account>(Account.class);
	BeanPropertyRowMapper<Appointment> apptMapper = new BeanPropertyRowMapper<Appointment>(Appointment.class);
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	
	
	public DatabaseAccess(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	/**
	 * retrieves all dogs 
	 * @return dogsList
	 */
	public List<Dog> getDogs() {
		String query = "Select * FROM Dogs";
		List<Dog> dogsList = jdbc.query(query, dogMapper);
		return dogsList;
	}
	
	/**
	 * retrieves all dogs of a specific user
	 * @param acctID of user
	 * @return dogsList
	 */
	public List<Dog> getDogsOf(int acctID) {
		String query = "Select * FROM Dogs WHERE ownerID=:acctID";
		namedParameters.addValue("acctID", acctID);
		
		List<Dog> dogsList = jdbc.query(query, namedParameters, dogMapper);
		return dogsList;
	}

	/**
	 * adds a dog 
	 * @param dog
	 * @return returnValue whether 
	 */
	public int addDog(Dog dog) {

		String query = "INSERT INTO dogs(name, gender, breed, birthday, ownerID)"
				+ "VALUES(:name, :gender, :breed, :birthday, :ownerID)";

		namedParameters
			.addValue("name", dog.getName())
			.addValue("gender", dog.getGender())
			.addValue("breed", dog.getBreed())
			.addValue("birthday", dog.getBirthday())
			.addValue("ownerID", dog.getOwnerID());

		int returnValue = jdbc.update(query, namedParameters);
		
		return returnValue;
	}

	
	/**
	 * retrieves all accounts
	 * @return list of accounts
	 */
	public List<Account> getAccounts() {
		String query = "Select * FROM Accounts";
		List<Account> acctList = jdbc.query(query, acctMapper);
		return acctList;
	}
	
	/**
	 * retrieves a specific account using a username
	 * @param username
	 * @return one account
	 */
	public Account getAccountOf(String username) {
		String query = "SELECT * FROM accounts WHERE username = :username";
		namedParameters.addValue("username", username);
		Account account = jdbc.query(query, namedParameters, acctMapper).get(0);
		
		return account;
	}

	/**
	 * adds an account
	 * @param newAcct data of new user
	 * @return returnValue
	 */
	public int addAcct(Account newAcct) {
		String query = "INSERT INTO accounts (username, password, authority, fname, lname, phone, email, address, city, province) "
				+ "VALUES (:username, :password, :authority, :fname, :lname, :phone, :email, :address, :city, :province)";

		namedParameters
		.addValue("username", newAcct.getUsername())
		.addValue("password", newAcct.getPassword())
		.addValue("authority", newAcct.getAuthority())
		.addValue("fname", newAcct.getFname())
		.addValue("lname", newAcct.getLname())
		.addValue("phone", newAcct.getPhone())
		.addValue("email", newAcct.getEmail())
		.addValue("address", newAcct.getAddress())
		.addValue("city", newAcct.getCity())
		.addValue("province", newAcct.getProvince());

		int returnValue = jdbc.update(query, namedParameters);
		return returnValue;
	}

	
	/**
	 * retrieves all appointments
	 * @return list of appointments
	 */
	public List<Appointment> getAppointments() {
		String query = "Select * FROM Appointments";
		List<Appointment> apptList = jdbc.query(query, apptMapper);
		return apptList;
	}
	
	
}
