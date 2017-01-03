package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IUserGroupDAO;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.iface.IUserGroupService;

public class UserGroupServiceImpl implements IUserGroupService {

	private IUserGroupDAO groupDAO;
	
	public void setGroupDAO(IUserGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	@Override
	public int addGroup(UserGroup group) {
		int id = groupDAO.addUserGroup(group);
		groupDAO.addUserToGroup(group.getOwner_id(), id);
		return id;
	}

	@Override
	public void updateGroup(UserGroup group) {
		groupDAO.updateUserGroup(group.getGr_id(), group);
		
	}

	@Override
	public void deleteGroup(int groupID) {
		groupDAO.deleteAllUsersFromGroup(groupID);
		groupDAO.deleteGroup(groupID);
	}

	@Override
	public UserGroup getGroupByID(int id) {
		return groupDAO.getUserGroupById(id);
	}

	@Override
	public List<UserGroup> getGroupsByUser(int userID) {
		return groupDAO.getGroupsByUser(userID);
	}

	@Override
	public void addUserToGroup(int userID, int groupID) {
		groupDAO.addUserToGroup(userID, groupID);
	}

	@Override
	public void deleteUserFromGroup(int userID, int groupID) {
		groupDAO.deleteUserFromGroup(userID, groupID);
	}

	@Override
	public void deleteAllGroupsByOwner(int ownerID) {
		List<UserGroup> groups = groupDAO.getGroupsByOwner(ownerID);
		for (UserGroup group : groups) {
			groupDAO.deleteAllUsersFromGroup(group.getGr_id());
			groupDAO.deleteGroup(group.getGr_id());
		}	
	}

}
