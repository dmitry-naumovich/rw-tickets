package by.epam.naumovich.rw_tickets.dao.iface;

import java.util.Set;

import by.epam.naumovich.rw_tickets.dao.exception.DAOException;
import by.epam.naumovich.rw_tickets.entity.User;

/**
 * Defines methods for implementing in the DAO layer for the User entity.
 * 
 * @author Dzmitry Naumovich
 * @version 1.0
 */
public interface IUserDAO {

	/**
	 * Adds new user to the data source
	 * 
	 * @param user new user entity
	 * @return id of the newly added user or 0 if it was not added
	 * @throws DAOException
	 */
	int addUser(User user) throws DAOException;
	
	/**
	 * Updates the user in the data source
	 * 
	 * @param id user id
	 * @param updUser updated user entity
	 * @throws DAOException
	 */
	void updateUser(int id, User updUser) throws DAOException;
	
	/**
	 * Deletes user entity from the data source
	 * 
	 * @param id user id
	 * 
	 * @throws DAOException
	 */
	void deleteUser(int id) throws DAOException;
	
	/**
	 * Gets user by his ID from the data source
	 * 
	 * @param id user ID
	 * @return found User entity
	 * @throws DAOException
	 */
	User getUserById(int id) throws DAOException;
	
	/**
	 * Gets user by his login from the data source
	 * 
	 * @param login user login
	 * @return found User entity
	 * @throws DAOException
	 */
	User getUserByLogin(String login) throws DAOException;
	
	/**
	 * Gets all users from the data source
	 * 
	 * @return the set containing all users
	 * @throws DAOException
	 */
	Set<User> getAllUsers() throws DAOException;
	
}
