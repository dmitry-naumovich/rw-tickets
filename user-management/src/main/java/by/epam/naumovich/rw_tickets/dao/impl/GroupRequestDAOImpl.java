package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.IGroupRequestDAO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;

public class GroupRequestDAOImpl implements IGroupRequestDAO {

	public static final String INSERT_NEW_REQUEST = "INSERT INTO gr_requests (rq_type, from_user, to_user, gr_id, cr_datetime, cl_datetime, status, rq_comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?";
	
	private JdbcTemplate jdbcTemplate;
	
	public GroupRequestDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int addGroupRequest(GroupRequest request) {
		Object[] params = new Object[] {request.getType(), request.getFromUser(), request.getToUser(), request.getGroupId(),
				request.getCreateDate(), request.getCreateTime(), request.getCloseDate(), request.getCloseTime(), request.getStatus(), request.getComment()};
		int[] types = new int[] {Types.CHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DATE, Types.TIME, Types.DATE, Types.TIME, Types.CHAR, Types.VARCHAR};
		jdbcTemplate.update(INSERT_NEW_REQUEST, params, types);
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
