package by.epam.naumovich.rw_tickets.dao;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import by.epam.naumovich.rw_tickets.dao.iface.IRequestDAO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;

/**
 * This unit testing class tests the IRequestDAO interface implementation which is injected by the Spring IOC technology.
 * The test data is loaded automatically from the XML file.
 * Test class is set up with the help of the DBUnit framework.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@DataSet("dbunit/DAODataTest.xml")
public class RequestDAOTest extends UnitilsJUnit4 {

	public static final String SPRING_TEST_CONFIG_FILE = "user-module-test.xml";
	public static final String SPRING_DAO_BEAN_NAME = "requestDao";
	
	private static boolean setUpIsDone = false;
	private static ApplicationContext context;
	private static IRequestDAO requestDAO;;
	private static GroupRequest testRequest;

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
		requestDAO = (IRequestDAO) context.getBean(SPRING_DAO_BEAN_NAME);
		initTestRequest();
		setUpIsDone = true;
    }
	
	public void initTestRequest() {
		testRequest = new GroupRequest();
		testRequest.setFrom_user(4);
		testRequest.setTo_user(1);
		testRequest.setGrId(1);
		testRequest.setStatus('o');
		testRequest.setComment("test comment 5");
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterAddRequest.xml")
	public void testAddRequest() {
		int reqNum = requestDAO.addRequest(testRequest);
		testRequest.setRq_num(reqNum);
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterUpdRequest.xml")
	public void testUpdateRequest() {
		char newStatus = 'a';
		requestDAO.updateRequest(2, newStatus);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteRequest() {
		requestDAO.deleteRequest(4);
		requestDAO.getRequestByNum(4);
	}
	
	@Test
	public void testGetReqByNum() {
		GroupRequest result = requestDAO.getRequestByNum(2);
		assertPropertyLenientEquals("from_user", 3, result);
		assertPropertyLenientEquals("to_user", 1, result);
		assertPropertyLenientEquals("id", 1, result);
		assertPropertyLenientEquals("status", 'o', result);
		assertPropertyLenientEquals("comment", "test comment", result);
	}
	
	@Test
	public void testGetNumByIDs() {
		int result = requestDAO.getReqNumByUserAndGroupIDs(1, 2, 5);
		assertEquals(3, result);
	}
	
	@Test
	public void testGetUserIncReq() {
		List<GroupRequest> result = requestDAO.getUserIncRequests(1);
		assertPropertyLenientEquals("rq_num", Arrays.asList(2), result);
		assertPropertyLenientEquals("id", Arrays.asList(1), result);
		assertPropertyLenientEquals("comment", Arrays.asList("test comment"), result);
	}
	
	@Test
	public void testGetUserOutReq() {
		List<GroupRequest> result = requestDAO.getUserOutRequests(1);
		assertPropertyLenientEquals("rq_num", Arrays.asList(1, 3), result);
		assertPropertyLenientEquals("id", Arrays.asList(1, 5), result);
		assertPropertyLenientEquals("comment", Arrays.asList("test comment", "test comment"), result);
	}
}
