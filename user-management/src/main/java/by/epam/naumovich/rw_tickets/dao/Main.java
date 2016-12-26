package by.epam.naumovich.rw_tickets.dao;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.entity.User;

public class Main {

	public static void main(String[] args) {
		System.out.println("MAIN IS IT?");
		User testUser = new User();
    	testUser.setLogin("testLogin");
    	testUser.setFname("testname");
    	testUser.setPwd("pwd");
    	testUser.setSname("testSurname");
    	testUser.setEmail("test@email.com");
    	testUser.setPassport("TST19000");
    	testUser.setAddress("test adress street 45");
    	testUser.setAdmin(false);
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("user-module.xml");
		IUserDAO dao = (IUserDAO) context.getBean("userDao");
	}

}
