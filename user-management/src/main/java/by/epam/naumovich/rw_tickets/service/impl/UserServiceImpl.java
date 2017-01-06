package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IUserService;
import by.epam.naumovich.rw_tickets.service.util.ExceptionMessages;
import by.epam.naumovich.rw_tickets.service.util.USER_SORT_TYPE;

public class UserServiceImpl implements IUserService {

	private IUserDAO userDAO;
	private IGroupDAO groupDAO;
	
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	@Override
	public int addUser(User user) throws ServiceException {
		try {
			return userDAO.addUser(user);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void updateUser(User updUser) throws ServiceException {
		try {
			userDAO.updateUser(updUser.getId(), updUser);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void deleteUser(int id) throws ServiceException {
		try {
			groupDAO.removeUserFromAllGroups(id);
			List<UserGroup> groups = groupDAO.getGroupsByOwner(id);
			for (UserGroup group : groups) {
				groupDAO.removeAllGroupMembers(group.getGr_id());
				groupDAO.deleteGroup(group.getGr_id());
			}
			userDAO.deleteUser(id);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public User getUserById(int id) throws ServiceException {
		try {
			return userDAO.getUserById(id);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public User getUserByLogin(String login) throws ServiceException {
		try {
			return userDAO.getUserByLogin(login);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}
	

	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			return userDAO.getAllUsers();
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public List<User> getAllGroupMembers(int groupID) throws ServiceException {
		try {
			return userDAO.getAllGroupMembers(groupID);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public String getLoginById(int id) throws ServiceException {
		try {
			return userDAO.getLoginById(id);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void authenticateByLogin(String login, String pass) throws ServiceException {
		try {
			User user = userDAO.getUserByLogin(login);
			if (user == null) {
				throw new ServiceException(ExceptionMessages.LOGIN_NOT_REGISTRATED);
			} else {
				if (!pass.equals(user.getPwd())) {
					throw new ServiceException(ExceptionMessages.WRONG_PASSWORD);
				}
			}
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void authenticateByEmail(String email, String pass) throws ServiceException {
		try {
			User user = userDAO.getUserByEmail(email);
			if (user == null) {
				throw new ServiceException(ExceptionMessages.EMAIL_NOT_REGISTRATED);
			} else {
				if (!pass.equals(user.getPwd())) {
					throw new ServiceException(ExceptionMessages.WRONG_PASSWORD);
				}
			}
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}
	
	@Override
	public List<User> getAllUsersSorted(USER_SORT_TYPE type) throws ServiceException {
		try {
			return userDAO.getAllUsersSorted(type.toString().toLowerCase());
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
		
	}

	@Override
	public List<User> findUsersByName(String name) throws ServiceException {
		try {
			return userDAO.getUsersByName(name);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public List<User> findUsersBySurname(String surname) throws ServiceException {
		try {
			return userDAO.getUsersBySurname(surname);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public List<User> findUsersByCountry(String country) throws ServiceException {
		try {
			return userDAO.getUsersByCountry(country);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public List<User> findUsersByCity(String city) throws ServiceException {
		try {
			return userDAO.getUsersByCity(city);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}
}
