package by.epam.naumovich.ticket_web_service.service;

import by.epam.naumovich.ticket_web_service.entity.Ticket;

/**
 * Contains static methods which are responsible for validating input values on service layer.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public final class Validator {

	private Validator() { };
	
	/**
	 * Validates integer values that all of them are positive.
	 * 
	 * @param ids - the undefined amount of integer values
	 * @return true if all values are correct and false otherwise
	 */
	public static boolean validateIds(int... ids) {
		for (int id : ids) {
			if (id <= 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Validates String objects that all of them are not empty or null.
	 * 
	 * @param strings - the undefined amount of String values
	 * @return true if all values are correct and false otherwise
	 */
	public static boolean validateStrings(String... strings) {
		for (String str : strings) {
			if (str == null || str.isEmpty()) {
				return false;
			}	
		}
		return true;
	}
	
	/**
	 * Validates new (i.e. it's not present in DB yet) Ticket object that it is not null and all its fields comply with the application bounds. 
	 * 
	 * @param ticket - the Ticket object to be validated
	 * @return true if the object comply with the bounds and false otherwise
	 */
	public static boolean validateNewTicket(Ticket ticket) {
		if (ticket == null) {
			return false;
		}
		if (!validateIds(ticket.getPassenger(), ticket.getPrice())) {
			return false;
		}
		if (!validateStrings(ticket.getDepCity(), ticket.getArrCity(), ticket.getDepCountry(), ticket.getArrCountry())) {
			return false;
		}
		if (ticket.getArrDate() == null || ticket.getArrTime() == null || ticket.getDepartDate() == null || ticket.getDepartTime() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Validates existing (i.e. it's already present in DB) Ticket object that it is not null and all its fields comply with the application bounds.
	 * 
	 * @param ticket - the Ticket object to be validated
	 * @return true if the object comply with the bounds and false otherwise
	 */
	public static boolean validateExistingTicket(Ticket ticket) {
		if (!validateNewTicket(ticket)) {
			return false;
		}
		if (!validateIds(ticket.getNum())) {
			return false;
		}
		return true;
	}
	
}
