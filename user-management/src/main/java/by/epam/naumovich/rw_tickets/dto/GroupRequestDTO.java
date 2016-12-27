package by.epam.naumovich.rw_tickets.dto;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

public class GroupRequestDTO {

	private GroupRequest request;
	private User sender;
	private User receiver;
	private UserGroup group;
	
	public GroupRequest getRequest() {
		return request;
	}
	public void setRequest(GroupRequest request) {
		this.request = request;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public UserGroup getGroup() {
		return group;
	}
	public void setGroup(UserGroup group) {
		this.group = group;
	}
}
