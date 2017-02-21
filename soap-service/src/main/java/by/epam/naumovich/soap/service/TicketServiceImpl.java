package by.epam.naumovich.soap.service;

import java.util.List;

import by.epam.naumovich.soap.dao.ITicketDAO;
import by.epam.naumovich.soap.entity.Ticket;
import by.epam.naumovich.soap.service.except.ServiceException;


public class TicketServiceImpl implements ITicketService {

	public static final String INVALID_INPUT_PARAMS = "Invalid input parameters passed into method";
	
	private ITicketDAO ticketDAO;
	
	public void setTicketDAO(ITicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}

	@Override
	public int addTicket(Ticket ticket) throws ServiceException {
		if (!Validator.validateNewTicket(ticket)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return ticketDAO.addTicket(ticket);
	}

	@Override
	public void updateStatus(int ticketNum, char newStatus) throws ServiceException {
		if (!Validator.validateIds(ticketNum)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		ticketDAO.updateStatus(ticketNum, newStatus);
	}

	@Override
	public void deleteTicket(int num) throws ServiceException {
		if (!Validator.validateIds(num)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		ticketDAO.deleteTicket(num);
	}

	@Override
	public Ticket getTicketByNum(int num) throws ServiceException {
		if (!Validator.validateIds(num)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return ticketDAO.getTicketByNum(num);
	}

	@Override
	public List<Ticket> getAllTickets() throws ServiceException {
		return ticketDAO.getAllTickets();
	}

}
