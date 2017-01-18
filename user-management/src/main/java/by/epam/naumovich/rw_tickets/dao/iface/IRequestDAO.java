package by.epam.naumovich.rw_tickets.dao.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;
/**
 * Defines methods to be implemented in the DAO layer for GroupRequest entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public interface IRequestDAO {

	/**
	 * Adds new group request to the data source
	 * 
	 * @param request new group request entity
	 * @return request num of the newly added group request or 0 if it was not added
	 */
	int addRequest(GroupRequest request);
	
	/**
	 * Updates the group request status in the data source
	 * 
	 * @param num group request's num
	 * @param newStatus new group request status
	 */
	void updateRequest(int num, char newStatus);
	
	/**
	 * Deletes group request entity from the data source
	 * 
	 * @param num group request's num
	 */
	void deleteRequest(int num);
	
	/**
	 * Gets group request by its num from the data source
	 * 
	 * @param num group request's num
	 * @return found GroupRequest entity
	 */
	GroupRequest getRequestByNum(int num);

	/**
	 * Returns the number of the request by its toUser, fromUser and groupID fields from the data source
	 * 
	 * @param fromUser id of the user who sent the request
	 * @param toUser id of the user who was sent the request to
	 * @param groupID id of the group
	 * @return request number
	 */
	int getReqNumByUserAndGroupIDs(int fromUser, int toUser, int groupID);

	/**
	 * Gets all user incoming group requests from the data source
	 * 
	 * @param userID ID of the user
	 * @return list of user incoming group requests
	 */
	List<GroupRequest> getUserIncRequests(int userID);
	
	/**
	 * Gets all user outcoming group requests from the data source
	 * 
	 * @param userID ID of the user
	 * @return list of user outcoming group requests
	 */
	List<GroupRequest> getUserOutRequests(int userID);
	
}
