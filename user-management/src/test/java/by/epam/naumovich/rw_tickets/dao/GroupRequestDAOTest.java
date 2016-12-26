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

import by.epam.naumovich.rw_tickets.dao.iface.IGroupRequestDAO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;

@DataSet("dbunit/DAODataTest.xml")
public class GroupRequestDAOTest extends UnitilsJUnit4 {

	ApplicationContext context;
	private IGroupRequestDAO requestDAO;;
	GroupRequest testRequest;

	@Before    
    public void init() {
		context = new ClassPathXmlApplicationContext("user-module-test.xml");
		requestDAO = (IGroupRequestDAO) context.getBean("groupRequestDao");
    }
	
	public void initTestRequest() {
		testRequest = new GroupRequest();
		testRequest.setRq_type('p');
		testRequest.setFrom_user(4);
		testRequest.setTo_user(1);
		testRequest.setGr_id(1);
		testRequest.setStatus('o');
		testRequest.setRq_comment("test comment 5");
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterAddRequest.xml")
	public void testAddRequest() {
		initTestRequest();
		int reqNum = requestDAO.addGroupRequest(testRequest);
		testRequest.setRq_num(reqNum);
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterUpdRequest.xml")
	public void testUpdateRequest() {
		char newStatus = 'a';
		requestDAO.updateGroupRequest(2, newStatus);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteRequest() {
		requestDAO.deleteGroupRequest(4);
		requestDAO.getGroupRequestByNum(4);
	}
	
	@Test
	public void testGetReqByNum() {
		GroupRequest result = requestDAO.getGroupRequestByNum(2);
		assertPropertyLenientEquals("rq_type", 'p', result);
		assertPropertyLenientEquals("from_user", 3, result);
		assertPropertyLenientEquals("to_user", 1, result);
		assertPropertyLenientEquals("gr_id", 1, result);
		assertPropertyLenientEquals("status", 'o', result);
		assertPropertyLenientEquals("rq_comment", "test comment", result);
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
		assertPropertyLenientEquals("gr_id", Arrays.asList(1), result);
		assertPropertyLenientEquals("rq_comment", Arrays.asList("test comment"), result);
	}
	
	@Test
	public void testGetUserOutReq() {
		List<GroupRequest> result = requestDAO.getUserOutRequests(1);
		assertPropertyLenientEquals("rq_num", Arrays.asList(1, 3), result);
		assertPropertyLenientEquals("gr_id", Arrays.asList(1, 5), result);
		assertPropertyLenientEquals("rq_comment", Arrays.asList("test comment", "test comment"), result);
	}
}
