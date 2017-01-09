package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.IRequestDAO;
import by.epam.naumovich.rw_tickets.dao.mapper.GroupRequestMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.IntegerRowMapper;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;

public class RequestDAOImpl implements IRequestDAO {

	public static final String INSERT_NEW_REQUEST = "INSERT INTO gr_requests (from_user, to_user, gr_id, status, rq_comment) VALUES (?, ?, ?, ?, ?)";
	public static final String UPDATE_REQUEST = "UPDATE gr_requests SET status = ? WHERE rq_num = ?";
	public static final String DELETE_REQUEST = "DELETE FROM gr_requests WHERE rq_num = ?";
	public static final String SELECT_REQ_BY_NUM = "SELECT * FROM gr_requests WHERE rq_num = ?";
	public static final String SELECT_REQ_NUM_BY_USER_AND_GROUP_IDS = "SELECT rq_num FROM gr_requests WHERE from_user = ? AND to_user = ? AND gr_id = ?";
	public static final String SELECT_USER_INC_REQUESTS = "SELECT * FROM gr_requests WHERE to_user = ? AND status != 'c' ORDER BY cr_datetime DESC";
	public static final String SELECT_USER_OUT_REQUESTS = "SELECT * FROM gr_requests WHERE from_user = ? ORDER BY cr_datetime DESC";
	
	private JdbcTemplate jdbcTemplate;
	
	public RequestDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int addRequest(GroupRequest request)  {
		Object[] params = new Object[] {request.getFrom_user(), request.getTo_user(), request.getGr_id(),
				request.getStatus(), request.getRq_comment()};
		int[] types = new int[] {Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR};
		jdbcTemplate.update(INSERT_NEW_REQUEST, params, types);
		return getReqNumByUserAndGroupIDs(request.getFrom_user(), request.getTo_user(), request.getGr_id());
	}

	@Override
	public void updateRequest(int num, char newStatus) {
		Object[] params = new Object[] {newStatus, num};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER};
		jdbcTemplate.update(UPDATE_REQUEST, params, types);
	}

	@Override
	public void deleteRequest(int num) {
		Object[] params = new Object[] {num};
		jdbcTemplate.update(DELETE_REQUEST, params);
	}

	@Override
	public GroupRequest getRequestByNum(int num) {
		Object[] params = new Object[] {num};
		List<GroupRequest> reqs = jdbcTemplate.query(SELECT_REQ_BY_NUM, params, new GroupRequestMapper());
		return reqs.get(0);
	}

	@Override
	public int getReqNumByUserAndGroupIDs(int fromUser, int toUser, int groupID) {
		Object[] params = new Object[] {fromUser, toUser, groupID};
		int[] types = new int[] {Types.INTEGER, Types.INTEGER, Types.INTEGER};
		List<Integer> ints = jdbcTemplate.query(SELECT_REQ_NUM_BY_USER_AND_GROUP_IDS, params, types, new IntegerRowMapper());
		return ints.get(0);
	}

	@Override
	public List<GroupRequest> getUserIncRequests(int userID) {
		Object[] params = new Object[] {userID};
		return jdbcTemplate.query(SELECT_USER_INC_REQUESTS, params, new GroupRequestMapper());
	}

	@Override
	public List<GroupRequest> getUserOutRequests(int userID) {
		Object[] params = new Object[] {userID};
		return jdbcTemplate.query(SELECT_USER_OUT_REQUESTS, params, new GroupRequestMapper());
	}
}
