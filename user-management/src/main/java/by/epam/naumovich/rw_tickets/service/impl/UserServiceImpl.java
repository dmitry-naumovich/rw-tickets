package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.iface.IUserService;
import by.epam.naumovich.rw_tickets.service.util.USER_SORT_TYPE;

public class UserServiceImpl implements IUserService {

	private IUserDAO userDAO;
	
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public int addUser(User user) {
		int id = 0;
		try {
			id = userDAO.addUser(user);
		} catch (Exception e) {
			// proceed exceptions from dao
		}
		return id;
	}

	@Override
	public void updateUser(User updUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllGroupUsers(int groupID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasswordByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsersSorted(USER_SORT_TYPE type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersByCountry(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

}
