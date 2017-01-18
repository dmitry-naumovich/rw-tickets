package by.epam.naumovich.rw_tickets.dto;

import java.util.Map;

import by.epam.naumovich.rw_tickets.entity.UserGroup;

/**
 * Data Transfer Object which combines UserGroup entity with group owner's login and the map of members' 'id-login' pairs.
 * It's used by the Service Facade layer which maps simple entities into this complex object which could be transfered to application interface.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class UserGroupDTO {

	private UserGroup group;
	private Map<Integer, String> members;
	private String ownerLogin;
	
	public UserGroup getGroup() {
		return group;
	}
	public void setGroup(UserGroup group) {
		this.group = group;
	}
	public Map<Integer, String> getMembers() {
		return members;
	}
	public void setMembers(Map<Integer, String> members) {
		this.members = members;
	}
	public String getOwnerLogin() {
		return ownerLogin;
	}
	public void setOwnerLogin(String ownerLogin) {
		this.ownerLogin = ownerLogin;
	}
	
}
