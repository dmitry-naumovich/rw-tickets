package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;

/**
* Defines methods that receive parameters from Command implementations, verify them, construct necessary entities if needed 
* and then pass them to the DAO layer, possibly getting some objects or primitive values back and passing them further back to the commands.
* Works with the GroupRequest entity mostly.
* 
* @author Dmitry Naumovich
* @version 1.0
*/
public interface IRequestService {

	public int addRequest(GroupRequest request) throws ServiceException;
	public void updateRequest(int reqNum, char newStatus) throws ServiceException;
	public void deleteRequest(int reqNum) throws ServiceException;
	
	GroupRequest getRequestByNum(int num) throws ServiceException;
	
	List<GroupRequest> getUserIncRequests(int userID) throws ServiceException;
	List<GroupRequest> getUserOutRequests(int userID) throws ServiceException;
}
