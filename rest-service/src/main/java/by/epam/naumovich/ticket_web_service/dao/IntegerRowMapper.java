package by.epam.naumovich.ticket_web_service.dao;

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
	public Integer mapRow(ResultSet arg0, int arg1) throws SQLException {
		return arg0.getInt(1);
	}

}
