package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.dao.mapper.IntegerRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.StringRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.UserGroupRowMapper;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import org.springframework.stereotype.Repository;

/**
 * IGroupDAO implementation for Oracle database which uses Spring JDBC framework to connect to the DB and perform all operations,
 * which might be needed by the service layer. This class works with UserGroup entity and relevant table in the DB.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Repository
public class GroupDAOImpl implements IGroupDAO {

	private static final String INSERT_NEW_GROUP = "INSERT INTO rw_group (name, owner) VALUES (?, ?)";
	private static final String UPDATE_GROUP = "UPDATE rw_group SET name = ?, owner = ? WHERE id = ?";
	private static final String DELETE_GROUP = "DELETE FROM rw_group WHERE id = ?";
	private static final String SELECT_GROUP_BY_ID = "SELECT * FROM rw_group WHERE id = ?";
	private static final String SELECT_GROUP_NAME_BY_ID = "SELECT name FROM rw_group WHERE id = ?";
	private static final String INSERT_NEW_INVOLVE = "INSERT INTO gr_involve (user_id, gr_id) VALUES (?, ?)";
	private static final String DELETE_USER_FROM_GROUP = "DELETE FROM gr_involve WHERE user_id = ? AND gr_id = ?";
	private static final String DELETE_ALL_GROUP_USERS = "DELETE FROM gr_involve WHERE gr_id = ?";
	private static final String DELETE_GROUPS_BY_OWNER = "DELETE FROM rw_group WHERE owner = ?";
	private static final String DELETE_USER_FROM_ALL_GROUPS = "DELETE FROM gr_involve WHERE user_id = ?";
	private static final String SELECT_USER_GROUPS_BY_ID = "SELECT rw_group.* FROM rw_group JOIN gr_involve ON rw_group.id = gr_involve.gr_id WHERE gr_involve.user_id = ?";
	private static final String SELECT_GROUPS_BY_OWNER = "SELECT * FROM rw_group WHERE owner = ?";
	private static final String SELECT_GROUP_ID_BY_NAME_AND_OWNER = "SELECT id FROM rw_group WHERE name = ? AND owner = ?";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GroupDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int addGroup(UserGroup group) {
		Object[] params = new Object[] {group.getName(), group.getOwner()};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER};
		jdbcTemplate.update(INSERT_NEW_GROUP, params, types);
		
		return getGroupIdByNameAndOwner(group.getName(), group.getOwner());
	}

	@Override
	public void updateGroup(int id, UserGroup updGroup) {
		Object[] params = new Object[] {updGroup.getName(), updGroup.getOwner(), id};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER, Types.INTEGER};
		jdbcTemplate.update(UPDATE_GROUP, params, types);
	}

	@Override
	public void deleteGroup(int id) {
		Object[] params = new Object[] {id};
		jdbcTemplate.update(DELETE_GROUP, params);
	}

	@Override
	public UserGroup getGroupById(int id) {
		Object[] params = new Object[] {id};
		List<UserGroup> groups = jdbcTemplate.query(SELECT_GROUP_BY_ID, params, new UserGroupRowMapper());
		return groups.isEmpty() ? null : groups.get(0);
	}

	@Override
	public String getGroupNameById(int id) {
		Object[] params = new Object[] {id};
		List<String> strings = jdbcTemplate.query(SELECT_GROUP_NAME_BY_ID, params, new StringRowMapper());
		return strings.isEmpty() ? null : strings.get(0);
	}

	@Override
	public void addGroupMember(int userID, int groupID) {
		Object[] params = new Object[] {userID, groupID};
		int[] types = new int[] {Types.INTEGER, Types.INTEGER};
		jdbcTemplate.update(INSERT_NEW_INVOLVE, params, types);
	}

	@Override
	public void removeGroupMember(int userID, int groupID) {
		Object[] params = new Object[] {userID, groupID};
		int[] types = new int[] {Types.INTEGER, Types.INTEGER};
		jdbcTemplate.update(DELETE_USER_FROM_GROUP, params, types);
	}

	@Override
	public List<UserGroup> getGroupsByUser(int userID) {
		Object[] params = new Object[] {userID};
		return jdbcTemplate.query(SELECT_USER_GROUPS_BY_ID, params, new UserGroupRowMapper());
	}
	
	@Override
	public List<UserGroup> getGroupsByOwner(int ownerID) {
		Object[] params = new Object[] {ownerID};
		return jdbcTemplate.query(SELECT_GROUPS_BY_OWNER, params, new UserGroupRowMapper());	
	}

	@Override
	public int getGroupIdByNameAndOwner(String name, int ownerID) {
		Object[] params = new Object[] {name, ownerID};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER};
		List<Integer> ints = jdbcTemplate.query(SELECT_GROUP_ID_BY_NAME_AND_OWNER, params, types, new IntegerRowMapper());
		return ints.isEmpty() ? -1 : ints.get(0);
	}

	@Override
	public void removeAllGroupMembers(int groupID) {
		Object[] params = new Object[] {groupID};
		jdbcTemplate.update(DELETE_ALL_GROUP_USERS, params);
	}

	@Override
	public void deleteAllGroupsByOwner(int ownerID) {
		Object[] params = new Object[] {ownerID};
		jdbcTemplate.update(DELETE_GROUPS_BY_OWNER, params);
	}

	@Override
	public void removeUserFromAllGroups(int userID) {
		Object[] params = new Object[] {userID};
		jdbcTemplate.update(DELETE_USER_FROM_ALL_GROUPS, params);
	}
}
