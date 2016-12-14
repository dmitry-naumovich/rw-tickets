package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import by.epam.naumovich.rw_tickets.dao.exception.DAOException;
import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.mapper.UserRowMapper;
import by.epam.naumovich.rw_tickets.entity.User;

public class UserDAOImpl implements IUserDAO {

	public final static String INSERT_NEW_USER = "INSERT INTO rw_users (u_id, login, pwd, fname, sname, email, b_date, country, city, address, phone, passport) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public final static String UPDATE_USER = "UPDATE rw_users SET login = ?, pwd = ?, fname = ?, sname = ?, email = ?, b_date = ?, country = ?, city = ?, address = ?, phone = ?, passport = ? WHERE u_id = ?";
	public final static String DELETE_USER = "DELETE FROM rw_users WHERE u_id = ?";
	public final static String SELECT_USER_BY_ID = "SELECT * FROM rw_users WHERE u_id = ?";
	public final static String SELECT_USER_BY_LOGIN = "SELECT * FROM rw_users WHERE login = ?";
	public final static String SELECT_ALL_USERS = "SELECT * FROM rw_users";
	public final static String SELECT_ID_BY_LOGIN = "SELECT rw_users.u_id FROM rw_users WHERE rw_users.login = ?";
	public final static String SELECT_GROUP_USERS = "SELECT rw_users.* FROM rw_users JOIN gr_involve ON rw_users.u_id = gr_involve.u_id WHERE gr_id = ?";
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int addUser(User user) {
		
		Object[] params = new Object[] { user.getId(), user.getLogin(), user.getPwd(), user.getFname(), user.getSname(), user.getEmail(), 
				user.getBirthDate(), user.getCountry(), user.getCity(), user.getAddress(), user.getPhone(), user.getPassport()};
		
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		
		jdbcTemplate.update(INSERT_NEW_USER, params, types);
		
		/*Object[] params2 = new Object[]{user.getLogin()};
		int[] types2 = new int[] {Types.VARCHAR};
		jdbcTemplate.update(SELECT_ID_BY_LOGIN, params2, types2, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet arg0) throws SQLException {
				user.setId(arg0.getInt(0));
			}
			
		});*/
		return user.getId();
	}

	public void updateUser(int id, User updUser) {
		Object[] params = new Object[] {updUser.getLogin(), updUser.getPwd(), updUser.getFname(), updUser.getSname(), updUser.getEmail(), 
				updUser.getBirthDate(), updUser.getCountry(), updUser.getCity(), updUser.getAddress(), updUser.getPhone(), updUser.getPassport(), id};
		
		int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
		
		jdbcTemplate.update(UPDATE_USER, params, types);
	}

	public void deleteUser(int id) {
		Object[] params = new Object[] {id};
		int[] types = new int[] {Types.INTEGER};
		jdbcTemplate.update(DELETE_USER, params, types);
	}

	public User getUserById(int id) {
		Object[] params = new Object[] {id};
		List<User> users = jdbcTemplate.query(SELECT_USER_BY_ID, params, new UserRowMapper());
		return users.get(0);
	}

	public User getUserByLogin(String login) {
		Object[] params = new Object[] {login};
		List<User> users = jdbcTemplate.query(SELECT_USER_BY_LOGIN, params, new UserRowMapper());
		return users.get(0);
	}

	public List<User> getAllUsers() {
		return jdbcTemplate.query(SELECT_ALL_USERS, new UserRowMapper());
	}

	public List<User> getAllGroupUsers(int groupID) {
		Object[] params = new Object[] {groupID};
		return jdbcTemplate.query(SELECT_USER_BY_LOGIN, params, new UserRowMapper());	
	}

	public String getPasswordByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersByCountry(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByName() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortBySurname() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByCity() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsersSortByBirthdate() {
		// TODO Auto-generated method stub
		return null;
	}

}
