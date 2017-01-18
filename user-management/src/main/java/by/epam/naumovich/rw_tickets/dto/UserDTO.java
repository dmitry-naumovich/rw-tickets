package by.epam.naumovich.rw_tickets.dto;

import java.util.Map;

import by.epam.naumovich.rw_tickets.entity.User;

/**
 * Data Transfer Object which combines User entity with City and Country names and the list of groups which user is involved into.
 * It's used by the Service Facade layer which maps simple entities into this complex object which could be transfered to application interface.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class UserDTO {

	private User user;
	private String country;
	private String city;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
