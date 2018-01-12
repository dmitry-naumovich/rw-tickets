package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;

/**
 * Defines methods that receive parameters from ServiceFacade layer, verify them, and then either pass them to the DAO layer 
 * or additionally does some logic possibly getting some objects or primitive values back and passing them further back to the ServiceFacade.
 * This class operates with the GroupRequest entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public interface IRequestService {

	int addRequest(GroupRequest request) throws ServiceException;
	void updateRequest(int reqNum, char newStatus) throws ServiceException;
	void deleteRequest(int reqNum) throws ServiceException;
	
	GroupRequest getRequestByNum(int num) throws ServiceException;
	
	List<GroupRequest> getUserIncRequests(int userID) throws ServiceException;
	List<GroupRequest> getUserOutRequests(int userID) throws ServiceException;
}
