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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateGroup(UserGroup group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroup(UserGroup group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserGroup getGroupByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserGroup> getAllGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserGroup> getGroupsByUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserToGroup(int userID, int groupID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserFromGroup(int userID, int groupID) {
		// TODO Auto-generated method stub
		
	}

}
