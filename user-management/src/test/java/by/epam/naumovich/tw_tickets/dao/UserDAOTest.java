package by.epam.naumovich.tw_tickets.dao;

import java.util.Arrays;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.*;

import by.epam.naumovich.rw_tickets.dao.exception.DAOException;
import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.impl.UserDAOImpl;
import by.epam.naumovich.rw_tickets.entity.User;

@DataSet({"UserDAOTest.xml"})
public class UserDAOTest extends UnitilsJUnit4 {
	
	private DataSource dataSource;
	private IUserDAO userDAO;
	
	@Before
	public void init() {
		userDAO = new UserDAOImpl();
	}
	
	@Test
    public void testGetById() throws DAOException {
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
        Set<User> result = userDAO.getAllUsers();    
        assertPropertyLenientEquals("login", Arrays.asList("jdoe", "resk", "testLgn", "tytyty"), result);
    }
    
    @Test
    public void testAddUser() throws DAOException {
    	User testUser = new User();
    	testUser.setId(9999);
    	testUser.setLogin("testLogin");
    	
    	userDAO.addUser(testUser);
    	User result = userDAO.getUserById(9999);
    	assertEquals(testUser.getLogin(), result.getLogin());
    }

}
