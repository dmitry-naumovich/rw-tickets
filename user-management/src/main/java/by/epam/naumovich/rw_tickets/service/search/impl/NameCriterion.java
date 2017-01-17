package by.epam.naumovich.rw_tickets.service.search.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;

public class NameCriterion implements UserCriterion {

	public final static String SPACE = " ";
	private String name;
	private boolean moreThanOneWord;
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
		List<User> found = new ArrayList<>();
		
		for (User u : users) {
			String realFName = u.getFname().toLowerCase();
			String realSName = u.getSname().toLowerCase();
			
			if (isNameSuitable(realFName, realSName)) {
				found.add(u);
			}
		}
		return found;
	}
	
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
