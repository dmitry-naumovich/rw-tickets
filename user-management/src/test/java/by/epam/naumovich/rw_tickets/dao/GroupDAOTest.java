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

/**
 * This unit testing class tests the IGroupDAO interface implementation which is injected by the Spring IOC technology.
 * The test data is loaded automatically from the XML file.
 * Test class is set up with the help of the DBUnit framework.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@DataSet("dbunit/DAODataTest.xml")
public class GroupDAOTest extends UnitilsJUnit4 {

	public static final String SPRING_TEST_CONFIG_FILE = "user-module-test.xml";
	public static final String SPRING_DAO_BEAN_NAME = "groupDao";
	
	private static boolean setUpIsDone = false;
	private static ApplicationContext context;
	private static IGroupDAO usGroupDAO;
	private static UserGroup testGroup;
	

	/**
	 * The methods initializes Test static fields once.
	 * 
	 */
	@Before    
    public void init() {
		if (setUpIsDone) {
			return;
		}
		context = new ClassPathXmlApplicationContext(SPRING_TEST_CONFIG_FILE);
		usGroupDAO = (IGroupDAO) context.getBean(SPRING_DAO_BEAN_NAME);
		initTestGroup();
		setUpIsDone = true;
    }
	
	public void initTestGroup() {
		testGroup = new UserGroup();
		testGroup.setGr_name("testName");
		testGroup.setOwner_id(2);
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterAddGroup.xml")
	public void testAddGroup() {
		usGroupDAO.addGroup(testGroup);
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterUpdGroup.xml")
	public void testUpdateGroup() {
		UserGroup group = usGroupDAO.getGroupById(2);
		group.setGr_name("updName");
		usGroupDAO.updateGroup(2, group);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteGroup() {
		usGroupDAO.deleteGroup(6);
		usGroupDAO.getGroupById(6);
	}
	
	@Test
	public void testGetGroupById() {
		UserGroup result = usGroupDAO.getGroupById(1);
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
	public void testAddGroupMember() {
		usGroupDAO.addGroupMember(3, 2);
	}
	
	@Test
	public void testRemoveGroupMember() {
		usGroupDAO.removeGroupMember(2, 1);
		List<UserGroup> groups = usGroupDAO.getGroupsByUser(2);
		assertEquals(groups.size(), 1);
	}
	
	@Test
	public void testGetGroupsByUser() {
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
		usGroupDAO.getGroupById(7);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveUserFromAllGroups() {
		usGroupDAO.removeUserFromAllGroups(5);
		usGroupDAO.getGroupsByUser(5).get(0);
	}
}
