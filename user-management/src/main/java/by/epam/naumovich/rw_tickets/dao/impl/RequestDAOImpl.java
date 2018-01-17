package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.IRequestDAO;
import by.epam.naumovich.rw_tickets.dao.mapper.RequestRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.IntegerRowMapper;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import org.springframework.stereotype.Repository;

/**
 * IRequestDAO implementation for Oracle database which uses Spring JDBC framework to connect to the DB and perform all operations,
 * which might be needed by the service layer. This class works with GroupRequest entity and relevant table in the DB.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Repository
public class RequestDAOImpl implements IRequestDAO {

	private static final String INSERT_NEW_REQUEST = "INSERT INTO gr_request (from_user, to_user, gr_id, status, rq_comment) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_REQUEST = "UPDATE gr_request SET status = ? WHERE rq_num = ?";
	private static final String DELETE_REQUEST = "DELETE FROM gr_request WHERE rq_num = ?";
	private static final String SELECT_REQ_BY_NUM = "SELECT * FROM gr_request WHERE rq_num = ?";
	private static final String SELECT_REQ_NUM_BY_USER_AND_GROUP_IDS = "SELECT rq_num FROM gr_request WHERE from_user = ? AND to_user = ? AND gr_id = ?";
	private static final String SELECT_USER_INC_REQUESTS = "SELECT * FROM gr_request WHERE to_user = ? AND status != 'c' ORDER BY cr_datetime DESC";
	private static final String SELECT_USER_OUT_REQUESTS = "SELECT * FROM gr_request WHERE from_user = ? ORDER BY cr_datetime DESC";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public RequestDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int addRequest(GroupRequest request)  {
		Object[] params = new Object[] {request.getFrom_user(), request.getTo_user(), request.getGrId(),
				request.getStatus(), request.getComment()};
		int[] types = new int[] {Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR};
		jdbcTemplate.update(INSERT_NEW_REQUEST, params, types);
		return getReqNumByUserAndGroupIDs(request.getFrom_user(), request.getTo_user(), request.getGrId());
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
		List<GroupRequest> reqs = jdbcTemplate.query(SELECT_REQ_BY_NUM, params, new RequestRowMapper());
		return reqs.isEmpty() ? null : reqs.get(0);
	}

	@Override
	public int getReqNumByUserAndGroupIDs(int fromUser, int toUser, int groupID) {
		Object[] params = new Object[] {fromUser, toUser, groupID};
		int[] types = new int[] {Types.INTEGER, Types.INTEGER, Types.INTEGER};
		List<Integer> ints = jdbcTemplate.query(SELECT_REQ_NUM_BY_USER_AND_GROUP_IDS, params, types, new IntegerRowMapper());
		return ints.isEmpty() ? -1 : ints.get(0);
	}

	@Override
	public List<GroupRequest> getUserIncRequests(int userID) {
		Object[] params = new Object[] {userID};
		return jdbcTemplate.query(SELECT_USER_INC_REQUESTS, params, new RequestRowMapper());
	}

	@Override
	public List<GroupRequest> getUserOutRequests(int userID) {
		Object[] params = new Object[] {userID};
		return jdbcTemplate.query(SELECT_USER_OUT_REQUESTS, params, new RequestRowMapper());
	}
}
