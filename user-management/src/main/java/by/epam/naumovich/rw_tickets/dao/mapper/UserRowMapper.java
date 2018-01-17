package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.naumovich.rw_tickets.entity.User;

/**
 * Spring JDBC RowMapper implementation which maps User table in database on User entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return User.builder()
				.id(rs.getInt(1))
				.login(rs.getString(2))
				.pwd(rs.getString(3))
				.fname(rs.getString(4))
				.sname(rs.getString(5))
				.email(rs.getString(6))
				.birthDate(rs.getDate(7))
				.city(rs.getString(8))
				.country(rs.getString(9))
				.address(rs.getString(10))
				.phone(rs.getString(11))
				.passport(rs.getString(12))
				.isAdmin(rs.getBoolean(13))
				.build();
	}

}
