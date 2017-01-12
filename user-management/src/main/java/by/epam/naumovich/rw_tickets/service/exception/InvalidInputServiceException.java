package by.epam.naumovich.rw_tickets.service.exception;

/**
 * Describes exceptions that are thrown by Service layer methods when at least one input parameter is null.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class InvalidInputServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidInputServiceException(String msg, Exception e) {
		super(msg, e);
	}

	public InvalidInputServiceException(String msg) {
		super(msg);
	}

}
