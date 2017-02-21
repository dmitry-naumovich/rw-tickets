package by.epam.naumovich.ticket_web_service.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import by.epam.naumovich.ticket_web_service.webservice.TicketPlainRS;
import by.epam.naumovich.ticket_web_service.webservice.TicketXmlRS;

//@ApplicationPath("/rest")
public class RestfulService extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<>();
		set.add(TicketXmlRS.class);
		set.add(TicketPlainRS.class);
		return set;
	}
}
