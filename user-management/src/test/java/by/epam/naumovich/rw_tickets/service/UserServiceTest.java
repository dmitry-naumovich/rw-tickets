package by.epam.naumovich.rw_tickets.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import by.epam.naumovich.rw_tickets.dao.iface.IUserDAO;
import by.epam.naumovich.rw_tickets.dao.impl.UserDAOImpl;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.iface.IUserService;
import by.epam.naumovich.rw_tickets.service.impl.UserServiceImpl;

public class UserServiceTest {

	//ApplicationContext context;
	
	private IUserDAO dao;
	IUserService service = new UserServiceImpl();
	
	private User expectedUser;
	
	@Before
	public void init() {
		//context = new ClassPathXmlApplicationContext("user-module-test.xml");
		//dao = (IUserDAO) context.getBean("userDao");
		dao = mock(UserDAOImpl.class);
		((UserServiceImpl)service).setUserDAO(dao);
		//service = new Service/*(IUserService) context.getBean("userServiceTarget");*/
		expectedUser = new User();
		expectedUser.setLogin("vasya");
		expectedUser.setFname("nama");
	}
	
	@Test
	public void testAddUser() {
		when(dao.addUser(expectedUser)).thenReturn(12);
		
		/*when(dao.addUser(testUser)).thenAnswer(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				User user = (User) invocation.getArguments()[0];
				return user.getLogin();
			}
		});*/
		
		assertEquals(12, service.addUser(expectedUser));
		verify(dao).addUser(expectedUser);
	    //assertTrue(service.addUser(testUser) > 0);
	    //assertNotNull(service.addUser(testUser));
	}
	
	@Test 
	public void testUpdateUser() {
		expectedUser.setId(1);
		service.updateUser(expectedUser);
		verify(dao).updateUser(1, expectedUser);
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
	}
	
	@Test
	public void testGetAllUsers() {
		service.getAllUsers();
		verify(dao).getAllUsers();
	}
	
	@Test
	public void testGetGroupUsers() {
		service.getAllGroupUsers(1);
		verify(dao).getAllGroupUsers(1);
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
	
	
	
}
