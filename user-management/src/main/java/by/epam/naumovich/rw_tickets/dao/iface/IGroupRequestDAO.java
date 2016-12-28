package by.epam.naumovich.rw_tickets.dao.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;
/**
 * Defines methods for implementing in the DAO layer for the GroupRequest entity.
 * 
 * @author Dzmitry Naumovich
 * @version 1.0
 */
public interface IGroupRequestDAO {

	/**
	 * Adds new group request to the data source
	 * 
	 * @param request new group request entity
	 * @return request num of the newly added group request or 0 if it was not added
	 * @throws DAOException
	 */
	int addGroupRequest(GroupRequest request);
	
	/**
	 * Updates the group request status in the data source
	 * 
	 * @param num group request's num
	 * @param newStatus new group request status
	 * @throws DAOException
	 */
	void updateGroupRequest(int num, char newStatus);
	
	/**
	 * Deletes group request entity from the data source
	 * 
	 * @param num group request's num
	 * 
	 * @throws DAOException
	 */
	void deleteGroupRequest(int num);
	
	/**
	 * Gets group request by its num from the data source
	 * 
	 * @param num group request's num
	 * @return found GroupRequest entity
	 * @throws DAOException
	 */
	GroupRequest getGroupRequestByNum(int num);
	

	/**
	 * Returns the number of the request by its toUser, fromUser and groupID fields from the data source
	 * 
	 * @param fromUser id of the user who sent the request
	 * @param toUser id of the user who was sent the request to
	 * @param groupID id of the group
	 * @return request number
	 * @throws DAOException
	 */
	int getReqNumByUserAndGroupIDs(int fromUser, int toUser, int groupID);

	/**
	 * Gets all user incoming group requests from the data source
	 * 
	 * @param userID ID of the user
	 * @return list of user incoming group requests
	 * @throws DAOException
	 */
	List<GroupRequest> getUserIncRequests(int userID);
	
	/**
	 * Gets all user outcoming group requests from the data source
	 * 
	 * @param userID ID of the user
	 * @return list of user outcoming group requests
	 * @throws DAOException
	 */
	List<GroupRequest> getUserOutRequests(int userID);
	
}
