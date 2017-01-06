package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;

/**
* Defines methods that receive parameters from Command implementations, verify them, construct necessary entities if needed 
* and then pass them to the DAO layer, possibly getting some objects or primitive values back and passing them further back to the commands.
* Works with the UserGroup entity mostly.
* 
* @author Dmitry Naumovich
* @version 1.0
*/
public interface IGroupService {

	public int addGroup(UserGroup group) throws ServiceException;
	public void updateGroup(UserGroup group) throws ServiceException;
	public void deleteGroup(int groupID) throws ServiceException;
	
	UserGroup getGroupByID(int id) throws ServiceException;
	String getGroupNameByID(int id) throws ServiceException;
	List<UserGroup> getGroupsByUser(int userID) throws ServiceException;
	
    void addGroupMember(int userID, int groupID) throws ServiceException;
	void removeGroupMember(int userID, int groupID) throws ServiceException;
	
	void deleteAllGroupsByOwner(int ownerID) throws ServiceException;	
}
