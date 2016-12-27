package by.epam.naumovich.rw_tickets.service.mapper;

import java.util.List;

import by.epam.naumovich.rw_tickets.dto.GroupRequestDTO;
import by.epam.naumovich.rw_tickets.dto.UserDTO;
import by.epam.naumovich.rw_tickets.dto.UserGroupDTO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

public class DTOMapper {

	public static UserDTO constructUserDTO(User user, List<UserGroup> groups) {
		UserDTO dto = new UserDTO();
		dto.setUser(user);
		dto.setUserGroups(groups);
		return dto;
	}
	
	public static UserGroupDTO constructUserGroupDTO(UserGroup group, List<User> users, User owner) {
		UserGroupDTO dto = new UserGroupDTO();
		dto.setGroup(group);
		dto.setUsers(users);
		dto.setOwner(owner);
		return dto;
	}
	
	public static GroupRequestDTO constructGroupRequestDTO(GroupRequest request, User sender, User receiver, UserGroup group) {
		GroupRequestDTO dto = new GroupRequestDTO();
		dto.setRequest(request);
		dto.setSender(sender);
		dto.setReceiver(receiver);
		dto.setGroup(group);
		return dto;
	}
	
}
