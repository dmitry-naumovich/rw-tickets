package by.epam.naumovich.rw_tickets.service.search.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;
import java.util.stream.Collectors;

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
		return users.stream().filter(u -> isEmailSuitable(u.getEmail().toLowerCase())).collect(Collectors.toList());
	}
	
	/**
	 * Checks if the email is suitable in terms of the search.
	 * 
	 * @param realEmail - the real user email
	 * @return true if the email is suitable and false otherwise
	 */
	private boolean isEmailSuitable(String realEmail) {
		return email.equals(realEmail) || email.contains(realEmail) || realEmail.contains(email);
	}

}
