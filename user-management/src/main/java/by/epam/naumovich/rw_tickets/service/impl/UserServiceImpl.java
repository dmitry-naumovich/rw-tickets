package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.iface.IUserService;
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
	public int addUser(User user) {
		return userDAO.addUser(user);
	}

	@Override
	public void updateUser(User updUser) {
		userDAO.updateUser(updUser.getId(), updUser);
	}

	@Override
	public void deleteUser(int id) {
		groupDAO.removeUserFromAllGroups(id);
		List<UserGroup> groups = groupDAO.getGroupsByOwner(id);
		for (UserGroup group : groups) {
			groupDAO.removeAllGroupMembers(group.getGr_id());
			groupDAO.deleteGroup(group.getGr_id());
		}
		userDAO.deleteUser(id);

	}

	@Override
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	public User getUserByLogin(String login) {
		return userDAO.getUserByLogin(login);
	}
	

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public List<User> getAllGroupMembers(int groupID) {
		return userDAO.getAllGroupMembers(groupID);
	}

	@Override
	public String getPasswordByLogin(String login) {
		return userDAO.getPasswordByLogin(login);
	}

	
	@Override
	public String getPasswordByEmail(String email) {
		return userDAO.getPasswordByEmail(email);
	}
	
	@Override
	public String getLoginById(int id) {
		return userDAO.getLoginById(id);
	}

	@Override
	public List<User> getAllUsersSorted(USER_SORT_TYPE type) {
		switch (type) {
		case NAME:
			return userDAO.getAllUsersSortByName();
		case SURNAME:
			return userDAO.getAllUsersSortBySurname();
		case LOGIN:
			return userDAO.getAllUsersSortByLogin();
		case EMAIL:
			return userDAO.getAllUsersSortByEmail();
		case COUNTRY:
			return userDAO.getAllUsersSortByCountry();
		case CITY:
			return userDAO.getAllUsersSortByCity();
		case BIRTHDATE:
			return userDAO.getAllUsersSortByBirthdate();
		case ADDRESS:
			return userDAO.getAllUsersSortByAddress();
		default:
			return userDAO.getAllUsers();
		}
	}

	@Override
	public List<User> findUsersByName(String name) {
		return userDAO.getUsersByName(name);
	}

	@Override
	public List<User> findUsersBySurname(String surname) {
		return userDAO.getUsersBySurname(surname);
	}

	@Override
	public List<User> findUsersByCountry(String country) {
		return userDAO.getUsersByCountry(country);
	}

	@Override
	public List<User> findUsersByCity(String city) {
		return userDAO.getUsersByCity(city);
	}
}
