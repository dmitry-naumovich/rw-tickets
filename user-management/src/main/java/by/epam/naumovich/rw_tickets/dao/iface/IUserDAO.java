package by.epam.naumovich.rw_tickets.dao.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;

/**
 * Defines methods to be implemented in the DAO layer for User entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public interface IUserDAO {

	/**
	 * Adds new user to the data source
	 * 
	 * @param user new user entity
	 * @return id of the newly added user or 0 if it was not added
	 */
	int addUser(User user);
	
	/**
	 * Updates the user in the data source
	 * 
	 * @param id user id
	 * @param updUser updated user entity
	 */
	void updateUser(int id, User updUser);
	
	/**
	 * Deletes user entity from the data source
	 * 
	 * @param id user id
	 */
	void deleteUser(int id);
	
	/**
	 * Gets user by his ID from the data source
	 * 
	 * @param id user ID
	 * @return found User entity
	 */
	User getUserById(int id);
	
	/**
	 * Gets user by his login from the data source
	 * 
	 * @param login user login
	 * @return found User entity
	 */
	User getUserByLogin(String login);
	
	/**
	 * Gets user by his email from the data source
	 * 
	 * @param email user's email
	 * @return found User entity
	 */
	User getUserByEmail(String email);
	
	/**
	 * Gets all users from the data source
	 * 
	 * @return the list containing all users
	 */
	List<User> getAllUsers();
	
	/**
	 * Gets all users from the concrete user group from the data source
	 * 
	 * @param groupID ID of the group
	 * @return the list containing all users from the concrete group
	 */
	List<User> getAllGroupMembers(int groupID);
	
	/**
	 * Returns the id of the user by his login
	 * 
	 * @param login user login
	 * @return id user id or 0 if it was not found
	 */
	int getIdByLogin(String login);
	
	/**
	 * Returns user login by his ID
	 * 
	 * @param id user ID
	 * @return user login
	 */
	String getLoginById(int id);
	
	/**
	 * Returns the password of the user by his login
	 * 
	 * @param login user login
	 * @return string password or null if it was not found
	 */
	String getPasswordByLogin(String login);
	
	/**
	 * Gets user password by his email from the data source
	 * 
	 * @param email user email
	 * @return string password or null if it was not found
	 */
	String getPasswordByEmail(String email);
	
	/**
	 * Gets users from the data source by specified country
	 * 
	 * @param countryCode country code
	 * @return the list containing users from the specified country
	 */
	List<User> getUsersByCountry(String countryCode);
	
	/**
	 * Gets users from the data source by specified city and country
	 * 
	 * @param cityCode city code
	 * @param countryCode country code
	 * @return the list containing users from the specified city
	 */
	List<User> getUsersByCity(String cityCode, String countryCode);
	
	/**
	 * Gets all users from the data source sorted by column specified
	 * 
	 * @param field - the field by which the list will be sorted
	 * @return the list containing all users sorted by field specified
	 */
	List<User> getAllUsersSorted(String columnName);
}
