package marjorieTeu.barkpointment.database;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import marjorieTeu.barkpointment.beans.Dog;
import marjorieTeu.barkpointment.beans.Account;

@Repository
public class DatabaseAccess {

	private NamedParameterJdbcTemplate jdbc;

	BeanPropertyRowMapper<Dog> dogMapper = new BeanPropertyRowMapper<Dog>(Dog.class);
	BeanPropertyRowMapper<Account> acctMapper = new BeanPropertyRowMapper<Account>(Account.class);

	public DatabaseAccess(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	/*
	 * retrieve all dogs
	 */
	public List<Dog> getDogs() {
		String query = "Select * FROM Dogs";
		List<Dog> dogs = jdbc.query(query, dogMapper);
		return dogs;
	}

	/*
	 * add a dog
	 */
	public int addDog(Dog dog) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "INSERT INTO dogs(name, gender, breed, birthday, ownerID)"
				+ "VALUES(:name, :gender, :breed, :birthday, :ownerID)";

		namedParameters
			.addValue("name", dog.getName())
			.addValue("gender", dog.getGender())
			.addValue("breed", dog.getBreed())
			.addValue("birthday", dog.getBirthday())
			.addValue("ownerID", 2);

		int returnValue = jdbc.update(query, namedParameters);
		return returnValue;
	}


	public List<Account> getAccounts() {
		String query = "Select * FROM Accounts";
		List<Account> acctList = jdbc.query(query, acctMapper);
		return acctList;
	}
}
