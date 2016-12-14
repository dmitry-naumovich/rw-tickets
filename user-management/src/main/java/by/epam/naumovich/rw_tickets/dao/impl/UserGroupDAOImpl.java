package by.epam.naumovich.rw_tickets.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.IUserGroupDAO;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

public class UserGroupDAOImpl implements IUserGroupDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public UserGroupDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int addUserGroup(UserGroup group) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateUserGroup(int id, UserGroup updGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserGroup(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserGroup getUserGroupById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addUserToGroup(int userID, int groupID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteUserFromGroup(int userID, int groupID) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserGroup> getUserGroupsByUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
