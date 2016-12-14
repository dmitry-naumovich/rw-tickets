package by.epam.naumovich.rw_tickets.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.IGroupRequestDAO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;

public class GroupRequestDAOImpl implements IGroupRequestDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public GroupRequestDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int addGroupRequest(GroupRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateGroupRequest(int num, GroupRequest updRequest) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteGroupRequest(int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public GroupRequest getGroupRequestById(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupRequest> getUserIncRequests(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupRequest> getUserIncRequestsSortByDate(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupRequest> getUserOutRequests(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupRequest> getUserOutRequestsSortByDate(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
