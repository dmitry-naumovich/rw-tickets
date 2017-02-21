package by.epam.naumovich.soap.webservice.util;

import java.sql.Date;
import java.sql.Time;

import by.epam.naumovich.soap.entity.Ticket;


public class TicketMapper {
	
	public static Ticket createTicket(String depCity, String depCountry, String arrCity, String arrCountry,
									  Date depDate, Time depTime, Date arrDate, Time arrTime,
									  int price, Character status, int passenger) {
		Ticket ticket = new Ticket();
		ticket.setDepCity(depCity);
		ticket.setDepCountry(depCountry);
		ticket.setArrCity(arrCity);
		ticket.setArrCountry(arrCountry);
		ticket.setDepartDate(depDate);
		ticket.setDepartTime(depTime);
		ticket.setArrDate(arrDate);
		ticket.setArrTime(arrTime);
		ticket.setPrice((short)price);
		ticket.setStatus(status);
		ticket.setPassenger(passenger);
		return ticket;
	}
						
}
