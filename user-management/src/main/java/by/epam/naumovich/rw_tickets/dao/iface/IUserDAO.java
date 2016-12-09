package by.epam.naumovich.rw_tickets.dao.iface;

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
	
	
}
