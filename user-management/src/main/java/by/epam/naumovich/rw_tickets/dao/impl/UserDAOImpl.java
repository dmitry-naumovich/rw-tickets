package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.exception.DAOException;
import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.entity.User;

public class UserDAOImpl implements IUserDAO {

	public final static String INSERT_NEW_USER = "INSERT INTO rw_users (u_id, login, pwd, fname, sname, email, b_date, country, city, address, phone_num, passport) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int addUser(User user) throws DAOException {
		/*Object[] params = new Object[] { user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(), 
				user.getBirthDate(), user.getCountry(), user.getCity(), user.getAddress(), user.getPhone(), user.getPassport()};
		
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		
		return jdbcTemplate.update(INSERT_NEW_USER, params, types);*/
		return 0;
	}

	public void updateUser(int id, User updUser) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void deleteUser(int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	public User getUserById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByLogin(String login) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<User> getAllUsers() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
