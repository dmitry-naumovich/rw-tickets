package by.epam.naumovich.rw_tickets.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.*;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.entity.User;

@DataSet("dbunit/DAODataTest.xml")
public class UserDAOTest extends UnitilsJUnit4 {
	
	ApplicationContext context;
	private IUserDAO userDAO;
	User testUser;

	@Before    
    public void init() {
		context = new ClassPathXmlApplicationContext("user-module-test.xml");
		userDAO = (IUserDAO) context.getBean("userDao");
    }
	
	public void initTestUser() {
		testUser = new User();
		testUser.setId(6);
		testUser.setLogin("testLogin");
    	testUser.setFname("testname");
    	testUser.setPwd("pwd");
    	testUser.setSname("testSurname");
    	testUser.setCountry("by");
    	testUser.setCity("mn");
    	testUser.setPhone("123456789012");
    	testUser.setEmail("test@email.com");
    	testUser.setPassport("TST190009999");
    	testUser.setAddress("test street 45");
    	testUser.setAdmin(false);
	}
	
    @Test
    @ExpectedDataSet({"dbunit/AfterAddUser.xml"})
    public void testAddUser() {
    	initTestUser();
    	int id = userDAO.addUser(testUser);
    	testUser.setId(id);
    }
    
    @Test
    @ExpectedDataSet({"dbunit/AfterUpdUser.xml"})
    public void testUpdateUser() {
    	User us = userDAO.getUserById(1);
    	us.setLogin("nee");
    	userDAO.updateUser(1, us);
    }
    
	@Test
    public void testGetById() {
        User result = userDAO.getUserById(1);
        assertPropertyLenientEquals("login", "jdoe", result);
        assertPropertyLenientEquals("pwd", "pwdd", result);
        assertPropertyLenientEquals("email", "nobody@nowhere.com", result);
        assertPropertyLenientEquals("fname", "johnny", result);
        assertPropertyLenientEquals("sname", "doe", result);
        assertPropertyLenientEquals("phone", "111223334455", result);
    }
	
	@Test
    public void testGetByLogin() {
        User result = userDAO.getUserByLogin("jdoe");
        assertPropertyLenientEquals("login", "jdoe", result);
        assertPropertyLenientEquals("pwd", "pwdd", result);
        assertPropertyLenientEquals("email", "nobody@nowhere.com", result);
        assertPropertyLenientEquals("fname", "johnny", result);
        assertPropertyLenientEquals("sname", "doe", result);
        assertPropertyLenientEquals("phone", "111223334455", result);
    }
	
    @Test
    public void testGetAllUsers() {
        List<User> result = userDAO.getAllUsers();
        assertPropertyLenientEquals("login", Arrays.asList("jdoe", "resk", "testLgn", "tytyty", "separ", "toDelete"), result);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
   	public void testDeleteUser() {
    	userDAO.deleteUser(6);
    	userDAO.getUserById(6);
   	}
    
    @Test
    public void testGetGroupUsers() {
    	List<User> result = userDAO.getAllGroupUsers(1);
    	assertPropertyLenientEquals("login", Arrays.asList("jdoe", "resk"), result);
    }
    
    @Test
    public void testGetIDByLogin() {
    	int result = userDAO.getIdByLogin("jdoe");
    	assertEquals(1, result);
    }
    @Test
    public void testGetPwdByLogin() {
    	String result = userDAO.getPasswordByLogin("tytyty");
    	assertEquals("pywd", result);
    }
    
    @Test
    public void testGetPwdByEmail() {
    	String result = userDAO.getPasswordByEmail("no3body@nowhere.com");
    	assertEquals("pywd", result);
    }
    
    @Test
    public void testGetLoginById() {
    	String result = userDAO.getLoginById(3);
    	assertEquals("testLgn", result);
    }
    

    @Test
    public void testGetUsersByName() {
    	List<User> result = userDAO.getUsersByName("bonny");
    	assertPropertyLenientEquals("login", Arrays.asList("testLgn", "tytyty"), result);
    }
    
    @Test
    public void testGetUsersBySurname() {
    	List<User> result = userDAO.getUsersBySurname("toge");
    	assertPropertyLenientEquals("login", Arrays.asList("tytyty", "separ"), result);
    }
    
    @Test
    public void testGetUsersByCountry() {
    	List<User> result = userDAO.getUsersByCountry("by");
    	assertPropertyLenientEquals("login", Arrays.asList("jdoe", "resk", "testLgn"), result);
    }
    
    @Test
    public void testGetUsersByCity() {
    	List<User> result = userDAO.getUsersByCity("mn");
    	assertPropertyLenientEquals("login", Arrays.asList("jdoe", "resk"), result);
    }
}
