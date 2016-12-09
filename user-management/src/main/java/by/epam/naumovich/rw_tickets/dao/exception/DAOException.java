package by.epam.naumovich.rw_tickets.dao.exception;

/**
 * Describes an common exception that may occur in the DAO layer
 * 
 * @author Dzmitry Naumovich
 * @version 1.0
 */
public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2399142216976511197L;
	
	/**
	 * Constructs DAOException object with the exception message
	 * 
	 * @param message occured exception message
	 */
	public DAOException(String message) {
		super(message);
	}
	
	/**
	 * Constructs DAOException object with the exception message and Exception object
	 * 
	 * @param message occured exception message
	 * @param e Exception object
	 */
	public DAOException(String message, Exception e) {
		super(message, e);
	}

}
