package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;

/**
* Defines methods that receive parameters from Command implementations, verify them, construct necessary entities if needed 
* and then pass them to the DAO layer, possibly getting some objects or primitive values back and passing them further back to the commands.
* Works with the GroupRequest entity mostly.
* 
* @author Dmitry Naumovich
* @version 1.0
*/
public interface IGroupRequestService {

	public int addRequest(GroupRequest request);
	public void updateRequest(GroupRequest request);
	public void deleteRequest(GroupRequest request);
	
	GroupRequest getRequestByNum(int num);
	
	List<GroupRequest> getAllRequests();
	List<GroupRequest> getUserIncRequests(int userID);
	List<GroupRequest> getUserOutRequests(int userID);
}
