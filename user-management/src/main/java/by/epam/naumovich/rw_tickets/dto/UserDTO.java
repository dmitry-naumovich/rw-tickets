package by.epam.naumovich.rw_tickets.dto;

import java.util.Map;

import by.epam.naumovich.rw_tickets.entity.User;

public class UserDTO {

	private User user;
	private Map<Integer, String> userGroups;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Map<Integer, String> getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(Map<Integer, String> userGroups) {
		this.userGroups = userGroups;
	}
	
}
