package by.epam.naumovich.rw_tickets.dto;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import lombok.Data;

/**
 * Data Transfer Object which combines GroupRequest entity with Request's sender and receiver logins and group's name.
 * It's used by the Service Facade layer which maps simple entities into this complex object which could be transfered to application interface.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Data
public class GroupRequestDTO {

	private GroupRequest request;
	private String sender;
	private String receiver;
	private String group;

}
