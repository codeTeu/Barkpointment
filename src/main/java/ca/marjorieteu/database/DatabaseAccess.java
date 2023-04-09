package ca.marjorieteu.database;

import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.marjorieteu.beans.Dog;
import ca.marjorieteu.beans.Owner;

@Repository
public class DatabaseAccess {
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	BeanPropertyRowMapper<Dog> dogMapper = new BeanPropertyRowMapper<Dog>(Dog.class);
	BeanPropertyRowMapper<Owner> ownerMapper = new BeanPropertyRowMapper<Owner>(Owner.class);

	private NamedParameterJdbcTemplate db;

	public DatabaseAccess(NamedParameterJdbcTemplate db) {
		this.db = db;
	}
	
	public List<Owner> getOwnerList() {
		String query = "SELECT * FROM owners";
		List<Owner> ownerList = db.query(query, ownerMapper);
		return ownerList;
	}

	public int addOwner(Owner owner) {
		String query = "INSERT INTO owners(first_name, last_name, address, city, province, postal_code, email, phone)"
								+ "VALUES(:fname, :lname, :address, :city, :province, :postalCode, :email, :phone)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("fname", owner.getFname())
							.addValue("lname", owner.getLname())
							.addValue("address", owner.getAddress())
							.addValue("city", owner.getCity())
							.addValue("province", owner.getProvince())
							.addValue("postalCode", owner.getPostalCode())
							.addValue("email", owner.getEmail())
							.addValue("phone", owner.getPhone());

		int returnValue = db.update(query, namedParameters);
		return returnValue;
	}
	
	
	

	public List<Dog> getDogList() {
		String query = "SELECT * FROM dogs";
		List<Dog> dogList = db.query(query, dogMapper);
//		System.out.println(dogList.toString());
		return dogList;
	}
	
	public int addDog(Dog dog) {
		String query = "INSERT INTO dogs(name, gender, birthday, breed, ownerID)"
							+ "VALUES(:name, :gender, :birthday, :breed, :ownerID)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("name", dog.getName())
					.addValue("gender", dog.getGender())
					.addValue("birthday",Date.valueOf(dog.getBirthday()))
					.addValue("breed", dog.getBreed())
					.addValue("ownerID", dog.getOwnerID());

		int returnValue = db.update(query, namedParameters);
		return returnValue;
	}



}
