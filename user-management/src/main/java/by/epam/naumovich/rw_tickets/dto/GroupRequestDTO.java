package by.epam.naumovich.rw_tickets.dto;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;

/**
 * Data Transfer Object which combines GroupRequest entity with Request's sender and receiver logins and group's name.
 * It's used by the Service Facade layer which maps simple entities into this complex object which could be transfered to application interface.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class GroupRequestDTO {

	private GroupRequest request;
	private String sender;
	private String receiver;
	private String group;
	
	public GroupRequest getRequest() {
		return request;
	}
	public void setRequest(GroupRequest request) {
		this.request = request;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
}
