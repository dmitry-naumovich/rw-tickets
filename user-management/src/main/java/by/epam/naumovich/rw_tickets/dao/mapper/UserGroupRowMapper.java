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
	public UserGroup mapRow(ResultSet arg0, int arg1) throws SQLException {
		UserGroup group = new UserGroup();
		group.setGr_id(arg0.getInt(1));
		group.setGr_name(arg0.getString(2));
		group.setCreateDate(Date.valueOf(arg0.getString(3).substring(0, 10)));
		group.setCreateTime(Time.valueOf(arg0.getString(3).substring(11, 19)));
		group.setOwner_id(arg0.getInt(4));
		return group;
	}

}
