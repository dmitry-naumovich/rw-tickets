package by.epam.naumovich.rw_tickets.service;

import java.util.List;

import by.epam.naumovich.rw_tickets.dto.GroupRequestDTO;
import by.epam.naumovich.rw_tickets.dto.UserDTO;
import by.epam.naumovich.rw_tickets.dto.UserGroupDTO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.iface.*;
import by.epam.naumovich.rw_tickets.service.mapper.DTOMapper;

/**
 * ServiceFacade class contains all necessary methods which might be called by the client. It hides the complexity of the business logic tier,
 * hence all of its methods are as simple as possible. It does so by storing links to all service interfaces and calling their methods when needed.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class ServiceFacade {

	private IUserService userService;
	private IUserGroupService userGroupService;
	private IGroupRequestService requestService;
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
	public void setRequestService(IGroupRequestService requestService) {
		this.requestService = requestService;
	}
	
	public UserDTO addUser(User user) {
		int userID = userService.addUser(user);
		user.setId(userID);
		List<UserGroup> userGroups = userGroupService.getGroupsByUser(userID);
		UserDTO userDTO = DTOMapper.constructUserDTO(user, userGroups);
		return userDTO;
	}
	
	public UserGroupDTO addUserGroup(UserGroup group) {
		int groupID = userGroupService.addGroup(group);
		group.setGr_id(groupID);
		List<User> users = userService.getAllGroupUsers(groupID);
		User owner = userService.getUserById(group.getOwner_id());
		UserGroupDTO groupDTO = DTOMapper.constructUserGroupDTO(group, users, owner);
		return groupDTO;
	}
	
	public GroupRequestDTO addGroupRequest(GroupRequest request) {
		int requestNum = requestService.addRequest(request);
		request.setRq_num(requestNum);
		User sender = userService.getUserById(request.getFrom_user());
		User receiver = userService.getUserById(request.getTo_user());
		UserGroup group = userGroupService.getGroupByID(request.getGr_id());
		GroupRequestDTO requestDTO = DTOMapper.constructGroupRequestDTO(request, sender, receiver, group);
		return requestDTO;
	}
	
	public UserDTO updateUser(User updUser) {
		userService.updateUser(updUser);
		List<UserGroup> userGroups = userGroupService.getGroupsByUser(updUser.getId());
		UserDTO userDTO = DTOMapper.constructUserDTO(updUser, userGroups);
		return userDTO;
	}
	
	public UserGroupDTO updateUserGroup(UserGroup updGroup) {
		userGroupService.updateGroup(updGroup);
		List<User> users = userService.getAllGroupUsers(updGroup.getGr_id());
		User owner = userService.getUserById(updGroup.getOwner_id());
		UserGroupDTO groupDTO = DTOMapper.constructUserGroupDTO(updGroup, users, owner);
		return groupDTO;
	}
	
	public void acceptRequest(int reqNum) {
		requestService.updateRequest(reqNum, 'a');
		GroupRequest req = requestService.getRequestByNum(reqNum);
		userGroupService.addUserToGroup(req.getTo_user(), req.getGr_id());
	}
	
	public void cancelRequest(int reqNum) {
		requestService.updateRequest(reqNum, 'c');
	}
	
	public void rejectRequest(int reqNum) {
		requestService.updateRequest(reqNum, 'r');
	}
}
