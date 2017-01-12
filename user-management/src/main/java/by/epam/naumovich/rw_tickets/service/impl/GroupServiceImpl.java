package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.exception.InvalidInputServiceException;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IGroupService;
import by.epam.naumovich.rw_tickets.service.util.ExceptionMessages;

public class GroupServiceImpl implements IGroupService {

	private IGroupDAO groupDAO;
	
	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	@Override
	public int addGroup(UserGroup group) throws ServiceException {
		if (group == null) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			int id = groupDAO.addGroup(group);
			groupDAO.addGroupMember(group.getOwner_id(), id);
			return id;
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.USER_NOT_ADDED);
		} catch (Exception e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void updateGroup(UserGroup group) throws ServiceException {
		if (group == null) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			groupDAO.updateGroup(group.getGr_id(), group);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.USER_NOT_UPDATED);
		}
		
	}

	@Override
	public void deleteGroup(int groupID) throws ServiceException {
		if (groupID <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try { 
			groupDAO.removeAllGroupMembers(groupID);
			groupDAO.deleteGroup(groupID);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
		
	}

	@Override
	public UserGroup getGroupByID(int id) throws ServiceException {
		if (id <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			return groupDAO.getGroupById(id);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		} catch (IndexOutOfBoundsException e) {
			throw new ServiceException(ExceptionMessages.GROUP_NOT_FOUND);
		}
	}

	@Override
	public String getGroupNameByID(int id) throws ServiceException {
		if (id <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			return groupDAO.getGroupNameById(id);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public List<UserGroup> getGroupsByUser(int userID) throws ServiceException {
		if (userID <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			return groupDAO.getGroupsByUser(userID);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void addGroupMember(int userID, int groupID) throws ServiceException {
		if (userID <= 0 || groupID <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			groupDAO.addGroupMember(userID, groupID);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void removeGroupMember(int userID, int groupID) throws ServiceException {
		if (userID <= 0 || groupID <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			groupDAO.removeGroupMember(userID, groupID);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void deleteAllGroupsByOwner(int ownerID) throws ServiceException {
		if (ownerID <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			List<UserGroup> groups = groupDAO.getGroupsByOwner(ownerID);
			for (UserGroup group : groups) {
				groupDAO.removeAllGroupMembers(group.getGr_id());
				groupDAO.deleteGroup(group.getGr_id());
			}	
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		} catch (Exception e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
		
	}

}
