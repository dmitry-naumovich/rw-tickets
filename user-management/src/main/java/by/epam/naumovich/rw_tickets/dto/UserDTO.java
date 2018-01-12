package by.epam.naumovich.rw_tickets.dto;

import java.util.Map;

import by.epam.naumovich.rw_tickets.entity.User;
import lombok.Data;

/**
 * Data Transfer Object which combines User entity with City and Country names and the list of groups which user is involved into.
 * It's used by the Service Facade layer which maps simple entities into this complex object which could be transfered to application interface.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Data
public class UserDTO {

	private User user;
	private String country;
	private String city;
	private Map<Integer, String> userGroups;

}
