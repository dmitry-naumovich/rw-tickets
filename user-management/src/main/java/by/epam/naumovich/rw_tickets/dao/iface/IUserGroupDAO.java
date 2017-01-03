package by.epam.naumovich.rw_tickets.dao.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.UserGroup;

/**
 * Defines methods for implementing in the DAO layer for the UserGroup entity.
 * 
 * @author Dzmitry Naumovich
 * @version 1.0
 */
public interface IUserGroupDAO {
	
	/**
	 * Adds new user group to the data source
	 * 
	 * @param group new user group entity
	 * @return id of the newly added user group or 0 if it was not added
	 */
	int addUserGroup(UserGroup group);
	
	/**
	 * Updates the user group in the data source
	 * 
	 * @param id user group's id
	 * @param updUserGroup updated user group entity
	 */
	void updateUserGroup(int id, UserGroup updGroup);
	
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
	UserGroup getUserGroupById(int id);
	
	/**
	 * Gets group ID by its name and owner from the data source
	 * 
	 * @param name group name
	 * @param ownerID group owner's ID
	 * @return id of the group
	 */
	int getGroupIdByNameAndOwner(String name, int ownerID);
	
	/**
	 * Adds user to the user group
	 * 
	 * @param userID ID of the user
	 * @param groupID ID of the user group
	 */
	void addUserToGroup(int userID, int groupID);
	
	/**
	 * Deletes user from the user group
	 * 
	 * @param userID ID of the user
	 * @param groupID ID of the user group
	 */
	void deleteUserFromGroup(int userID, int groupID);
	
	/**
	 * Deletes all users from the specified group
	 * 
	 * @param groupID - ID of the group
	 */
	void deleteAllUsersFromGroup(int groupID);
	
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
	void deleteUserFromAllGroups(int userID);
	
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
