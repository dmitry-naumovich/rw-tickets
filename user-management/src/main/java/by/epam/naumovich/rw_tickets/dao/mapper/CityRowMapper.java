package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.naumovich.rw_tickets.entity.City;

/**
 * Spring JDBC RowMapper implementation which maps City table in database on City entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class CityRowMapper implements RowMapper<City> {

	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		return City.builder()
				.code(rs.getString(1))
				.country(rs.getString(2))
				.name(rs.getString(3))
				.build();
	}

}
