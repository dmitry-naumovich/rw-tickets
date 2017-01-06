package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.mapper.IntegerRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.StringRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.UserRowMapper;
import by.epam.naumovich.rw_tickets.entity.User;

public class UserDAOImpl implements IUserDAO {

	public static final String INSERT_NEW_USER = "INSERT INTO rw_users (login, pwd, fname, sname, email, country, city, address, phone, passport) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_USER = "UPDATE rw_users SET login = ?, pwd = ?, fname = ?, sname = ?, email = ?, country = ?, city = ?, address = ?, phone = ?, passport = ? WHERE rw_users.u_id = ?";
	public static final String DELETE_USER = "DELETE FROM rw_users WHERE u_id = ?";
	public static final String SELECT_USER_BY_ID = "SELECT * FROM rw_users WHERE u_id = ?";
	public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM rw_users WHERE login = ?";
	public static final String SELECT_ALL_USERS = "SELECT * FROM rw_users";
	public static final String SELECT_ID_BY_LOGIN = "SELECT u_id FROM rw_users WHERE login = ?";
	public static final String SELECT_LOGIN_BY_ID = "SELECT login FROM rw_users WHERE u_id = ?";
	public static final String SELECT_GROUP_USERS = "SELECT rw_users.* FROM rw_users JOIN gr_involve ON rw_users.u_id = gr_involve.user_id WHERE gr_id = ?";
	public static final String SELECT_PASS_BY_LOGIN = "SELECT rw_users.pwd FROM rw_users WHERE rw_users.login = ?";
	public static final String SELECT_PASS_BY_EMAIL = "SELECT rw_users.pwd FROM rw_users WHERE rw_users.email = ?";
	public static final String SELECT_USERS_BY_FNAME = "SELECT * FROM rw_users WHERE fname = ?";
	public static final String SELECT_USERS_BY_SNAME = "SELECT * FROM rw_users WHERE sname = ?";
	public static final String SELECT_USERS_BY_COUNTRY = "SELECT * FROM rw_users WHERE country = ?";
	public static final String SELECT_USERS_BY_CITY = "SELECT * FROM rw_users WHERE city = ?";
	public static final String SELECT_ALL_USERS_SORT_BY_NAME = "SELECT * FROM rw_users ORDER BY fname ASC";
	public static final String SELECT_ALL_USERS_SORT_BY_SURNAME = "SELECT * FROM rw_users ORDER BY sname ASC";
	public static final String SELECT_ALL_USERS_SORT_BY_EMAIL = "SELECT * FROM rw_users ORDER BY email ASC";
	public static final String SELECT_ALL_USERS_SORT_BY_COUNTRY = "SELECT * FROM rw_users ORDER BY country ASC";
	public static final String SELECT_ALL_USERS_SORT_BY_CITY = "SELECT * FROM rw_users ORDER BY city ASC";
	public static final String SELECT_ALL_USERS_SORT_BY_ADDRESS = "SELECT * FROM rw_users ORDER BY address ASC";
	public static final String SELECT_ALL_USERS_SORT_BY_LOGIN = "SELECT * FROM rw_users ORDER BY login ASC";
	public static final String SELECT_ALL_USERS_SORT_BY_BDATE = "SELECT * FROM rw_users ORDER BY b_date ASC";

	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int addUser(User user) {
		Object[] params = new Object[] {user.getLogin(), user.getPwd(), user.getFname(), user.getSname(), user.getEmail(), 
				 user.getCountry(), user.getCity(), user.getAddress(), user.getPhone(), user.getPassport()};
		
		int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		
		jdbcTemplate.update(INSERT_NEW_USER, params, types);
		return getIdByLogin(user.getLogin());
	}

	@Override
	public void updateUser(int id, User updUser) {
		Object[] params = new Object[] {updUser.getLogin(), updUser.getPwd(), updUser.getFname(), updUser.getSname(), updUser.getEmail(), 
				 updUser.getCountry(), updUser.getCity(), updUser.getAddress(), updUser.getPhone(), updUser.getPassport(), id};
		
		int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
		
		jdbcTemplate.update(UPDATE_USER, params, types);
	}

