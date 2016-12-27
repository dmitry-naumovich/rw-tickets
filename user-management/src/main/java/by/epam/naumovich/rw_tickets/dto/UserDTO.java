package by.epam.naumovich.rw_tickets.dto;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

public class UserDTO {

	private User user;
	private List<UserGroup> userGroups;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserGroup> getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
	
}
