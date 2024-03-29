package ca.marjorieteu.database;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.marjorieteu.beans.Appointment;
import ca.marjorieteu.beans.Dog;
import ca.marjorieteu.beans.Owner;

@Repository
public class DatabaseAccess {
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	BeanPropertyRowMapper<Dog> dogMapper = new BeanPropertyRowMapper<Dog>(Dog.class);
	BeanPropertyRowMapper<Owner> ownerMapper = new BeanPropertyRowMapper<Owner>(Owner.class);
	BeanPropertyRowMapper<Appointment> apptMapper = new BeanPropertyRowMapper<Appointment>(Appointment.class);

	private NamedParameterJdbcTemplate db;

	public DatabaseAccess(NamedParameterJdbcTemplate db) {
		this.db = db;
	}

	public List<Appointment> getApptList() {
		String query = "SELECT * FROM appointments";
		List<Appointment> apptList = db.query(query, apptMapper);
		return apptList;
	}

	public List<Appointment> getApptListOf(int ownerID) {
		String query = "SELECT * FROM appointments WHERE ownerID=:ownerID";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("ownerID", ownerID);
		
		List<Appointment> apptList = db.query(query, namedParameters, apptMapper);
		
		return apptList;
	}
	
	public int addAppt(Appointment appt) {
		String query = "INSERT INTO appointments(date, time, ownerID, dogID, dogName,reasonOfVisit)"
				+ "VALUES(:date, :time, :ownerID, :dogID, :dogName,:reasonOfVisit)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("date", Date.valueOf(appt.getDate()))
				.addValue("time", Time.valueOf(appt.getTime()))
				.addValue("ownerID", appt.getOwnerID())
				.addValue("dogID", appt.getDogID())
				.addValue("dogName", appt.getDogName())
				.addValue("reasonOfVisit", appt.getReasonOfVisit());

		int returnValue = db.update(query, namedParameters);
		return returnValue;
	}

	public Owner getOwner(int ownerID) {
		String query = "SELECT * FROM owners WHERE ownerID= :ownerID";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("ownerID", ownerID);

		Owner owner = db.query(query, namedParameters, ownerMapper).get(0);

		return owner;
	}

	public List<Owner> getOwnerList() {
		String query = "SELECT * FROM owners";
		List<Owner> ownerList = db.query(query, ownerMapper);
		// System.out.println(" list: " +ownerList.get(0).toString());
		return ownerList;
	}

	public int addOwner(Owner owner) {
		String query = "INSERT INTO owners(fname, lname, address, city, province, postalCode, email, phone)"
				+ "VALUES(:fname, :lname, :address, :city, :province, :postalCode, :email, :phone)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("fname", owner.getFname()).addValue("lname", owner.getLname())
				.addValue("address", owner.getAddress()).addValue("city", owner.getCity())
				.addValue("province", owner.getProvince()).addValue("postalCode", owner.getPostalCode())
				.addValue("email", owner.getEmail()).addValue("phone", owner.getPhone());

		int returnValue = db.update(query, namedParameters);
		return returnValue;
	}

	public int profileUpdate(Owner owner) {

		String query = "UPDATE owners SET fname=:fname, lname=:lname, address=:address, city=:city, province=:province, postalCode=:postalCode, email=:email, phone=:phone WHERE ownerID = :ownerID";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("ownerID", owner.getOwnerID()).addValue("fname", owner.getFname())
				.addValue("lname", owner.getLname()).addValue("address", owner.getAddress())
				.addValue("city", owner.getCity()).addValue("province", owner.getProvince())
				.addValue("postalCode", owner.getPostalCode()).addValue("email", owner.getEmail())
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
	
	

	public List<Dog> getDogListOf(int ownerID) {
		String query = "SELECT * FROM dogs WHERE ownerID=:ownerID";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("ownerID", ownerID);
		
		List<Dog> dogList = db.query(query, namedParameters, dogMapper);
		
		return dogList;
	}
	
	public Dog getDogByID(int dogID) {
		String query = "SELECT * FROM dogs WHERE dogID=:dogID";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("dogID", dogID);
		
		Dog dog = db.query(query, namedParameters, dogMapper).get(0);
		System.out.println(dog.toString());
		return dog;
	}

	public int addDog(Dog dog) {
		String query = "INSERT INTO dogs(name, gender, birthday, breed, ownerID)"
				+ "VALUES(:name, :gender, :birthday, :breed, :ownerID)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("name", dog.getName()).addValue("gender", dog.getGender())
				.addValue("birthday", Date.valueOf(dog.getBirthday())).addValue("breed", dog.getBreed())
				.addValue("ownerID", dog.getOwnerID());

		int returnValue = db.update(query, namedParameters);
		return returnValue;
	}

}
