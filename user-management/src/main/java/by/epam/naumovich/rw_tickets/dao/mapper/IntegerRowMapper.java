package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Spring JDBC RowMapper implementation which maps integer number from database on Integer object.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class IntegerRowMapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getInt(1);
	}

}
