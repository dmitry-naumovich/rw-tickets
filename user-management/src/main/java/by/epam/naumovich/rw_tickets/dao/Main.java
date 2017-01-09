package by.epam.naumovich.rw_tickets.dao;

/*import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.naumovich.rw_tickets.service.ServiceFacade;

import by.epam.naumovich.rw_tickets.service.iface.IUserService;
import by.epam.naumovich.rw_tickets.service.impl.UserServiceImpl;*/
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;

public class Main {

	private Main() { }
	
	public static void main(String[] args) throws ServiceException {
		// considering this as controller/command yet
		// so the command would call the Validator class to validate values and construct simple entity objects
    	// like ApplicationContext context = new ClassPathXmlApplicationContext("user-module.xml");
		// and ServiceFacade facade = (ServiceFacade) context.getBean("serviceFacade");
		// facade.method()...
		
		// caution! either the controller or the service (probably facade) must handle the DataAccessExceptions coming from the Spring JDBC
		// so one of those classes must wrap the method calls into the 'try-catch' section
		// my case: service catches dao exceptions and throws ServiceExceptions and controller should handle them
		/* ApplicationContext context = new ClassPathXmlApplicationContext("user-module.xml")
		ServiceFacade facade = (ServiceFacade) context.getBean("serviceFacade")
		facade.getUserById(81)*/
	}

}
