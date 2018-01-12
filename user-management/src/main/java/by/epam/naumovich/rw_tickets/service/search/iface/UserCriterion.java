package by.epam.naumovich.rw_tickets.service.search.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.User;

/**
 * Functional interface which describes the Search Criterion for the User entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@FunctionalInterface
public interface UserCriterion {
	
	/**
	 * Selects those User objects from the input collection who meet the search criterion.
	 * 
	 * @param users the list of users to be checked
	 * @return the list of users who are met with the criterion
	 */
	List<User> meetCriterion(List<User> users);

}
