package by.epam.naumovich.rw_tickets.dao;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.naumovich.rw_tickets.service.ServiceFacade;

public class Main {

	public static void main(String[] args) {   	
		// considering this as controller/command yet
		// so the command would call the Validator class to validate values and construct simple entity objects
    	ApplicationContext context = new ClassPathXmlApplicationContext("user-module.xml");
		ServiceFacade facade = (ServiceFacade) context.getBean("serviceFacade");
		
	}

}
