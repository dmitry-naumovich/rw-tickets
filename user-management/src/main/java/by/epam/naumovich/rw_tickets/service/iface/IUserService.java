package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.util.USER_SORT_TYPE;

/**
 * Defines methods that receive parameters from Command implementations, verify them, construct necessary entities if needed 
 * and then pass them to the DAO layer, possibly getting some objects or primitive values back and passing them further back to the commands.
 * Works with the User entity mostly.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public interface IUserService {
	
	int addUser(User user) throws ServiceException;
	void updateUser(User updUser) throws ServiceException;
	void deleteUser(int id) throws ServiceException;
	
	User getUserById(int id) throws ServiceException;
	User getUserByLogin(String login) throws ServiceException;
	List<User> getAllUsers() throws ServiceException;
	List<User> getAllGroupMembers(int groupID) throws ServiceException;
	
	boolean authenticateByLogin(String login, String pass) throws ServiceException;
	boolean authenticateByEmail(String email, String pass) throws ServiceException;
	String getLoginById(int id) throws ServiceException;
	
	List<User> searchForUsers(String name, String login, String email, String countryCode, String cityCode) throws ServiceException;
	List<User> getUsersByCountry(String countryCode) throws ServiceException;	
	List<User> getUsersByCity(String cityCode, String countryCode) throws ServiceException;	
	
	List<User> getAllUsersSorted(USER_SORT_TYPE type) throws ServiceException;	
}