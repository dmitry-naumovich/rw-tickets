package by.epam.naumovich.soap.service;

import java.util.List;

import by.epam.naumovich.soap.entity.Ticket;
import by.epam.naumovich.soap.service.except.ServiceException;

/**
 * Defines methods that receive parameters from ServiceFacade layer, verify them, and then either pass them to the DAO layer 
 * or additionally does some logic possibly getting some objects or primitive values back and passing them further back to the ServiceFacade.
 * This class operates with the Ticket entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public interface ITicketService {

	public int addTicket(Ticket ticket) throws ServiceException;
	public void updateStatus(int ticketNum, char newStatus) throws ServiceException;
	public void deleteTicket(int num) throws ServiceException;
	
	Ticket getTicketByNum(int num) throws ServiceException;
	List<Ticket> getAllTickets() throws ServiceException;
}
