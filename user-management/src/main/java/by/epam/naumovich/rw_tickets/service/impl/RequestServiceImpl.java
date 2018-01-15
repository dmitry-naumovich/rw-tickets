package by.epam.naumovich.rw_tickets.service.impl;

import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IRequestDAO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IRequestService;
import by.epam.naumovich.rw_tickets.service.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IRequestService implementation which validates input parameters using the Validator class and invokes the methods from DAO 
 * which is injected into this class by the Spring Framework IoC framework.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 * @see Validator
 */
@Service
public class RequestServiceImpl implements IRequestService {

	private static final String INVALID_INPUT_PARAMS = "Invalid input parameters passed into method";

	private final IRequestDAO requestDAO;

	@Autowired
	public RequestServiceImpl(IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}

	@Override
	public int addRequest(GroupRequest request) throws ServiceException {
		if (!Validator.validateNewRequest(request)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return requestDAO.addRequest(request);
	}

	@Override
	public void updateRequest(int reqNum, char newStatus) throws ServiceException {
		if (!Validator.validateIds(reqNum) || !Validator.validateRequestStatus(newStatus)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		requestDAO.updateRequest(reqNum, newStatus);
	}

	@Override
	public void deleteRequest(int reqNum) throws ServiceException {
		if (!Validator.validateIds(reqNum)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		requestDAO.deleteRequest(reqNum);
	}

	@Override
	public GroupRequest getRequestByNum(int num) throws ServiceException {
		if (!Validator.validateIds(num)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return requestDAO.getRequestByNum(num);
	}

	@Override
	public List<GroupRequest> getUserIncRequests(int userID) throws ServiceException {
		if (!Validator.validateIds(userID)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return requestDAO.getUserIncRequests(userID);
	}

	@Override
	public List<GroupRequest> getUserOutRequests(int userID) throws ServiceException {
		if (!Validator.validateIds(userID)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return requestDAO.getUserOutRequests(userID);
	}

}
