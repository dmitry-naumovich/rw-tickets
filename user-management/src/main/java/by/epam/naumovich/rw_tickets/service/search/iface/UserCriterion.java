package by.epam.naumovich.rw_tickets.service.search.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;

public interface UserCriterion {
	
	public List<User> meetCriterion(List<User> users);

}
