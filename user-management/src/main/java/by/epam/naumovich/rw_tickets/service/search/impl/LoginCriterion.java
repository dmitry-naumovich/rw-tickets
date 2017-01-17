package by.epam.naumovich.rw_tickets.service.search.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;

public class LoginCriterion implements UserCriterion {

	public final static String SPACE = " ";
	private String login;
	private boolean moreThanOneWord;
	private String[] words;
	
	public LoginCriterion(String login) {
		this.login = login.toLowerCase();
		
		if (login.split(SPACE).length > 1) {
			moreThanOneWord = true;
			words = login.split(SPACE);
		}
	}
	@Override
	public List<User> meetCriterion(List<User> users) {
		List<User> found = new ArrayList<>();
		
		for (User u : users) {
			String realLogin = u.getLogin().toLowerCase();
			
			if (isLoginSuitable(realLogin)) {
				found.add(u);
			}
		}
		return found;
	}
	
	private boolean isLoginSuitable(String realLogin) {
		if (login.equals(realLogin)) {
			return true;
		}
		
		if (login.contains(realLogin) || realLogin.contains(login)) {
			return true;
		}
		
		if (moreThanOneWord) {
			for (String s : words) {
				if (realLogin.contains(s) || s.contains(realLogin)) {
					return true;
				}
			}
		}
		return false;
	}

}
