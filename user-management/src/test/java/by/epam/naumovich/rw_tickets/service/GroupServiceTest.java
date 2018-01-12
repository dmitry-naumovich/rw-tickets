package by.epam.naumovich.rw_tickets.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;

import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IGroupService;
import by.epam.naumovich.rw_tickets.service.impl.GroupServiceImpl;
import org.mockito.Mock;

/**
 * Tests the IGroupService interface implementation which is injected by the Spring IOC technology.
 * Test class is set up with the help of the Mockito framework.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class GroupServiceTest {

	private static boolean setUpIsDone = false;
	@Mock
	private IGroupDAO dao;
	private IGroupService service;
	
	private UserGroup expectedGroup;
	private List<UserGroup> groups;
	
	@Before
	public void init() {
		if (setUpIsDone) {
			return;
		}
        service = new GroupServiceImpl(dao);
		initTestGroup();
		initGroupCollection();
		setUpIsDone = true;
	}
	
	private void initTestGroup() {
		expectedGroup = new UserGroup();
		expectedGroup.setId(90);
		expectedGroup.setName("testGroup");
		expectedGroup.setOwner(1);
	}

	private void initGroupCollection() {
		groups = new ArrayList<>();
		
		UserGroup group = new UserGroup();
		group.setId(8);
		group.setName("test2");
		group.setOwner(2);
		
		UserGroup group2 = new UserGroup();
		group2.setId(9);
		group2.setName("test3");
		group2.setOwner(7);
		
		groups.add(expectedGroup);
		groups.add(group);
		groups.add(group2);
	}
	
	@Test
	public void testAddGroup() throws ServiceException {
		when(dao.addGroup(expectedGroup)).thenAnswer(invocation -> {
					UserGroup group = (UserGroup) invocation.getArguments()[0];
					return group.getId();
				});
		
		assertEquals(90, service.addGroup(expectedGroup));
		verify(dao).addGroup(expectedGroup);
		verify(dao).addGroupMember(expectedGroup.getOwner(), expectedGroup.getId());
		verifyNoMoreInteractions(dao);
	}
	
	@Test
	public void testUpdateGroup() throws ServiceException {
		expectedGroup.setCreateDate(new Date(1L));
		expectedGroup.setCreateTime(new Time(1L));
		service.updateGroup(expectedGroup);
		verify(dao).updateGroup(90, expectedGroup);
	}
	
	@Test
	public void testDeleteGroup() throws ServiceException {
		service.deleteGroup(10);
		verify(dao).deleteGroup(10);
		verify(dao).removeAllGroupMembers(10);
	}
	
	@Test(expected=ServiceException.class)
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
