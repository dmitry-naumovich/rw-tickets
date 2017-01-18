package by.epam.naumovich.rw_tickets.service.search.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;

/**
 * UserCriterion implementation which combines multiple (at least 2) criteria and selects those User object which meet all of them.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class AndCriterion implements UserCriterion {

	/**
	 * List of UserCriteria which are combined.
	 */
	private List<UserCriterion> criteria;
	
	public AndCriterion(List<UserCriterion> criteria) {
		this.criteria = criteria;
	}
	
	/**
	 * Adds criterion to the list
	 * 
	 * @param criterion the criterion to be added
	 */
	public void addCriterion(UserCriterion criterion) {
		criteria.add(criterion);
	}
	
	@Override
	public List<User> meetCriterion(List<User> users) {
		List<User> output = users;
		for (UserCriterion crit : criteria) {
			output = crit.meetCriterion(output);
		}
		return output;
	}

}
