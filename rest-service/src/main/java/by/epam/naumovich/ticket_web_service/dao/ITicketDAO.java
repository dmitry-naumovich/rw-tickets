package by.epam.naumovich.ticket_web_service.dao;

import java.util.List;

import by.epam.naumovich.ticket_web_service.entity.Ticket;

/**
 * Defines methods to be implemented in the DAO layer for Ticket entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public interface ITicketDAO {

	/**
	 * Adds new ticket to the data source
	 * 
	 * @param ticket new ticket entity
	 * @return the number of the added ticket
	 */
	int addTicket(Ticket ticket);
	
	/**
	 * Updates the ticket status in the data source
	 * 
	 * @param ticketNum ticket number
	 * @param status new ticket status
	 */
	void updateStatus(int ticketNum, char status);
	
	/**
	 * Deletes ticket from the data source
	 * 
	 * @param ticketNum ticket number
	 */
	void deleteTicket(int ticketNum);
	
	/**
	 * Gets ticket by its number from the data source
	 * 
	 * @param num ticket number
	 * @return found Ticket entity
	 */
	Ticket getTicketByNum(int num);
	
	/**
	 * Gets all tickets from the data source
	 * 
	 * @return all found Ticket entities in a list
	 */
	List<Ticket> getAllTickets();
	
}
