package by.epam.naumovich.ticket_web_service.webservice.util;

public final class TicketPaths {

	public static final String TICKET_WITH_NUM_PATH = "/tickets/{ticketNum: \\d+}";
	public static final String SINGLE_TICKET_PATH = "/ticket";
	public static final String TICKETS_PATH = "/tickets";
	
	private TicketPaths() {};
}
