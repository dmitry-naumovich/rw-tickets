package by.epam.naumovich.rw_tickets.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.dao.impl.UserDAOImpl;
import by.epam.naumovich.rw_tickets.dao.impl.GroupDAOImpl;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IUserService;
import by.epam.naumovich.rw_tickets.service.impl.UserServiceImpl;

/**
 * Tests the IUserService interface implementation which is injected by the Spring IOC technology.
 * Test class is set up with the help of the Mockito framework.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class UserServiceTest {

	private static boolean setUpIsDone = false;
	private IUserDAO dao;
	private IGroupDAO groupDAO;
	
	private IUserService service = new UserServiceImpl();
	
	private User expectedUser;
	private List<User> userCollection;
	
	@Before
	public void init() {
		if (setUpIsDone) {
			return;
		}
		dao = mock(UserDAOImpl.class);
		groupDAO = mock(GroupDAOImpl.class);
		((UserServiceImpl)service).setUserDAO(dao);
		((UserServiceImpl)service).setGroupDAO(groupDAO);
		initTestUser();
		initUserCollection();
		setUpIsDone = true;
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
		when(dao.addUser(expectedUser)).thenAnswer(invocation -> {
				User user = (User) invocation.getArguments()[0];
				return user.getId();
			});
		
		assertEquals(expectedUser.getId(), service.addUser(expectedUser));
		verify(dao).addUser(expectedUser);
	}
	
	@Test 
	public void testUpdateUser() throws ServiceException {
		service.updateUser(expectedUser);
		verify(dao).updateUser(2, expectedUser);
	}
	
	@Test
	public void testDeleteUser() throws ServiceException {
		service.deleteUser(1);
		verify(dao).deleteUser(1);
	}
	
	@Test(expected=ServiceException.class)
	public void testGetUserByID() throws ServiceException {
		when(dao.getUserById(anyInt())).thenReturn(expectedUser);
		
		User actual = service.getUserById(1);
		verify(dao).getUserById(1);
		
		assertEquals(expectedUser, actual);
		
		service.getUserById(-1);
		verify(dao).getUserById(-1);
		verifyNoMoreInteractions(dao);
	}
	
	@Test(expected=ServiceException.class)
	public void testGetUserByLogin() throws ServiceException {
		when(dao.getUserByLogin(anyString())).thenReturn(expectedUser);
		
		User actual = service.getUserByLogin("vasya");
		verify(dao).getUserByLogin("vasya");
		
		assertEquals(expectedUser, actual);
		
		service.getUserByLogin(null);
		verify(dao).getUserByLogin(null);
		verifyNoMoreInteractions(dao);
	}
	
	@Test
	public void testGetAllUsers() throws ServiceException {
		service.getAllUsers();
		verify(dao).getAllUsers();
	}
	
	@Test
	public void testGetGroupMembers() throws ServiceException {
		service.getAllGroupMembers(1);
		verify(dao).getAllGroupMembers(1);
	}
	
	@Test
	public void testAuthenticateByLogin() throws ServiceException {
		service.authenticateByLogin("loginT", "pws");
		verify(dao).getUserByLogin("loginT");
		
	}
	
	@Test
	public void testAuthenticateByEmail() throws ServiceException {
		when(dao.getUserByEmail(anyString())).thenReturn(expectedUser);
		service.authenticateByEmail("email@mail.com", "pws");
		verify(dao).getUserByEmail("email@mail.com");
	}
	
	@Test
	public void testGetLoginById() throws ServiceException {
		String expected = "testLg";
		when(dao.getLoginById(8)).thenReturn(expected);
		String actual = service.getLoginById(8);
		assertEquals(expected, actual);
	}
}
