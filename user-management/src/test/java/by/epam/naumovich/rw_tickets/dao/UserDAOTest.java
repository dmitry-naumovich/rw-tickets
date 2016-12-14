package by.epam.naumovich.rw_tickets.dao;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import static org.unitils.reflectionassert.ReflectionAssert.*;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.entity.User;


@DataSet("UserDAOTest.xml")
public class UserDAOTest extends UnitilsJUnit4 {
	
	/*@TestDataSource
    private DataSource dataSource;*/
	
	private IUserDAO userDAO;
	User testUser;

	@Before    
    public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("user-module-test.xml");
		System.out.println("context here");
		userDAO = (IUserDAO) context.getBean("userDao");
		testUser = new User();
		testUser.setId(9999);
		testUser.setLogin("testLogin");
    	testUser.setFname("testname");
    	testUser.setPwd("pwd");
    	testUser.setSname("testSurname");
    	testUser.setEmail("test@email.com");
    	testUser.setPassport("TST19000");
    	testUser.setAddress("test adress street 45");
    	testUser.setAdmin(false);
    }
/*	
	
	@Test
    public void testGetById() {
        User result = userDAO.getUserById(1000);
        assertPropertyLenientEquals("login", "jdoe", result);
        assertPropertyLenientEquals("pwd", "pwdd", result);
        assertPropertyLenientEquals("email", "nobody@nowhere.com", result);
        assertPropertyLenientEquals("fname", "johnny", result);
        assertPropertyLenientEquals("sname", "doe", result);
        assertPropertyLenientEquals("phone", "111223334455", result);
    }*/
	
	@Test
    public void testGetByLogin() {
		System.out.println("testGetById() method");
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
        if (result.isEmpty()) {
        	System.out.println("eee");
        }
        assertPropertyLenientEquals("login", Arrays.asList("jdoe", "resk", "testLgn", "tytyty", "separ"), result);
    }
    
    @Test
    @DatabaseSetup("UserDAOTest.xml")
    @ExpectedDataSet({"dbunit/AddUserTest.xml"})
    public void testAddUser() {
    	userDAO.addUser(testUser);
    }
    
    /*@Test
    @ExpectedDataSet({"dbunit/UpdUserTest.xml"})
    public void testUpdateUser() {
    	testUser.setLogin("noow");
    	userDAO.updateUser(1000, testUser);
    }*/
    
   /* @Test(expected = EmptyResultDataAccessException.class)
   	public void testDeleteUser() {
    	userDAO.deleteUser(1300);
    	userDAO.getUserById(3000);
    }*/
    
    @Test
    public void testGetPwdByLogin() {
    	String result = userDAO.getPasswordByLogin("tytyty");
    	assertPropertyLenientEquals("pwd", "pywd", result);
    }
    
    @Test
    public void testGetPwdByEmail() {
    	String result = userDAO.getPasswordByLogin("no3body@nowhere.com");
    	assertPropertyLenientEquals("pwd", "pywd", result);
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
