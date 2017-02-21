package by.epam.naumovich.ticket_web_service.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TicketList {

	//@XmlElement(name="tickets")
	private List<Ticket> tickets = new ArrayList<>();
	
	public TicketList() {
		super();
	}
	
	@XmlElement(name="ticket")
	//@XmlElementWrapper(name = "tickets")
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public boolean add(Ticket t) {
		return tickets.add(t);
	}

	@Override
	public String toString() {
		return "TicketList [tickets=" + tickets + "]";
	}
	
}