	@Override
	public void deleteUser(int id) {
		Object[] params = new Object[] {id};
		int[] types = new int[] {Types.INTEGER};
		jdbcTemplate.update(DELETE_USER, params, types);
	}

	@Override
	public User getUserById(int id) {
		Object[] params = new Object[] {id};
		List<User> users = jdbcTemplate.query(SELECT_USER_BY_ID, params, new UserRowMapper());
		return users.get(0);
	}

	@Override
	public User getUserByLogin(String login) {
		Object[] params = new Object[] {login};
		List<User> users = jdbcTemplate.query(SELECT_USER_BY_LOGIN, params, new UserRowMapper());
		return users.get(0);
	}

	@Override
	public List<User> getAllUsers() {
		return jdbcTemplate.query(SELECT_ALL_USERS, new UserRowMapper());
	}

	@Override
	public List<User> getAllGroupMembers(int groupID) {
		Object[] params = new Object[] {groupID};
		return jdbcTemplate.query(SELECT_GROUP_USERS, params, new UserRowMapper());	
	}
	
	@Override
	public int getIdByLogin(String login) {
		Object[] params = new Object[]{login};
		int[] types = new int[] {Types.VARCHAR};
		List<Integer> ints = jdbcTemplate.query(SELECT_ID_BY_LOGIN, params, types, new IntegerRowMapper());
		return ints.get(0);
	}

	@Override
	public String getLoginById(int id) {
		Object[] params = new Object[]{id};
		List<String> strings = jdbcTemplate.query(SELECT_LOGIN_BY_ID, params, new StringRowMapper());
		return strings.get(0);
	}
	
	@Override
	public String getPasswordByLogin(String login) {
		Object[] params = new Object[]{login};
		int[] types = new int[] {Types.VARCHAR};
		List<String> strings = jdbcTemplate.query(SELECT_PASS_BY_LOGIN, params, types, new StringRowMapper());
		return strings.get(0);
	}

	@Override
	public String getPasswordByEmail(String email) {
		Object[] params = new Object[]{email};
		int[] types = new int[] {Types.VARCHAR};
		List<String> strings = jdbcTemplate.query(SELECT_PASS_BY_EMAIL, params, types, new StringRowMapper());
		return strings.get(0);
	}

	@Override
	public List<User> getUsersByName(String name) {
		Object[] params = new Object[] {name};
		return jdbcTemplate.query(SELECT_USERS_BY_FNAME, params, new UserRowMapper());
	}

	@Override
	public List<User> getUsersBySurname(String surname) {
		Object[] params = new Object[] {surname};
		return jdbcTemplate.query(SELECT_USERS_BY_SNAME, params, new UserRowMapper());
	}

	@Override
	public List<User> getUsersByCountry(String country) {
		Object[] params = new Object[] {country};
		return jdbcTemplate.query(SELECT_USERS_BY_COUNTRY, params, new UserRowMapper());
	}

	@Override
	public List<User> getUsersByCity(String city) {
		Object[] params = new Object[] {city};
		return jdbcTemplate.query(SELECT_USERS_BY_CITY, params, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsersSortByName() {
		return jdbcTemplate.query(SELECT_ALL_USERS_SORT_BY_NAME, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsersSortBySurname() {
		return jdbcTemplate.query(SELECT_ALL_USERS_SORT_BY_SURNAME, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsersSortByEmail() {
		return jdbcTemplate.query(SELECT_ALL_USERS_SORT_BY_EMAIL, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsersSortByCountry() {
		return jdbcTemplate.query(SELECT_ALL_USERS_SORT_BY_COUNTRY, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsersSortByCity() {
		return jdbcTemplate.query(SELECT_ALL_USERS_SORT_BY_CITY, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsersSortByAddress() {
		return jdbcTemplate.query(SELECT_ALL_USERS_SORT_BY_ADDRESS, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsersSortByLogin() {
		return jdbcTemplate.query(SELECT_ALL_USERS_SORT_BY_LOGIN, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsersSortByBirthdate() {
		return jdbcTemplate.query(SELECT_ALL_USERS_SORT_BY_BDATE, new UserRowMapper());
	}

}
