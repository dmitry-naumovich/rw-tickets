package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;

/**
 * Defines methods that receive parameters from ServiceFacade layer, verify them, and then either pass them to the DAO layer 
 * or additionally does some logic possibly getting some objects or primitive values back and passing them further back to the ServiceFacade.
 * This class operates with the UserGroup entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public interface IGroupService {

	int addGroup(UserGroup group) throws ServiceException;
	void updateGroup(UserGroup group) throws ServiceException;
	void deleteGroup(int groupID) throws ServiceException;
	
	UserGroup getGroupByID(int id) throws ServiceException;
	String getGroupNameByID(int id) throws ServiceException;
	List<UserGroup> getGroupsByUser(int userID) throws ServiceException;
	
    void addGroupMember(int userID, int groupID) throws ServiceException;
	void removeGroupMember(int userID, int groupID) throws ServiceException;
	
	void deleteAllGroupsByOwner(int ownerID) throws ServiceException;	
}
