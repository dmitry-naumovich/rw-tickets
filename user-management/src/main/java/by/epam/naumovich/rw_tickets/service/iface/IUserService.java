package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
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
	
	int addUser(User user);
	void updateUser(int id, User updUser);
	void deleteUser(int id);
	
	User getUserById(int id);
	User getUserByLogin(String login);
	List<User> getAllUsers();
	List<User> getAllGroupUsers(int groupID);
	String getPasswordByLogin(String login);
	String getPasswordByEmail(String email);
	
	List<User> findUsersByName(String name);
	List<User> findUsersBySurname(String surname);	
	List<User> findUsersByCountry(String country);	
	List<User> findUsersByCity(String city);	
	
	List<User> getAllUsersSorted(USER_SORT_TYPE type);	
}