package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.exception.DAOException;
import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.entity.User;

public class UserDAOImpl implements IUserDAO {

	public final static String INSERT_NEW_USER = "INSERT INTO rw_users (u_id, login, pwd, fname, sname, email, b_date, country, city, address, phone, passport) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int addUser(User user) throws DAOException {
		Object[] params = new Object[] { user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(), 
				user.getBirthDate(), user.getCountry(), user.getCity(), user.getAddress(), user.getPhone(), user.getPassport()};
		
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		
		return jdbcTemplate.update(INSERT_NEW_USER, params, types);
		//return 0;
		// here method must get the ID of the newly added user and return it
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

	public List<User> getAllUsers() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllGroupUsers(int groupID) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPasswordByLogin(String login) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPasswordByEmail(String email) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersByName(String name) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersBySurname(String surname) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersByCountry(String country) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersByCity(String city) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByName() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortBySurname() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByEmail() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByCountry() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByCity() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByAddress() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByLogin() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByBirthdate() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
