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
		//transaction start
		int userID = userService.addUser(user);
		User simpleUser = userService.getUserById(userID);
		List<UserGroup> userGroups = userGroupService.getGroupsByUser(userID);
		UserDTO userDTO = DTOMapper.constructUserDTO(simpleUser, userGroups);
		//transaction end
		return userDTO;
	}
	
	public UserGroupDTO addUserGroup(UserGroup group) {
		//transaction start
		int groupID = userGroupService.addGroup(group);
		UserGroup simpleGroup = userGroupService.getGroupByID(groupID);
		List<User> users = userService.getAllGroupUsers(groupID);
		User owner = userService.getUserById(simpleGroup.getOwner_id());
		UserGroupDTO groupDTO = DTOMapper.constructUserGroupDTO(simpleGroup, users, owner);
		//transaction end
		return groupDTO;
	}
	
	public GroupRequestDTO addGroupRequest(GroupRequest request) {
		//transaction start
		int requestNum = requestService.addRequest(request);
		GroupRequest simpleReq = requestService.getRequestByNum(requestNum);
		User sender = userService.getUserById(simpleReq.getFrom_user());
		User receiver = userService.getUserById(simpleReq.getTo_user());
		UserGroup group = userGroupService.getGroupByID(simpleReq.getGr_id());
		GroupRequestDTO requestDTO = DTOMapper.constructGroupRequestDTO(simpleReq, sender, receiver, group);
		//transaction end
		return requestDTO;
	}
	
	
	
	
}
