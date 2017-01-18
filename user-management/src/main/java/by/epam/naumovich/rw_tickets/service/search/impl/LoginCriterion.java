package by.epam.naumovich.rw_tickets.service.search.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;

/**
 * UserCriterion implementation which searches for users by the login specified.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class LoginCriterion implements UserCriterion {

	public static final String SPACE = " ";
	
	/**
	 * The email value defined for search by the user.
	 */
	private String login;
	
	/**
	 * The flag that shows that the value entered consists of more that one word.
	 */
	private boolean moreThanOneWord;
	
	/**
	 * The array of words which is created by separating the entered value into the words.
	 */
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
	
	/**
	 * Checks if the login is suitable in terms of the search.
	 * 
	 * @param realLogin - the real user login
	 * @return true if the login is suitable and false otherwise
	 */
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
