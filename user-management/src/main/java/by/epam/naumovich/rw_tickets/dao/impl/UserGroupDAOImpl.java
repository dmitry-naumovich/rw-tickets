package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.IUserGroupDAO;
import by.epam.naumovich.rw_tickets.dao.mapper.IntegerRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.UserGroupRowMapper;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

public class UserGroupDAOImpl implements IUserGroupDAO {

	public static final String INSERT_NEW_GROUP = "INSERT INTO rw_groups (gr_name, owner_id) VALUES (?, ?)";
	public static final String UPDATE_GROUP = "UPDATE rw_groups SET gr_name = ?, owner_id = ? WHERE gr_id = ?";
	public static final String DELETE_GROUP = "DELETE FROM rw_groups WHERE gr_id = ?";
	public static final String SELECT_GROUP_BY_ID = "SELECT * FROM rw_groups WHERE gr_id = ?";
	public static final String INSERT_NEW_INVOLVE = "INSERT INTO gr_involve (user_id, gr_id) VALUES (?, ?)";
	public static final String DELETE_USER_FROM_GROUP = "DELETE FROM gr_involve WHERE user_id = ? AND gr_id = ?";
	public static final String SELECT_USER_GROUPS_BY_ID = "SELECT rw_groups.* FROM rw_groups JOIN gr_involve ON rw_groups.gr_id = gr_involve.gr_id WHERE gr_involve.user_id = ?";
	public static final String SELECT_GROUP_ID_BY_NAME_AND_OWNER = "SELECT gr_id FROM rw_groups WHERE gr_name = ? AND owner_id = ?";
	
	private JdbcTemplate jdbcTemplate;
	
	public UserGroupDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int addUserGroup(UserGroup group) {
		Object[] params = new Object[] {group.getGr_name(), group.getOwner_id()};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER};
		jdbcTemplate.update(INSERT_NEW_GROUP, params, types);
		
		// insert new involve here
		
		return getGroupIdByNameAndOwner(group.getGr_name(), group.getOwner_id());
	}

	@Override
	public void updateUserGroup(int id, UserGroup updGroup) {
		Object[] params = new Object[] {updGroup.getGr_name(), updGroup.getOwner_id(), id};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER, Types.INTEGER};
		jdbcTemplate.update(UPDATE_GROUP, params, types);
	}

	@Override
	public void deleteUserGroup(int id) {
		Object[] params = new Object[] {id};
		jdbcTemplate.update(DELETE_GROUP, params);
	}

	@Override
	public UserGroup getUserGroupById(int id) {
		Object[] params = new Object[] {id};
		List<UserGroup> groups = jdbcTemplate.query(SELECT_GROUP_BY_ID, params, new UserGroupRowMapper());
		return groups.get(0);
	}

	@Override
	public void addUserToGroup(int userID, int groupID) {
		Object[] params = new Object[] {userID, groupID};
		int[] types = new int[] {Types.INTEGER, Types.INTEGER};
		jdbcTemplate.update(INSERT_NEW_INVOLVE, params, types);
	}

	@Override
	public void deleteUserFromGroup(int userID, int groupID) {
		Object[] params = new Object[] {userID, groupID};
		int[] types = new int[] {Types.INTEGER, Types.INTEGER};
		jdbcTemplate.update(DELETE_USER_FROM_GROUP, params, types);
	}

	@Override
	public List<UserGroup> getUserGroupsByUser(int userID) {
		Object[] params = new Object[] {userID};
		return jdbcTemplate.query(SELECT_USER_GROUPS_BY_ID, params, new UserGroupRowMapper());
	}

	@Override
	public int getGroupIdByNameAndOwner(String name, int ownerID) {
		Object[] params = new Object[] {name, ownerID};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER};
		List<Integer> ints = jdbcTemplate.query(SELECT_GROUP_ID_BY_NAME_AND_OWNER, params, types, new IntegerRowMapper());
		return ints.get(0);
	}

}
