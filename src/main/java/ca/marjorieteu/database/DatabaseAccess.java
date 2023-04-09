package ca.marjorieteu.database;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.marjorieteu.beans.Dog;

@Repository
public class DatabaseAccess {

	private NamedParameterJdbcTemplate db;

	public DatabaseAccess(NamedParameterJdbcTemplate db) {
		this.db = db;
	}

	public List<Dog> getDogs() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM dogs";

		BeanPropertyRowMapper<Dog> dogMapper = new BeanPropertyRowMapper<Dog>(Dog.class);

		List<Dog> dogs = db.query(query, namedParameters, dogMapper);
		return dogs;
	}
}
