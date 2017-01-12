package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import by.epam.naumovich.rw_tickets.dao.iface.IRequestDAO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.service.exception.InvalidInputServiceException;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IRequestService;
import by.epam.naumovich.rw_tickets.service.util.ExceptionMessages;

public class RequestServiceImpl implements IRequestService {

	private IRequestDAO requestDAO;
	
	public void setRequestDAO(IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}

	@Override
	public int addRequest(GroupRequest request) throws ServiceException {
		if (request == null) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			return requestDAO.addRequest(request);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void updateRequest(int reqNum, char newStatus) throws ServiceException {
		if (reqNum <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			requestDAO.updateRequest(reqNum, newStatus);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		} 
	}

	@Override
	public void deleteRequest(int reqNum) throws ServiceException {
		if (reqNum <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			requestDAO.deleteRequest(reqNum);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public GroupRequest getRequestByNum(int num) throws ServiceException {
		if (num <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			return requestDAO.getRequestByNum(num);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public List<GroupRequest> getUserIncRequests(int userID) throws ServiceException {
		if (userID <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			return requestDAO.getUserIncRequests(userID);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public List<GroupRequest> getUserOutRequests(int userID) throws ServiceException {
		if (userID <= 0) {
			throw new InvalidInputServiceException(ExceptionMessages.INVALID_INPUT_PARAMS);
		}
		try {
			return requestDAO.getUserOutRequests(userID);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

}
