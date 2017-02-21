package by.epam.naumovich.ticket_web_service.webservice;

import java.net.URI;
import java.sql.Date;
import java.sql.Time;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.naumovich.ticket_web_service.entity.Ticket;
import by.epam.naumovich.ticket_web_service.entity.TicketList;
import by.epam.naumovich.ticket_web_service.service.ITicketService;
import by.epam.naumovich.ticket_web_service.service.except.ServiceException;
import by.epam.naumovich.ticket_web_service.webservice.util.ResponseMessages;
import by.epam.naumovich.ticket_web_service.webservice.util.TicketMapper;
import by.epam.naumovich.ticket_web_service.webservice.util.TicketPaths;

@Path("/plain")
public class TicketPlainRS {

	public static final String CONTEXT_FILE_NAME = "spring-context.xml";
	public static final String SERVICE_BEAN_NAME = "ticketService";
	public static final String TICKET_NUM_PARAM = "ticketNum";
	public static final char TICKET_STATUS_ACQUIRED = 'a';
	
	private boolean contextLoaded = false;
	private ITicketService ticketService;
	
	public TicketPlainRS() {
		// Empty constructor may be used by Jersey RESTful Web Services framework, for instance
	}
	
	private void checkSpringContext() {
		if (!contextLoaded) {
			try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_FILE_NAME)) {
				ticketService = (ITicketService)context.getBean(SERVICE_BEAN_NAME);
			}
			contextLoaded = true;
		}	
	}
	
	@GET
	@Path(TicketPaths.TICKET_WITH_NUM_PATH)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getTicketByNum(@PathParam(TICKET_NUM_PARAM) int ticketNum) {
		checkSpringContext();
		Ticket ticket = null;
		try {
			ticket = ticketService.getTicketByNum(ticketNum);
		} catch (RuntimeException | ServiceException e) {
			return Response.status(Response.Status.FORBIDDEN).entity(ResponseMessages.WRONG_TICKET_ID).type(MediaType.TEXT_PLAIN).build();
		}
		return Response.status(200).entity(ticket.toString()).build();
	}
	
	@GET
	@Path(TicketPaths.TICKETS_PATH)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAllTickets() {
		checkSpringContext();
		TicketList list = new TicketList();
		try {
			list.setTickets(ticketService.getAllTickets());
		} catch (RuntimeException | ServiceException e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok(list.toString()).build();
	}
	
	@POST
	@Path(TicketPaths.SINGLE_TICKET_PATH)
	@Produces(MediaType.TEXT_PLAIN)
	public Response create(@FormParam("depCity") String depCity, @FormParam("depCountry") String depCountry,
						   @FormParam("arrCity") String arrCity, @FormParam("arrCountry") String arrCountry,
						   @FormParam("depDate") Date depDate, @FormParam("depTime") Time depTime,
						   @FormParam("arrDate") Date arrDate, @FormParam("arrTime") Time arrTime,
						   @FormParam("price") int price, @FormParam("status") Character status, @FormParam("passenger") int passenger)  {
		checkSpringContext();
		Ticket ticket = TicketMapper.createTicket(depCity, depCountry, arrCity, arrCountry, depDate, depTime, arrDate, arrTime, price, status, passenger);
		
		int newTicketID = 0;
		
		try {
			newTicketID = ticketService.addTicket(ticket);
		} catch (ServiceException e) {
			return Response.status(Response.Status.FORBIDDEN).entity(ResponseMessages.WRONG_INPUT_PARAMETERS).type(MediaType.TEXT_PLAIN).build();
		} catch (RuntimeException e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}
		return Response.created(URI.create("/" + newTicketID)).build();
	}
	
	@DELETE
	@Path(TicketPaths.TICKET_WITH_NUM_PATH)
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@PathParam(TICKET_NUM_PARAM) int ticketNum) {
		checkSpringContext();
		try {
			ticketService.deleteTicket(ticketNum);
		} catch (ServiceException e) {
			return Response.status(Response.Status.FORBIDDEN).entity(ResponseMessages.TICKET_NOT_DELETED).type(MediaType.TEXT_PLAIN).build();
		} catch (RuntimeException e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		return Response.status(200).entity(ResponseMessages.SUCCESSFULL_TICKET_DELETION).build();
	}
	
	@PUT
	@Path(TicketPaths.TICKET_WITH_NUM_PATH)
	@Produces(MediaType.TEXT_PLAIN)
	public Response acquireTicket(@PathParam(TICKET_NUM_PARAM) int ticketNum) {
		checkSpringContext();
		try {
			ticketService.updateStatus(ticketNum, TICKET_STATUS_ACQUIRED);
		} catch (ServiceException e) {
			return Response.status(Response.Status.FORBIDDEN).entity(ResponseMessages.WRONG_INPUT_PARAMETERS).type(MediaType.TEXT_PLAIN).build();
		} catch (RuntimeException e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		return Response.status(200).entity(ResponseMessages.SUCCESSFULL_TICKET_ACQUISITON).build();
	}
	
	
}
