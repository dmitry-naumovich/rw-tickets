package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.UserGroup;

/**
* Defines methods that receive parameters from Command implementations, verify them, construct necessary entities if needed 
* and then pass them to the DAO layer, possibly getting some objects or primitive values back and passing them further back to the commands.
* Works with the UserGroup entity mostly.
* 
* @author Dmitry Naumovich
* @version 1.0
*/
public interface IGroupService {

	public int addGroup(UserGroup group);
	public void updateGroup(UserGroup group);
	public void deleteGroup(int groupID);
	
	UserGroup getGroupByID(int id);
	String getGroupNameByID(int id);
	List<UserGroup> getGroupsByUser(int userID);
	
    void addUserToGroup(int userID, int groupID);
	void deleteUserFromGroup(int userID, int groupID);
	
	void deleteAllGroupsByOwner(int ownerID);	
}
