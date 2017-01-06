package by.epam.naumovich.rw_tickets.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.*;

import by.epam.naumovich.rw_tickets.dao.iface.IGroupDAO;
import by.epam.naumovich.rw_tickets.entity.UserGroup;

@DataSet("dbunit/DAODataTest.xml")
public class UserGroupDAOTest extends UnitilsJUnit4 {

	ApplicationContext context;
	private IGroupDAO usGroupDAO;
	UserGroup testGroup;

	@Before    
    public void init() {
		context = new ClassPathXmlApplicationContext("user-module-test.xml");
		usGroupDAO = (IGroupDAO) context.getBean("groupDao");
    }
	
	public void initTestGroup() {
		testGroup = new UserGroup();
		testGroup.setGr_name("testName");
		testGroup.setOwner_id(2);
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterAddGroup.xml")
	public void testAddGroup() {
		initTestGroup();
		usGroupDAO.addUserGroup(testGroup);
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterUpdGroup.xml")
	public void testUpdateGroup() {
		UserGroup group = usGroupDAO.getUserGroupById(2);
		group.setGr_name("updName");
		usGroupDAO.updateUserGroup(2, group);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteGroup() {
		usGroupDAO.deleteGroup(6);
		usGroupDAO.getUserGroupById(6);
	}
	
	@Test
	public void testGetGroupById() {
		UserGroup result = usGroupDAO.getUserGroupById(1);
		assertPropertyLenientEquals("gr_name", "TestGrName1", result);
		assertPropertyLenientEquals("owner_id", 1, result);
	}
	
	@Test
	public void testGetGroupIdByNameAndOwner() {
		int result = usGroupDAO.getGroupIdByNameAndOwner("TestGrName2", 2);
    	assertEquals(2, result);
	}
	
	@Test
	public void testGetGroupNameByID() {
		String result = usGroupDAO.getGroupNameById(4);
		assertEquals("TestGrName4", result);
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterAddGroupUser.xml")
	public void testAddUserToGroup() {
		usGroupDAO.addUserToGroup(3, 2);
	}
	
	@Test
	public void testDeleteUserFromGroup() {
		usGroupDAO.deleteUserFromGroup(2, 1);
		List<UserGroup> groups = usGroupDAO.getGroupsByUser(2);
		assertEquals(groups.size(), 1);
	}
	
	@Test
	public void testGetUserGroups() {
		List<UserGroup> result = usGroupDAO.getGroupsByUser(1);
		assertPropertyLenientEquals("gr_id", Arrays.asList(1, 5), result);
		assertPropertyLenientEquals("gr_name", Arrays.asList("TestGrName1", "TestGrName5"), result);
	}
	
	@Test
	public void testGetGroupsByOwner() {
		List<UserGroup> result = usGroupDAO.getGroupsByOwner(1);
		assertPropertyLenientEquals("gr_id", Arrays.asList(1,5), result);
		assertPropertyLenientEquals("gr_name", Arrays.asList("TestGrName1", "TestGrName5"), result);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testDeleteAllGroupsByOwner() {
		usGroupDAO.deleteAllGroupsByOwner(5);
		usGroupDAO.getUserGroupById(7);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testDeleteUserFromAllGroups() {
		usGroupDAO.deleteUserFromAllGroups(5);
		usGroupDAO.getGroupsByUser(5).get(0);
	}
}