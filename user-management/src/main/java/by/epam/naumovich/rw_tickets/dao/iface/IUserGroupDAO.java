package by.epam.naumovich.rw_tickets.dao.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.dao.exception.DAOException;
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
	 * @throws DAOException
	 */
	int addUserGroup(UserGroup group) throws DAOException;
	
	/**
	 * Updates the user group in the data source
	 * 
	 * @param id user group's id
	 * @param updUserGroup updated user group entity
	 * @throws DAOException
	 */
	void updateUserGroup(int id, UserGroup updGroup) throws DAOException;
	
	/**
	 * Deletes user group entity from the data source
	 * 
	 * @param id user group's id
	 * 
	 * @throws DAOException
	 */
	void deleteUserGroup(int id) throws DAOException;
	
	/**
	 * Gets user group by its ID from the data source
	 * 
	 * @param id user group's ID
	 * @return found UserGroup entity
	 * @throws DAOException
	 */
	UserGroup getUserGroupById(int id) throws DAOException;
	
	/**
	 * Adds user to the user group
	 * 
	 * @param userID ID of the user
	 * @param groupID ID of the user group
	 * @return number of rows changed
	 * @throws DAOException
	 */
	int addUserToGroup(int userID, int groupID) throws DAOException;
	
	/**
	 * Deletes user from the user group
	 * 
	 * @param userID ID of the user
	 * @param groupID ID of the user group
	 * @throws DAOException
	 */
	void deleteUserFromGroup(int userID, int groupID) throws DAOException;
	
	/**
	 * Gets list of user groups by his ID
	 * 
	 * @param userID ID of the user
	 * @return list of user groups
	 * @throws DAOException
	 */
	List<UserGroup> getUserGroupsByUser(int userID) throws DAOException;
	

}
