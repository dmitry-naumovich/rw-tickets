package by.epam.naumovich.rw_tickets.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IUserGroupDAO;
import by.epam.naumovich.rw_tickets.dao.impl.UserGroupDAOImpl;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.iface.IUserGroupService;
import by.epam.naumovich.rw_tickets.service.impl.UserGroupServiceImpl;

public class UserGroupServiceTest {

	private IUserGroupDAO dao;
	IUserGroupService service = new UserGroupServiceImpl();
	
	private UserGroup expectedGroup;
	private List<UserGroup> groups;
	
	@Before
	public void init() {
		dao = mock(UserGroupDAOImpl.class);
		((UserGroupServiceImpl)service).setGroupDAO(dao);
		expectedGroup = new UserGroup();
		expectedGroup.setGr_id(90);
		expectedGroup.setGr_name("testGroup");
		expectedGroup.setOwner_id(1);
	}
	
	@Before
	public void initGroupCollection() {
		groups = new ArrayList<UserGroup>();
		
		UserGroup group = new UserGroup();
		group.setGr_id(8);
		group.setGr_name("test2");
		group.setOwner_id(2);
		
		UserGroup group2 = new UserGroup();
		group2.setGr_id(9);
		group2.setGr_name("test3");
		group2.setOwner_id(7);
		
		groups.add(expectedGroup);
		groups.add(group);
		groups.add(group2);
	}
	
	@Test
	public void testAddGroup() {
		when(dao.addUserGroup(expectedGroup)).thenAnswer(new Answer<Integer>() {

			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				UserGroup group = (UserGroup) invocation.getArguments()[0];
				return group.getGr_id();
			}
			
		});
		
		assertEquals(90, service.addGroup(expectedGroup));
		verify(dao).addUserGroup(expectedGroup);
		verifyNoMoreInteractions(dao);
	}
	
	@Test
	public void testUpdateGroup() {
		expectedGroup.setGr_id(13);
		service.updateGroup(expectedGroup);
		verify(dao).updateUserGroup(13, expectedGroup);
	}
	
	@Test
	public void testDeleteGroup() {
		service.deleteGroup(10);
		verify(dao).deleteUserGroup(10);
		//verify all DELETE USER FROM GROUP method also caleld
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetGroupByID() {
		when(dao.getUserGroupById(anyInt())).thenReturn(expectedGroup);
		when(dao.getUserGroupById(-1)).thenThrow(RuntimeException.class);
		
		UserGroup actual = service.getGroupByID(2);
		verify(dao).getUserGroupById(2);
		
		assertEquals(expectedGroup, actual);
		
		service.getGroupByID(-1);
		verify(dao).getUserGroupById(-1);
		verifyNoMoreInteractions(dao);
	}
	
	@Test
	public void testAddUserToGroup() {
		service.addUserToGroup(1, 10);
		verify(dao).addUserToGroup(1, 10);
	}
	
	@Test
	public void testDeleteUserFromGroup() {
		service.deleteUserFromGroup(1, 10);
		verify(dao).deleteUserFromGroup(1, 10);
	}
	
	@Test
	public void testGetUserGroups() {
		when(dao.getGroupsByUser(anyInt())).thenReturn(groups);
		List<UserGroup> actual = service.getGroupsByUser(2);
		assertThat(actual, is(groups));
		verify(dao).getGroupsByUser(2);
	}
}
