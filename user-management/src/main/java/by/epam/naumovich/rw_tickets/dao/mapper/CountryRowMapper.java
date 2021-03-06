package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.naumovich.rw_tickets.entity.Country;

/**
 * Spring JDBC RowMapper implementation which maps Country table in database on Country entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class CountryRowMapper implements RowMapper<Country> {

	@Override
	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Country.builder()
				.code(rs.getString(1))
				.name(rs.getString(2))
				.build();
	}

}
