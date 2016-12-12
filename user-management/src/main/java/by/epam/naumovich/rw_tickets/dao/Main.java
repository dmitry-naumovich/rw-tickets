package by.epam.naumovich.rw_tickets.dao;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.naumovich.rw_tickets.dao.exception.DAOException;
import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.entity.User;

public class Main {

	public static void main(String[] args) {
		User testUser = new User();
    	testUser.setLogin("testLogin");
    	testUser.setName("testname");
    	testUser.setPassword("pwd");
    	testUser.setSurname("testSurname");
    	testUser.setEmail("test@email.com");
    	testUser.setPassport("TST19000");
    	testUser.setAddress("test adress street 45");
    	testUser.setAdmin(false);
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("user-module.xml");

		IUserDAO dao = (IUserDAO) context.getBean("userDao");
		try {
			dao.addUser(testUser);
		} catch (DAOException e) {
			System.out.println("DAOException occured");
		}
	}

}
