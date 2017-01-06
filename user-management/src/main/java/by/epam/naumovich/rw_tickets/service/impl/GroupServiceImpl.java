package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.iface.IGroupService;

public class GroupServiceImpl implements IGroupService {

	private IGroupDAO groupDAO;
	
	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	@Override
	public int addGroup(UserGroup group) {
		int id = groupDAO.addGroup(group);
		groupDAO.addGroupMember(group.getOwner_id(), id);
		return id;
	}

	@Override
	public void updateGroup(UserGroup group) {
		groupDAO.updateGroup(group.getGr_id(), group);
		
	}

	@Override
	public void deleteGroup(int groupID) {
		groupDAO.removeAllGroupMembers(groupID);
		groupDAO.deleteGroup(groupID);
	}

	@Override
	public UserGroup getGroupByID(int id) {
		return groupDAO.getGroupById(id);
	}

	@Override
	public String getGroupNameByID(int id) {
		return groupDAO.getGroupNameById(id);
	}

	@Override
	public List<UserGroup> getGroupsByUser(int userID) {
		return groupDAO.getGroupsByUser(userID);
	}

	@Override
	public void addGroupMember(int userID, int groupID) {
		groupDAO.addGroupMember(userID, groupID);
	}

	@Override
	public void removeGroupMember(int userID, int groupID) {
		groupDAO.removeGroupMember(userID, groupID);
	}

	@Override
	public void deleteAllGroupsByOwner(int ownerID) {
		List<UserGroup> groups = groupDAO.getGroupsByOwner(ownerID);
		for (UserGroup group : groups) {
			groupDAO.removeAllGroupMembers(group.getGr_id());
			groupDAO.deleteGroup(group.getGr_id());
		}	
	}

}
