package by.epam.naumovich.soap.webservice;

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.naumovich.soap.entity.Ticket;
import by.epam.naumovich.soap.service.ITicketService;
import by.epam.naumovich.soap.service.except.ServiceException;
import by.epam.naumovich.soap.webservice.util.TicketMapper;

@WebService
public class TicketSoap {

	public static final char TICKET_STATUS_ACQUIRED = 'a';
	private static final String CONTEXT_FILE_NAME = "spring-context.xml";
	private static final String SERVICE_BEAN_NAME = "ticketService";
	
	private boolean contextLoaded = false;
	private ITicketService ticketService;
	
	public TicketSoap() {
		
	}
	
	private void checkSpringContext() {
		if (!contextLoaded) {
			try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_FILE_NAME)) {
				ticketService = (ITicketService)context.getBean(SERVICE_BEAN_NAME);
			}
			contextLoaded = true;
		}	
	}
	
	@WebMethod
	public String hello(String name) {
		return "Hello, " + name;
	}
	
	@WebMethod
	public List<Ticket> getAllTickets() {
		checkSpringContext();
		try {
			return ticketService.getAllTickets();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	@WebMethod
	public Ticket getTicketByNum(int num) {
		checkSpringContext();
		try {
			return ticketService.getTicketByNum(num);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			
		}
		return new Ticket();
	}
	
	@WebMethod
	public int create(String depCity, String depCountry, String arrCity, String arrCountry, Date depDate, Time depTime,
			  Date arrDate, Time arrTime, int price, Character status, int passenger)  {
		checkSpringContext();
		Ticket ticket = TicketMapper.createTicket(depCity, depCountry, arrCity, arrCountry, depDate, depTime, arrDate, arrTime, price, status, passenger);
		
		int newTicketID = 0;
		
		try {
			newTicketID = ticketService.addTicket(ticket);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return newTicketID;
	}
	
	@WebMethod
	public void delete(int num) {
		checkSpringContext();
		try {
			ticketService.deleteTicket(num);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@WebMethod
	public void acquireTicket(int num) {
		checkSpringContext();
		try {
			ticketService.updateStatus(num, TICKET_STATUS_ACQUIRED);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
}
