package by.epam.naumovich.rw_tickets.service.search.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;

public class EmailCriterion implements UserCriterion {

	private String email;
	
	public EmailCriterion(String email) {
		this.email = email;
	}
	
	@Override
	public List<User> meetCriterion(List<User> users) {
		List<User> found = new ArrayList<User>();
		
		for (User u : users) {
			String realEmail = u.getEmail().toLowerCase();
			
			if (isEmailSuitable(realEmail)) {
				found.add(u);
			}
		}
		return found;
	}
	
	private boolean isEmailSuitable(String realEmail) {
		if (email.equals(realEmail) || realEmail.equals(email)) {
			return true;
		}
		
		if (email.contains(realEmail) || realEmail.contains(email)) {
			return true;
		}
		
		return false;
	}

}
