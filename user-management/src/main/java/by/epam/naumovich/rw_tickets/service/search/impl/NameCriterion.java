package by.epam.naumovich.rw_tickets.service.search.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;
import java.util.stream.Collectors;

/**
 * UserCriterion implementation which searches for users by the name/surname specified.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class NameCriterion implements UserCriterion {

	private static final String SPACE = " ";
	
	/**
	 * The name/surname value defined for search by the user.
	 */
	private String name;
	
	/**
	 * The flag that shows that the value entered consists of more that one word.
	 */
	private boolean moreThanOneWord;
	
	/**
	 * The array of words which is created by separating the entered value into the words.
	 */
	private String[] words;
	
	public NameCriterion(String name) {
		this.name = name.toLowerCase();
		
		if (name.split(SPACE).length > 1) {
			moreThanOneWord = true;
			words = name.split(SPACE);
		}
	}
	
	@Override
	public List<User> meetCriterion(List<User> users) {
		return users.stream().filter(u -> isNameSuitable(u.getFname().toLowerCase(), u.getSname().toLowerCase()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Checks if the name/surname is suitable in terms of the search.
	 * 
	 * @param fName - the real user's first name
	 * @param sName - the real user's second name
	 * @return true if the name or surname or both are suitable and false otherwise
	 */
	private boolean isNameSuitable(String fName, String sName) {
		if (name.equals(fName) || name.equals(sName)) {
			return true;
		}
		
		if (name.contains(fName) || name.contains(sName)) {
			return true;
		}
		if (fName.contains(name) || sName.contains(name)) {
			return true;
		}
		
		if (moreThanOneWord) {
			for (String s : words) {
				if (fName.contains(s) || s.contains(fName)) {
					return true;
				}
				if (sName.contains(s) || s.contains(sName)) {
					return true;
				}
			}
		}
		return false;
	}

}
