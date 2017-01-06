package by.epam.naumovich.rw_tickets.service;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.dto.GroupRequestDTO;
import by.epam.naumovich.rw_tickets.dto.UserDTO;
import by.epam.naumovich.rw_tickets.dto.UserGroupDTO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.*;
import by.epam.naumovich.rw_tickets.service.mapper.DTOMapper;
import by.epam.naumovich.rw_tickets.service.util.USER_SORT_TYPE;

/**
 * ServiceFacade class contains all necessary methods which may be called by the client. 
 * It hides the complexity of the business logic tier, hence all of its methods are as simple as possible. 
 * It does so by storing links to all service interfaces and calling their methods when needed.
 * This tier also deals with data transfer objects (DTOs). 
 * It works with DTOMapper class which converts simple Plain Old Java Objects (POJOs) into DTOs.
 * ServiceFacade return those DTOs to the client.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class ServiceFacade {

	private IUserService userService;
	private IGroupService groupService;
	private IRequestService requestService;
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public void setGroupService(IGroupService userGroupService) {
		this.groupService = userGroupService;
	}
	public void setRequestService(IRequestService requestService) {
		this.requestService = requestService;
	}
	
	public UserDTO addUser(User user) throws ServiceException {
		int userID = userService.addUser(user);
		user.setId(userID);
		List<UserGroup> userGroups = groupService.getGroupsByUser(userID);
		return DTOMapper.constructUserDTO(user, userGroups);
	}
	
	public UserGroupDTO addUserGroup(UserGroup group) throws ServiceException {
		int groupID = groupService.addGroup(group);
		group.setGr_id(groupID);
		List<User> members = userService.getAllGroupMembers(groupID);
		String ownerLogin = userService.getLoginById(group.getOwner_id());
		return DTOMapper.constructUserGroupDTO(group, members, ownerLogin);
	}
	
	public GroupRequestDTO addGroupRequest(GroupRequest request) throws ServiceException {
		int requestNum = requestService.addRequest(request);
		request.setRq_num(requestNum);
		String sender = userService.getLoginById(request.getFrom_user());
		String receiver = userService.getLoginById(request.getTo_user());
		String group = groupService.getGroupNameByID(request.getGr_id());
		return DTOMapper.constructGroupRequestDTO(request, sender, receiver, group);
	}
	
	public UserDTO updateUser(User updUser) throws ServiceException {
		userService.updateUser(updUser);
		List<UserGroup> userGroups = groupService.getGroupsByUser(updUser.getId());
		return DTOMapper.constructUserDTO(updUser, userGroups);
	}
	
	public UserGroupDTO updateUserGroup(UserGroup updGroup) throws ServiceException {
		groupService.updateGroup(updGroup);
		List<User> members = userService.getAllGroupMembers(updGroup.getGr_id());
		String ownerLogin = userService.getLoginById(updGroup.getOwner_id());
		return DTOMapper.constructUserGroupDTO(updGroup, members, ownerLogin);
	}
	
	public void acceptRequest(int reqNum) throws ServiceException {
		requestService.updateRequest(reqNum, 'a');
		GroupRequest req = requestService.getRequestByNum(reqNum);
		groupService.addGroupMember(req.getTo_user(), req.getGr_id());
	}
	
	public void cancelRequest(int reqNum) throws ServiceException {
		requestService.updateRequest(reqNum, 'c');
	}
	
	public void rejectRequest(int reqNum) throws ServiceException {
		requestService.updateRequest(reqNum, 'r');
	}
	
	public void deleteUser(int userID) throws ServiceException {
		userService.deleteUser(userID);
	}
	
	public void deleteGroup(int groupID) throws ServiceException {
		groupService.deleteGroup(groupID);
	}
	
	public void deleteRequest(int reqNum) throws ServiceException {
		requestService.deleteRequest(reqNum);
	}
	
	public UserDTO getUserById(int userID) throws ServiceException {
		User user = userService.getUserById(userID);
		List<UserGroup> userGroups = groupService.getGroupsByUser(userID);
		return DTOMapper.constructUserDTO(user, userGroups);
	}
	
	public UserGroupDTO getGroupById(int groupID) throws ServiceException {
		UserGroup group = groupService.getGroupByID(groupID);
		List<User> members = userService.getAllGroupMembers(groupID);
		String ownerLogin = userService.getLoginById(group.getOwner_id());
		return DTOMapper.constructUserGroupDTO(group, members, ownerLogin);
	}
	
	public GroupRequestDTO getRequestByNum(int reqNum) throws ServiceException {
		GroupRequest request = requestService.getRequestByNum(reqNum);
		String sender = userService.getLoginById(request.getFrom_user());
		String receiver = userService.getLoginById(request.getTo_user());
		String group = groupService.getGroupNameByID(request.getGr_id());
		return DTOMapper.constructGroupRequestDTO(request, sender, receiver, group);
	}
	
	public List<User> getAllExistingUsers() throws ServiceException {
		return userService.getAllUsers();
	}

	public List<User> getAllUsersSorted(USER_SORT_TYPE sortType) throws ServiceException {
		return userService.getAllUsersSorted(sortType);
	}
	
	public UserDTO leaveGroup(int memberID, int groupID) throws ServiceException {
		groupService.removeGroupMember(memberID, groupID);
		return getUserById(memberID);
	}
	
	public UserGroupDTO removeGroupMember(int memberID, int groupID) throws ServiceException {
		groupService.removeGroupMember(memberID, groupID);
		return getGroupById(groupID);
	}
	
	public List<GroupRequestDTO> getIncomingRequests(int userID) throws ServiceException {
		List<GroupRequest> requests = requestService.getUserIncRequests(userID);
		List<GroupRequestDTO> incReqs = new ArrayList<>();
		for (GroupRequest req : requests) {
			String sender = userService.getLoginById(req.getFrom_user());
			String receiver = userService.getLoginById(req.getTo_user());
			String group = groupService.getGroupNameByID(req.getGr_id());
			incReqs.add(DTOMapper.constructGroupRequestDTO(req, sender, receiver, group));
		}
		return incReqs;
	}
	
	public List<GroupRequestDTO> getOutcomingRequests(int userID) throws ServiceException {
		List<GroupRequest> requests = requestService.getUserOutRequests(userID);
		List<GroupRequestDTO> outReqs = new ArrayList<>();
		for (GroupRequest req : requests) {
			String sender = userService.getLoginById(req.getFrom_user());
			String receiver = userService.getLoginById(req.getTo_user());
			String group = groupService.getGroupNameByID(req.getGr_id());
			outReqs.add(DTOMapper.constructGroupRequestDTO(req, sender, receiver, group));
		}
		return outReqs;
	}

	public void authenticateByLogin(String login, String pass) throws ServiceException {
		userService.authenticateByLogin(login, pass);
	}
	
	public void authenticateByEmail(String email, String pass) throws ServiceException {
		userService.authenticateByEmail(email, pass);
	}
	
}
