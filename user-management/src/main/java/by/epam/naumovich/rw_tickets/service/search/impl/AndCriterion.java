package by.epam.naumovich.rw_tickets.service.search.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.search.iface.UserCriterion;

public class AndCriterion implements UserCriterion {

	private List<UserCriterion> criteria;
	
	public AndCriterion(List<UserCriterion> criteria) {
		this.criteria = criteria;
	}
	
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
