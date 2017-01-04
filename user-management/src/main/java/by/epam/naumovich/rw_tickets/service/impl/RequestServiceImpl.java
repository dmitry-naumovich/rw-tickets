package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IRequestDAO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.service.iface.IRequestService;

public class RequestServiceImpl implements IRequestService {

	private IRequestDAO requestDAO;
	
	public void setRequestDAO(IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}

	@Override
	public int addRequest(GroupRequest request) {
		return requestDAO.addGroupRequest(request);
	}

	@Override
	public void updateRequest(int reqNum, char newStatus) {
		requestDAO.updateGroupRequest(reqNum, newStatus);
	}

	@Override
	public void deleteRequest(int reqNum) {
		requestDAO.deleteGroupRequest(reqNum);
	}

	@Override
	public GroupRequest getRequestByNum(int num) {
		return requestDAO.getGroupRequestByNum(num);
	}

	@Override
	public List<GroupRequest> getUserIncRequests(int userID) {
		return requestDAO.getUserIncRequests(userID);
	}

	@Override
	public List<GroupRequest> getUserOutRequests(int userID) {
		return requestDAO.getUserOutRequests(userID);
	}

}
