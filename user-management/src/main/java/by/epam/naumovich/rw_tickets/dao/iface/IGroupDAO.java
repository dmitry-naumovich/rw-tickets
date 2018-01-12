package by.epam.naumovich.rw_tickets.dao.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.UserGroup;

/**
 * Defines methods to be implemented in the DAO layer for UserGroup entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public interface IGroupDAO {
	
	/**
	 * Adds new user group to the data source
	 * 
	 * @param group new user group entity
	 * @return id of the newly added user group or 0 if it was not added
	 */
	int addGroup(UserGroup group);
	
	/**
	 * Updates the user group in the data source
	 * 
	 * @param id user group's id
	 * @param updGroup updated user group entity
	 */
	void updateGroup(int id, UserGroup updGroup);
	
	/**
	 * Deletes user group entity from the data source
	 * 
	 * @param id user group's id
	 */
	void deleteGroup(int id);
	
	/**
	 * Gets user group by its ID from the data source
	 * 
	 * @param id user group's ID
	 * @return found UserGroup entity
	 */
	UserGroup getGroupById(int id);
	
	/**
	 * Gets group ID by its name and owner from the data source
	 * 
	 * @param name group name
	 * @param ownerID group owner's ID
	 * @return id of the group
	 */
	int getGroupIdByNameAndOwner(String name, int ownerID);
	
	/**
	 * Gets group's name by its ID
	 * 
	 * @param id group's ID
	 * @return group's name
	 */
	String getGroupNameById(int id);
	
	/**
	 * Adds user to the user group
	 * 
	 * @param userID ID of the user
	 * @param groupID ID of the user group
	 */
	void addGroupMember(int userID, int groupID);
	
	/**
	 * Deletes user from the user group
	 * 
	 * @param userID ID of the user
	 * @param groupID ID of the user group
	 */
	void removeGroupMember(int userID, int groupID);
	
	/**
	 * Deletes all users from the specified group
	 * 
	 * @param groupID - ID of the group
	 */
	void removeAllGroupMembers(int groupID);
	
	/**
	 * Deletes all groups which are owned by the user with the specified ID
	 * 
	 * @param ownerID ID of the owner
	 */
	void deleteAllGroupsByOwner(int ownerID);
	
	/**
	 * Deletes user from all groups which he involves in
	 * 
	 * @param userID ID of the user
	 */
	void removeUserFromAllGroups(int userID);
	
	/**
	 * Gets list of user groups by his ID
	 * 
	 * @param userID ID of the user
	 * @return list of user groups
	 */
	List<UserGroup> getGroupsByUser(int userID);
	
	/**
	 * Gets list of groups by their owner's ID
	 * 
	 * @param ownerID ID of the owner
	 * @return list of user groups
	 */
	List<UserGroup> getGroupsByOwner(int ownerID);
}
