package by.epam.naumovich.tw_tickets.dao;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.*;

import by.epam.naumovich.rw_tickets.dao.exception.DAOException;
import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.impl.UserDAOImpl;
import by.epam.naumovich.rw_tickets.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:user-module-test.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
       DirtiesContextTestExecutionListener.class,
      TransactionalTestExecutionListener.class,
       DbUnitTestExecutionListener.class })
@DataSet({"UserDAOTest.xml"})
public class UserDAOTest extends UnitilsJUnit4 {
	
	//private IUserDAO userDAO;
	//User testUser = new User();
	@TestDataSource
	private DataSource dataSource;
	
	@Before
	public void init() {
		System.out.println("IS IT?");
	}
	
	@Test
    public void testGetById() throws DAOException {
		System.out.println("IS IT SHIT");
		ApplicationContext context = new ClassPathXmlApplicationContext("user-module-test.xml");
		IUserDAO userDAO = (IUserDAO) context.getBean("userDao");
        User result = userDAO.getUserById(999);
        assertPropertyLenientEquals("login", "jdoe", result);
        assertPropertyLenientEquals("password", "pwdd", result);
        assertPropertyLenientEquals("email", "nobody@nowhere.com", result);
        assertPropertyLenientEquals("name", "johnny", result);
        assertPropertyLenientEquals("surname", "doe", result);
        assertPropertyLenientEquals("phone", "111223334455", result);
    }
	
	/*@Before
	public void init() {
		System.out.println("IS IT?");
		ApplicationContext context = new ClassPathXmlApplicationContext("user-module-test.xml");
		userDAO = (IUserDAO) context.getBean("userDao");
    	testUser.setLogin("testLogin");
    	testUser.setName("testname");
    	testUser.setPassword("pwd");
    	testUser.setSurname("testSurname");
    	testUser.setEmail("test@email.com");
    	testUser.setPassport("TST19000");
    	testUser.setAddress("test adress street 45");
    	testUser.setAdmin(false);
    	
    	IUserDAO dao = (IUserDAO) context.getBean("userDao");
		try {
			dao.addUser(testUser);
		} catch (DAOException e) {
			System.out.println("DAOException occured");
		}
    	
	}
	
	
	@Test
    public void testGetById() throws DAOException {
		System.out.println("IS IT SHIT");
        User result = userDAO.getUserById(999);
        assertPropertyLenientEquals("login", "jdoe", result);
        assertPropertyLenientEquals("password", "pwdd", result);
        assertPropertyLenientEquals("email", "nobody@nowhere.com", result);
        assertPropertyLenientEquals("name", "johnny", result);
        assertPropertyLenientEquals("surname", "doe", result);
        assertPropertyLenientEquals("phone", "111223334455", result);
    }

	@Test
    public void testGetByLogin() throws DAOException {
        User result = userDAO.getUserByLogin("jdoe");
        assertPropertyLenientEquals("userName", "jdoe", result);
    }
	
    @Test
    public void testGetAllUsers() throws DAOException {
        List<User> result = userDAO.getAllUsers();    
        assertPropertyLenientEquals("login", Arrays.asList("jdoe", "resk", "testLgn", "tytyty"), result);
    }
    
    @Test
    @ExpectedDataSet ({"AddUserTest.xml"})
    public void testAddUser() throws DAOException {
    	userDAO.addUser(testUser);
    }*/

}
