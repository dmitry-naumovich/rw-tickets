package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.jdbc.core.RowMapper;

import by.epam.naumovich.rw_tickets.entity.UserGroup;

/**
 * Spring JDBC RowMapper implementation which maps UserGroup table in database on UserGroup entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class UserGroupRowMapper implements RowMapper<UserGroup> {

	@Override
	public UserGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		return UserGroup.builder()
				.id(rs.getInt(1))
				.name(rs.getString(2))
				.createDate(Date.valueOf(rs.getString(3).substring(0, 10)))
				.createTime(Time.valueOf(rs.getString(3).substring(11, 19)))
				.owner(rs.getInt(4))
				.build();
	}

}
