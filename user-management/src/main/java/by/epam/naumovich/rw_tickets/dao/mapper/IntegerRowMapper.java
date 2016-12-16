package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IntegerRowMapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet arg0, int arg1) throws SQLException {
		return arg0.getInt(1);
	}

}
