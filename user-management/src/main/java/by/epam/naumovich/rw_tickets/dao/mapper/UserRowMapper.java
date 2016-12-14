package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.naumovich.rw_tickets.entity.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet arg0, int arg1) throws SQLException {
		User user = new User();
		user.setId(arg0.getInt(1));
		System.out.println("user.id = " + user.getId());
		user.setLogin(arg0.getString(2));
		user.setPwd(arg0.getString(3));
		user.setFname(arg0.getString(4));
		user.setSname(arg0.getString(5));
		user.setEmail(arg0.getString(6));
		user.setBirthDate(arg0.getDate(7));
		user.setCountry(arg0.getString(8));
		user.setCity(arg0.getString(9));
		user.setAddress(arg0.getString(10));
		user.setPhone(arg0.getString(11));
		user.setPassport(arg0.getString(12));
		user.setAdmin(arg0.getBoolean(13));
		return user;
	}

}
