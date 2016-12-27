package by.epam.naumovich.rw_tickets.dto;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

public class UserGroupDTO {

	private UserGroup group;
	private List<User> users;
	private User owner;
	
	public UserGroup getGroup() {
		return group;
	}
	public void setGroup(UserGroup group) {
		this.group = group;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
}
