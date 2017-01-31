package by.epam.naumovich.rw_tickets.entity;

public class Ticket {

	private int num;
	private String cityFrom;
	private String countryFrom;
	private String cityTo;
	private String countryTo;
	private Date departDate;
	private Time departTime;
	private Date arrivalDate;
	private Time arrivalTime;
	private short price;
	private char status;
	private User passenger;
}
