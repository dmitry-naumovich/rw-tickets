package by.epam.naumovich.soap.webservice.util;

public final class ResponseMessages {

	public static final String MISSING_PARAMS = "Please, fill all the necessary fields";
	public static final String SUCCESSFULL_TICKET_ACQUISITON = "The ticket was successfully deleted";
	public static final String SUCCESSFULL_TICKET_CREATION = "The ticket was successfully created";
	public static final String SUCCESSFULL_TICKET_DELETION = "The ticket was successfully deleted";
	public static final String TICKET_NOT_DELETED = "Something went wrong and the ticket was not deleted";
	public static final String WRONG_INPUT_PARAMETERS = "Wrong input parameter(s)! Please, try again.";
	public static final String WRONG_TICKET_ID = "Ticket with such ID does not exist. Please, try again!";
	
	private ResponseMessages() {};
}
