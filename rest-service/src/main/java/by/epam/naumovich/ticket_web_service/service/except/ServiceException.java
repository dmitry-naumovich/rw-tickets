package by.epam.naumovich.ticket_web_service.service.except;

/**
 * Describes exceptions that may occur in service layer classes.
 * 
 * @author Dmitry_Naumovich
 * @version 1.0
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ServiceException(String msg, Exception e) {
		super(msg, e);
	}
	
	public ServiceException(String msg) {
		super(msg);
	}

}
