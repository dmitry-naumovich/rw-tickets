package by.epam.naumovich.rw_tickets.dto;

import java.util.Map;

import by.epam.naumovich.rw_tickets.entity.UserGroup;
import lombok.Data;

/**
 * Data Transfer Object which combines UserGroup entity with group owner's login and the map of members' 'id-login' pairs.
 * It's used by the Service Facade layer which maps simple entities into this complex object which could be transfered to application interface.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Data
public class UserGroupDTO {

	private UserGroup group;
	private Map<Integer, String> members;
	private String ownerLogin;

}
