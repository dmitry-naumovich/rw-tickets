package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Spring JDBC RowMapper implementation which maps varchar value from database on String object.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class StringRowMapper implements RowMapper<String> {

	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getString(1);
	}

}
