package by.epam.naumovich.rw_tickets.service.mapper;

import java.util.List;

import by.epam.naumovich.rw_tickets.dto.GroupRequestDTO;
import by.epam.naumovich.rw_tickets.dto.UserDTO;
import by.epam.naumovich.rw_tickets.dto.UserGroupDTO;
import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import java.util.stream.Collectors;

/**
 * Contains methods that are responsible for mapping simple entities and collections of such entities on Data Transfer Objects and pass them back.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class DTOMapper {

	private DTOMapper() { }
	
	public static UserDTO constructUserDTO(User user, List<UserGroup> groups, City city, Country country) {
		return UserDTO.builder()
				.user(user)
				.city(city.getName())
				.country(country.getName())
                .userGroups(groups.stream().collect(Collectors.toMap(UserGroup::getId, UserGroup::getName)))
				.build();
	}
	
	public static UserGroupDTO constructUserGroupDTO(UserGroup group, List<User> members, String ownerLogin) {
		return UserGroupDTO.builder()
                .group(group)
                .ownerLogin(ownerLogin)
                .members(members.stream().collect(Collectors.toMap(User::getId, User::getLogin)))
                .build();
	}
	
	public static GroupRequestDTO constructGroupRequestDTO(GroupRequest request, String sender, String receiver, String group) {
		return GroupRequestDTO.builder()
                .request(request)
                .sender(sender)
                .receiver(receiver)
                .group(group)
                .build();
	}
	
}
