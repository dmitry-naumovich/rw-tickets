package by.epam.naumovich.rw_tickets.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IUserService;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;
import by.epam.naumovich.rw_tickets.service.search.impl.AndCriterion;
import by.epam.naumovich.rw_tickets.service.search.impl.EmailCriterion;
import by.epam.naumovich.rw_tickets.service.search.impl.LoginCriterion;
import by.epam.naumovich.rw_tickets.service.search.impl.NameCriterion;
import by.epam.naumovich.rw_tickets.service.util.USER_SORT_TYPE;
import by.epam.naumovich.rw_tickets.service.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IUserService implementation which validates input parameters using the Validator class and invokes the methods from DAO 
 * which is injected into this class by the Spring Framework IoC framework.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 * @see Validator
 */
@Service
public class UserServiceImpl implements IUserService {

	private static final String INVALID_INPUT_PARAMS = "Invalid input parameters passed into method";

	private final IUserDAO userDAO;
	private final IGroupDAO groupDAO;

	@Autowired
	public UserServiceImpl(IUserDAO userDAO, IGroupDAO groupDAO) {
		this.userDAO = userDAO;
		this.groupDAO = groupDAO;
	}

	@Override
	public int addUser(User user) throws ServiceException {
		if (!Validator.validateNewUser(user)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return userDAO.addUser(user);
		
	}

	@Override
	public void updateUser(User updUser) throws ServiceException {
		if (!Validator.validateExistingUser(updUser)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		userDAO.updateUser(updUser.getId(), updUser);
		
	}

	@Override
	public void deleteUser(int id) throws ServiceException {
		if (!Validator.validateIds(id)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		groupDAO.removeUserFromAllGroups(id);

		groupDAO.getGroupsByOwner(id).forEach(group -> {
			groupDAO.removeAllGroupMembers(group.getId());
			groupDAO.deleteGroup(group.getId());
		});
		userDAO.deleteUser(id);
	}

	@Override
	public User getUserById(int id) throws ServiceException {
		if (!Validator.validateIds(id)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		User user = userDAO.getUserById(id);
		if (user == null) {
		    throw new InvalidParameterException("No user found with id " + id);
        }
		return user;
	}

	@Override
	public User getUserByLogin(String login) throws ServiceException {
		if (!Validator.validateStrings(login)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		User user = userDAO.getUserByLogin(login);
		if (user == null) {
		    throw new InvalidParameterException("No user found with login " + login);
        }
		return user;
	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		return userDAO.getAllUsers();
	}

	@Override
	public List<User> getAllGroupMembers(int groupID) throws ServiceException {
		if (!Validator.validateIds(groupID)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return userDAO.getAllGroupMembers(groupID);
		
	}

	@Override
	public String getLoginById(int id) throws ServiceException {
		if (!Validator.validateIds(id)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		String login = userDAO.getLoginById(id);
		if (login == null) {
		    throw new InvalidParameterException("No user found by id " + id);
        }
        return login;
		
	}

	@Override
	public boolean authenticateByLogin(String login, String pass) throws ServiceException {
		if (!Validator.validateStrings(login, pass)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		User user = userDAO.getUserByLogin(login);
		if (user == null) {
            throw new InvalidParameterException("No user found with login " + login);
        }
		return pass.equals(user.getPwd());
	}

	@Override
	public boolean authenticateByEmail(String email, String pass) throws ServiceException {
		if (!Validator.validateStrings(email, pass)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		User user = userDAO.getUserByEmail(email);
        if (user == null) {
            throw new InvalidParameterException("No user found with email " + email);
        }
        return pass.equals(user.getPwd());
	}
	
	@Override
	public List<User> getAllUsersSorted(USER_SORT_TYPE type) throws ServiceException {
		if (type == null) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return userDAO.getAllUsersSorted(type.toString().toLowerCase());
	}

	@Override
	public List<User> searchForUsers(String name, String login, String email, String countryCode, String cityCode) throws ServiceException {
		List<User> users;
		
		if (cityCode != null) {
			users = userDAO.getUsersByCity(cityCode, countryCode);
		} else if (countryCode != null) {
			users = userDAO.getUsersByCountry(countryCode);
		} else {
			users = userDAO.getAllUsers();
		}
		
		List<UserCriterion> criteria = new ArrayList<>();
		if (name != null) {
			criteria.add(new NameCriterion(name));
		}
		if (login != null) {
			criteria.add(new LoginCriterion(login));
		}
		if (email != null) {
			criteria.add(new EmailCriterion(email));
		}
		
		if (criteria.isEmpty()) {
			return users;
		} else if (criteria.size() == 1) {
			return criteria.get(0).meetCriterion(users);
		} else {
			UserCriterion andCrit = new AndCriterion(criteria);
			return andCrit.meetCriterion(users);
		}
	}

	@Override
	public List<User> getUsersByCountry(String countryCode) throws ServiceException {
		if (!Validator.validateStrings(countryCode)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return userDAO.getUsersByCountry(countryCode);
	}

	@Override
	public List<User> getUsersByCity(String cityCode, String countryCode) throws ServiceException {
		if (!Validator.validateStrings(cityCode, countryCode)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return userDAO.getUsersByCity(cityCode, countryCode);
	}
}
