package by.epam.naumovich.rw_tickets.service.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import by.epam.naumovich.rw_tickets.dto.GroupRequestDTO;
import by.epam.naumovich.rw_tickets.dto.UserDTO;
import by.epam.naumovich.rw_tickets.dto.UserGroupDTO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

public class DTOMapper {

	private DTOMapper() { };
	
	public static UserDTO constructUserDTO(User user, List<UserGroup> groups) {
		UserDTO dto = new UserDTO();
		dto.setUser(user);
		Map<Integer, String> grs = new LinkedHashMap<>();
		for (UserGroup gr : groups) {
			grs.put(gr.getGr_id(), gr.getGr_name());
		}
		dto.setUserGroups(grs);
		return dto;
	}
	
	public static UserGroupDTO constructUserGroupDTO(UserGroup group, List<User> members, String ownerLogin) {
		UserGroupDTO dto = new UserGroupDTO();
		dto.setGroup(group);
		dto.setOwnerLogin(ownerLogin);
		Map<Integer, String> membs = new LinkedHashMap<>();
		for (User user : members) {
			membs.put(user.getId(), user.getLogin());
		}
		dto.setMembers(membs);
		return dto;
	}
	
	public static GroupRequestDTO constructGroupRequestDTO(GroupRequest request, String sender, String receiver, String group) {
		GroupRequestDTO dto = new GroupRequestDTO();
		dto.setRequest(request);
		dto.setSender(sender);
		dto.setReceiver(receiver);
		dto.setGroup(group);
		return dto;
	}
	
}
