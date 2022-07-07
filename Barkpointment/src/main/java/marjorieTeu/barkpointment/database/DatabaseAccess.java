package marjorieTeu.barkpointment.database;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import marjorieTeu.barkpointment.beans.Dog;

@Repository
public class DatabaseAccess {

	private NamedParameterJdbcTemplate jdbc;

	public DatabaseAccess(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	/*	
	 * retrieve all dogs 
	 */
	public List<Dog> getDogs() {
		String query = "Select * FROM Dogs WHERE name IS NOT NULL";

		BeanPropertyRowMapper<Dog> dogMapper = new BeanPropertyRowMapper<Dog>(Dog.class);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		List<Dog> dogs = jdbc.query(query, namedParameters, dogMapper);
		return dogs;
	}
	
	
	
	

}
