package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.mapper.IntegerRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.StringRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.UserRowMapper;
import by.epam.naumovich.rw_tickets.entity.User;
import org.springframework.stereotype.Repository;

/**
 * IUserDAO implementation for Oracle database which uses Spring JDBC framework to connect to the DB and perform all operations,
 * which might be needed by the service layer. This class works with User and UserGroup entities and relevant tables in the DB.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Repository
public class UserDAOImpl implements IUserDAO {

	public static final String INSERT_NEW_USER = "INSERT INTO rw_user (login, pwd, fname, sname, email, country, city, address, phone, passport) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_USER = "UPDATE rw_user SET login = ?, pwd = ?, fname = ?, sname = ?, email = ?, country = ?, city = ?, address = ?, phone = ?, passport = ? WHERE rw_user.id = ?";
	public static final String DELETE_USER = "DELETE FROM rw_user WHERE id = ?";
	public static final String SELECT_USER_BY_ID = "SELECT * FROM rw_user WHERE id = ?";
	public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM rw_user WHERE login = ?";
	public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM rw_user WHERE email = ?";
	public static final String SELECT_ALL_USERS = "SELECT * FROM rw_user";
	public static final String SELECT_ALL_USERS_SORTED = "SELECT * FROM rw_user ORDER BY ? ASC";
	public static final String SELECT_ID_BY_LOGIN = "SELECT id FROM rw_user WHERE login = ?";
	public static final String SELECT_LOGIN_BY_ID = "SELECT login FROM rw_user WHERE id = ?";
	public static final String SELECT_GROUP_USERS = "SELECT rw_user.* FROM rw_user JOIN gr_involve ON rw_user.id = gr_involve.user_id WHERE gr_id = ?";
	public static final String SELECT_PASS_BY_LOGIN = "SELECT rw_user.pwd FROM rw_user WHERE rw_user.login = ?";
	public static final String SELECT_PASS_BY_EMAIL = "SELECT rw_user.pwd FROM rw_user WHERE rw_user.email = ?";
	public static final String SELECT_USERS_BY_COUNTRY = "SELECT * FROM rw_user WHERE country = ?";
	public static final String SELECT_USERS_BY_CITY = "SELECT * FROM rw_user WHERE city = ? AND country = ?";
	

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
	public User getUserByEmail(String email) {
		Object[] params = new Object[] {email};
		List<User> users = jdbcTemplate.query(SELECT_USER_BY_EMAIL, params, new UserRowMapper());
		return users.get(0);
	}

	@Override
	public List<User> getAllUsers() {
		return jdbcTemplate.query(SELECT_ALL_USERS, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsersSorted(String columnName) {
		Object[] params = new Object[] {columnName};
		return jdbcTemplate.query(SELECT_ALL_USERS_SORTED, params, new UserRowMapper());
	}

	@Override
	public List<User> getAllGroupMembers(int groupID) {
		Object[] params = new Object[] {groupID};
		return jdbcTemplate.query(SELECT_GROUP_USERS, params, new UserRowMapper());	
	}
	
	@Override
	public int getIdByLogin(String login) {
		Object[] params = new Object[]{login};
		List<Integer> ints = jdbcTemplate.query(SELECT_ID_BY_LOGIN, params, new IntegerRowMapper());
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
		List<String> strings = jdbcTemplate.query(SELECT_PASS_BY_LOGIN, params, new StringRowMapper());
		return strings.get(0);
	}

	@Override
	public String getPasswordByEmail(String email) {
		Object[] params = new Object[]{email};
		List<String> strings = jdbcTemplate.query(SELECT_PASS_BY_EMAIL, params, new StringRowMapper());
		return strings.get(0);
	}

	@Override
	public List<User> getUsersByCountry(String country) {
		Object[] params = new Object[] {country};
		return jdbcTemplate.query(SELECT_USERS_BY_COUNTRY, params, new UserRowMapper());
	}

	@Override
	public List<User> getUsersByCity(String cityCode, String countryCode) {
		Object[] params = new Object[] {cityCode, countryCode};
		int[] types = new int[] {Types.VARCHAR, Types.VARCHAR};
		return jdbcTemplate.query(SELECT_USERS_BY_CITY, params, types, new UserRowMapper());
	}
}
