package by.epam.naumovich.rw_tickets.dao.iface;

import by.epam.naumovich.rw_tickets.dao.exception.DAOException;
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
	int addGroupRequest(GroupRequest request) throws DAOException;
	
	/**
	 * Updates the group request in the data source
	 * 
	 * @param num group request's num
	 * @param updRequestRequest updated group request entity
	 * @throws DAOException
	 */
	void updateGroupRequest(int num, GroupRequest updRequest) throws DAOException;
	
	/**
	 * Deletes group request entity from the data source
	 * 
	 * @param num group request's num
	 * 
	 * @throws DAOException
	 */
	void deleteGroupRequest(int num) throws DAOException;
	
	/**
	 * Gets group request by its num from the data source
	 * 
	 * @param num group request's num
	 * @return found GroupRequest entity
	 * @throws DAOException
	 */
	GroupRequest getGroupRequestById(int num) throws DAOException;
}
