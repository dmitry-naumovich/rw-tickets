package by.epam.naumovich.rw_tickets.dao.iface;

import java.util.List;

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
	 * @return the list containing all users
	 * @throws DAOException
	 */
	List<User> getAllUsers() throws DAOException;
	
	/**
	 * Gets all users from the concrete user group from the data source
	 * 
	 * @param groupID ID of the group
	 * @return the list containing all users from the concrete group
	 * @throws DAOException
	 */
	List<User> getAllGroupUsers(int groupID) throws DAOException;
	
	/**
	 * Returns the password of the user by his login
	 * 
	 * @param login user login
	 * @return string password or null if it was not found
	 * @throws DAOException
	 */
	String getPasswordByLogin(String login) throws DAOException;
	
	/**
	 * Gets user password by his email from the data source
	 * 
	 * @param email user email
	 * @return string password or null if it was not found
	 * @throws DAOException
	 */
	String getPasswordByEmail(String email) throws DAOException;
	
	/**
	 * Gets users from the data source by specified name
	 * 
	 * @param name user name
	 * @return the list containing users with the specified name
	 * @throws DAOException
	 */
	List<User> getUsersByName(String name) throws DAOException;
	
	/**
	 * Gets users from the data source by specified surname
	 * 
	 * @param surname user surname
	 * @return the list containing users with the specified surname
	 * @throws DAOException
	 */
	List<User> getUsersBySurname(String surname) throws DAOException;
	
	/**
	 * Gets users from the data source by specified country
	 * 
	 * @param country country
	 * @return the list containing users from the specified country
	 * @throws DAOException
	 */
	List<User> getUsersByCountry(String country) throws DAOException;
	
	/**
	 * Gets users from the data source by specified city
	 * 
	 * @param city city
	 * @return the list containing users from the specified city
	 * @throws DAOException
	 */
	List<User> getUsersByCity(String city) throws DAOException;
	
	/**
	 * Gets all users from the data source sorted by name
	 * 
	 * @return the list containing all users sorted by name
	 * @throws DAOException
	 */
	List<User> getAllUsersSortByName() throws DAOException;
	
	/**
	 * Gets all users from the data source sorted by surname
	 * 
	 * @return the list containing all users sorted by surname
	 * @throws DAOException
	 */
	List<User> getAllUsersSortBySurname() throws DAOException;
	
	/**
	 * Gets all users from the data source sorted by email
	 * 
	 * @return the list containing all users sorted by email
	 * @throws DAOException
	 */
	List<User> getAllUsersSortByEmail() throws DAOException;
	
	/**
	 * Gets all users from the data source sorted by country
	 * 
	 * @return the list containing all users sorted by country
	 * @throws DAOException
	 */
	List<User> getAllUsersSortByCountry() throws DAOException;
	
	/**
	 * Gets all users from the data source sorted by city
	 * 
	 * @return the list containing all users sorted by city
	 * @throws DAOException
	 */
	List<User> getAllUsersSortByCity() throws DAOException;

	/**
	 * Gets all users from the data source sorted by address
	 * 
	 * @return the list containing all users sorted by address
	 * @throws DAOException
	 */
	List<User> getAllUsersSortByAddress() throws DAOException;
	
	/**
	 * Gets all users from the data source sorted by login
	 * 
	 * @return the list containing all users sorted by login
	 * @throws DAOException
	 */
	List<User> getAllUsersSortByLogin() throws DAOException;
	
	/**
	 * Gets all users from the data source sorted by birth date
	 * 
	 * @return the list containing all users sorted by birth date
	 * @throws DAOException
	 */
	List<User> getAllUsersSortByBirthdate() throws DAOException;


}
