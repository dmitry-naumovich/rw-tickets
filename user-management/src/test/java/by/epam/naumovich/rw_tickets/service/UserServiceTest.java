package by.epam.naumovich.rw_tickets.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IUserService;
import by.epam.naumovich.rw_tickets.service.impl.UserServiceImpl;
import org.mockito.Mock;

/**
 * Tests the IUserService interface implementation which is injected by the Spring IOC technology.
 * Test class is set up with the help of the Mockito framework.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class UserServiceTest {

	@Mock
	private IUserDAO userDAO;
	@Mock
	private IGroupDAO groupDAO;
	
	private IUserService service;
	
	private User expectedUser;
	private List<User> userCollection;
	
	@Before
	public void init() {
        service = new UserServiceImpl(userDAO, groupDAO);
		initTestUser();
		initUserCollection();
	}
	
	private void initTestUser() {
		expectedUser = new User();
		expectedUser.setId(2);
		expectedUser.setLogin("vasya");
		expectedUser.setFname("nama");
		expectedUser.setEmail("email");
		expectedUser.setSname("snama");
		expectedUser.setPassport("11");
		expectedUser.setPwd("pws");
	}

	private void initUserCollection() {
		userCollection = new ArrayList<>();
		
		User sec = new User();
		sec.setId(3);
		sec.setLogin("second");
		sec.setFname("secName");

		User third = new User();
		third.setId(4);
		third.setLogin("loggy");
		third.setFname("thirName");

		userCollection = new ArrayList<>();

		userCollection.add(expectedUser);
		userCollection.add(sec);
		userCollection.add(third);	
	}
	
	@Test
	public void testAddUser() throws ServiceException {		
		when(userDAO.addUser(expectedUser)).thenAnswer(invocation -> {
				User user = (User) invocation.getArguments()[0];
				return user.getId();
			});
		
		assertEquals(expectedUser.getId(), service.addUser(expectedUser));
		verify(userDAO).addUser(expectedUser);
	}
	
	@Test 
	public void testUpdateUser() throws ServiceException {
		service.updateUser(expectedUser);
		verify(userDAO).updateUser(2, expectedUser);
	}
	
	@Test
	public void testDeleteUser() throws ServiceException {
		service.deleteUser(1);
		verify(userDAO).deleteUser(1);
	}
	
	@Test(expected=ServiceException.class)
	public void testGetUserByID() throws ServiceException {
		when(userDAO.getUserById(anyInt())).thenReturn(expectedUser);
		
		User actual = service.getUserById(1);
		verify(userDAO).getUserById(1);
		
		assertEquals(expectedUser, actual);
		
		service.getUserById(-1);
		verify(userDAO).getUserById(-1);
		verifyNoMoreInteractions(userDAO);
	}
	
	@Test(expected=ServiceException.class)
	public void testGetUserByLogin() throws ServiceException {
		when(userDAO.getUserByLogin(anyString())).thenReturn(expectedUser);
		
		User actual = service.getUserByLogin("vasya");
		verify(userDAO).getUserByLogin("vasya");
		
		assertEquals(expectedUser, actual);
		
		service.getUserByLogin(null);
		verify(userDAO).getUserByLogin(null);
		verifyNoMoreInteractions(userDAO);
	}
	
	@Test
	public void testGetAllUsers() throws ServiceException {
		service.getAllUsers();
		verify(userDAO).getAllUsers();
	}
	
	@Test
	public void testGetGroupMembers() throws ServiceException {
		service.getAllGroupMembers(1);
		verify(userDAO).getAllGroupMembers(1);
	}
	
	@Test
	public void testAuthenticateByLogin() throws ServiceException {
		service.authenticateByLogin("loginT", "pws");
		verify(userDAO).getUserByLogin("loginT");
		
	}
	
	@Test
	public void testAuthenticateByEmail() throws ServiceException {
		when(userDAO.getUserByEmail(anyString())).thenReturn(expectedUser);
		service.authenticateByEmail("email@mail.com", "pws");
		verify(userDAO).getUserByEmail("email@mail.com");
	}
	
	@Test
	public void testGetLoginById() throws ServiceException {
		String expected = "testLg";
		when(userDAO.getLoginById(8)).thenReturn(expected);
		String actual = service.getLoginById(8);
		assertEquals(expected, actual);
	}
}
