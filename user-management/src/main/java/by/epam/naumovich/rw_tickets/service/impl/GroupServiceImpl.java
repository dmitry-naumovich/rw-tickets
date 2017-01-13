package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IGroupService;
import by.epam.naumovich.rw_tickets.service.util.Validator;

public class GroupServiceImpl implements IGroupService {

	public static final String INVALID_INPUT_PARAMS = "Invalid input parameters passed into method";
	private IGroupDAO groupDAO;
	
	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	@Override
	public int addGroup(UserGroup group) throws ServiceException {
		if (!Validator.validateNewUserGroup(group)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		int id = groupDAO.addGroup(group);
		groupDAO.addGroupMember(group.getOwner_id(), id);
		return id;
	}

	@Override
	public void updateGroup(UserGroup group) throws ServiceException {
		if (!Validator.validateExistingUserGroup(group)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		groupDAO.updateGroup(group.getGr_id(), group);
	}

	@Override
	public void deleteGroup(int groupID) throws ServiceException {
		if (!Validator.validateIds(groupID)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		groupDAO.removeAllGroupMembers(groupID);
		groupDAO.deleteGroup(groupID);
	}

	@Override
	public UserGroup getGroupByID(int id) throws ServiceException {
		if (!Validator.validateIds(id)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return groupDAO.getGroupById(id);
		
	}

	@Override
	public String getGroupNameByID(int id) throws ServiceException {
		if (!Validator.validateIds(id)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return groupDAO.getGroupNameById(id);
	}

	@Override
	public List<UserGroup> getGroupsByUser(int userID) throws ServiceException {
		if (!Validator.validateIds(userID)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return groupDAO.getGroupsByUser(userID);
	}

	@Override
	public void addGroupMember(int userID, int groupID) throws ServiceException {
		if (!Validator.validateIds(userID, groupID)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		groupDAO.addGroupMember(userID, groupID);
		
	}

	@Override
	public void removeGroupMember(int userID, int groupID) throws ServiceException {
		if (!Validator.validateIds(userID, groupID)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		groupDAO.removeGroupMember(userID, groupID);
	}
		

	@Override
	public void deleteAllGroupsByOwner(int ownerID) throws ServiceException {
		if (!Validator.validateIds(ownerID)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		List<UserGroup> groups = groupDAO.getGroupsByOwner(ownerID);
		if (!groups.isEmpty()) {
			for (UserGroup group : groups) {
				groupDAO.removeAllGroupMembers(group.getGr_id());
				groupDAO.deleteGroup(group.getGr_id());
			}
		}
			
	}

}
