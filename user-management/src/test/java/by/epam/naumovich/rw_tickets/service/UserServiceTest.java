package by.epam.naumovich.rw_tickets.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.dao.impl.UserDAOImpl;
import by.epam.naumovich.rw_tickets.dao.impl.GroupDAOImpl;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.iface.IUserService;
import by.epam.naumovich.rw_tickets.service.impl.UserServiceImpl;

public class UserServiceTest {

	private static boolean setUpIsDone = false;
	private static IUserDAO dao;
	private static IGroupDAO groupDAO;
	
	private static IUserService service = new UserServiceImpl();
	
	private static User expectedUser;
	private static List<User> userCollection;
	
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
	
	public void initTestUser() {
		expectedUser = new User();
		expectedUser.setId(2);
		expectedUser.setLogin("vasya");
		expectedUser.setFname("nama");
	}
	
	public void initUserCollection() {
		userCollection = new ArrayList<User>();
		
		User sec = new User();
		sec.setId(3);
		sec.setLogin("second");
		sec.setFname("secName");

		User third = new User();
		third.setId(4);
		third.setLogin("loggy");
		third.setFname("thirName");

		userCollection = new ArrayList<User>();

		userCollection.add(expectedUser);
		userCollection.add(sec);
		userCollection.add(third);	
	}
	
	@Test
	public void testAddUser() {		
		when(dao.addUser(expectedUser)).thenAnswer(new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				User user = (User) invocation.getArguments()[0];
				return user.getId();
			}
		});
		
		assertEquals(expectedUser.getId(), service.addUser(expectedUser));
		verify(dao).addUser(expectedUser);
	}
	
	@Test 
	public void testUpdateUser() {
		service.updateUser(expectedUser);
		verify(dao).updateUser(2, expectedUser);
	}
	
	@Test
	public void testDeleteUser() {
		service.deleteUser(1);
		verify(dao).deleteUser(1);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetUserByID() {
		when(dao.getUserById(anyInt())).thenReturn(expectedUser);
		when(dao.getUserById(-1)).thenThrow(RuntimeException.class);
		
		User actual = service.getUserById(1);
		verify(dao).getUserById(1);
		
		assertEquals(expectedUser, actual);
		
		service.getUserById(-1);
		verify(dao).getUserById(-1);
		verifyNoMoreInteractions(dao);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetUserByLogin() {
		when(dao.getUserByLogin(anyString())).thenReturn(expectedUser);
		when(dao.getUserByLogin(null)).thenThrow(RuntimeException.class);
		
		User actual = service.getUserByLogin("vasya");
		verify(dao).getUserByLogin("vasya");
		
		assertEquals(expectedUser, actual);
		
		service.getUserByLogin(null);
		verify(dao).getUserByLogin(null);
		verifyNoMoreInteractions(dao);
	}
	
	@Test
	public void testGetAllUsers() {
		service.getAllUsers();
		verify(dao).getAllUsers();
	}
	
	@Test
	public void testGetGroupMembers() {
		service.getAllGroupMembers(1);
		verify(dao).getAllGroupMembers(1);
	}
	
	@Test
	public void testGetPasswordByLogin() {
		service.getPasswordByLogin("loginT");
		verify(dao).getPasswordByLogin("loginT");
	}
	
	@Test
	public void getPasswordByEmail() {
		service.getPasswordByEmail("email@mail.com");
		verify(dao).getPasswordByEmail("email@mail.com");
	}
	
	@Test
	public void testGetLoginById() {
		String expected = "testLg";
		when(dao.getLoginById(8)).thenReturn(expected);
		String actual = service.getLoginById(8);
		assertEquals(expected, actual);
	}
}
