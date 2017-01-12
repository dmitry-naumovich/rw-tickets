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

import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.dao.impl.GroupDAOImpl;

import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.exception.InvalidInputServiceException;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IGroupService;
import by.epam.naumovich.rw_tickets.service.impl.GroupServiceImpl;

public class GroupServiceTest {

	private static boolean setUpIsDone = false;
	private static IGroupDAO dao;
	private static IGroupService service = new GroupServiceImpl();
	
	private static UserGroup expectedGroup;
	private static List<UserGroup> groups;
	
	@Before
	public void init() {
		if (setUpIsDone) {
			return;
		}
		dao = mock(GroupDAOImpl.class);
		((GroupServiceImpl)service).setGroupDAO(dao);
		initTestGroup();
		initGroupCollection();
		setUpIsDone = true;
	}
	
	public void initTestGroup() {
		expectedGroup = new UserGroup();
		expectedGroup.setGr_id(90);
		expectedGroup.setGr_name("testGroup");
		expectedGroup.setOwner_id(1);
	}
	
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
	public void testAddGroup() throws ServiceException {
		when(dao.addGroup(expectedGroup)).thenAnswer(new Answer<Integer>() {

			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				UserGroup group = (UserGroup) invocation.getArguments()[0];
				return group.getGr_id();
			}
			
		});
		
		assertEquals(90, service.addGroup(expectedGroup));
		verify(dao).addGroup(expectedGroup);
		verify(dao).addGroupMember(expectedGroup.getOwner_id(), expectedGroup.getGr_id());
		verifyNoMoreInteractions(dao);
	}
	
	@Test
	public void testUpdateGroup() throws ServiceException {
		service.updateGroup(expectedGroup);
		verify(dao).updateGroup(90, expectedGroup);
	}
	
	@Test
	public void testDeleteGroup() throws ServiceException {
		service.deleteGroup(10);
		verify(dao).deleteGroup(10);
		verify(dao).removeAllGroupMembers(10);
	}
	
	@Test(expected=InvalidInputServiceException.class)
	public void testGetGroupByID() throws ServiceException {
		when(dao.getGroupById(anyInt())).thenReturn(expectedGroup);
		
		UserGroup actual = service.getGroupByID(2);
		verify(dao).getGroupById(2);
		
		assertEquals(expectedGroup, actual);
		
		service.getGroupByID(-1);
		verify(dao).getGroupById(-1);
		verifyNoMoreInteractions(dao);
	}
	
	@Test
	public void testAddGroupMember() throws ServiceException {
		service.addGroupMember(1, 10);
		verify(dao).addGroupMember(1, 10);
	}
	
	@Test
	public void testRemoveGroupMember() throws ServiceException {
		service.removeGroupMember(1, 10);
		verify(dao).removeGroupMember(1, 10);
	}
	
	@Test
	public void testGetGroupsByUser() throws ServiceException {
		when(dao.getGroupsByUser(anyInt())).thenReturn(groups);
		List<UserGroup> actual = service.getGroupsByUser(2);
		assertThat(actual, is(groups));
		verify(dao).getGroupsByUser(2);
	}
	
	@Test
	public void deleteAllGroupsByOwner() throws ServiceException {
		service.deleteAllGroupsByOwner(2);
		verify(dao).getGroupsByOwner(2);
	}
}
