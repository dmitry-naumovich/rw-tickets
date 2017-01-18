package by.epam.naumovich.rw_tickets.service.search.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;

/**
 * UserCriterion implementation which searches for users by the email specified.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class EmailCriterion implements UserCriterion {

	/**
	 * The email value defined for search by the user.
	 */
	private String email;
	
	public EmailCriterion(String email) {
		this.email = email;
	}
	
	@Override
	public List<User> meetCriterion(List<User> users) {
		List<User> found = new ArrayList<>();
		
		for (User u : users) {
			String realEmail = u.getEmail().toLowerCase();
			
			if (isEmailSuitable(realEmail)) {
				found.add(u);
			}
		}
		return found;
	}
	
	/**
	 * Checks if the email is suitable in terms of the search.
	 * 
	 * @param realEmail - the real user email
	 * @return true if the email is suitable and false otherwise
	 */
	private boolean isEmailSuitable(String realEmail) {
		if (email.equals(realEmail)) {
			return true;
		}
		
		if (email.contains(realEmail) || realEmail.contains(email)) {
			return true;
		}
		
		return false;
	}

}
