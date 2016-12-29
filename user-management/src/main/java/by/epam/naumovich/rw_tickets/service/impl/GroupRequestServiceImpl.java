package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IGroupRequestDAO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.service.iface.IGroupRequestService;

public class GroupRequestServiceImpl implements IGroupRequestService {

	private IGroupRequestDAO requestDAO;
	
	public void setRequestDAO(IGroupRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}

	@Override
	public int addRequest(GroupRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateRequest(int reqNum, char newStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRequest(int reqNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public GroupRequest getRequestByNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupRequest> getAllRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupRequest> getUserIncRequests(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupRequest> getUserOutRequests(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
