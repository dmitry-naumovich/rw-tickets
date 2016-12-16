package by.epam.naumovich.rw_tickets.dao;

import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
		testRequest.setType('p');
		testRequest.setFromUser(4);
		testRequest.setToUser(1);
		testRequest.setGroupId(1);
		testRequest.setComment("test comment 5");
		testRequest.setCreateDate(Date.valueOf(LocalDate.now()));
		testRequest.setCreateTime(Time.valueOf(LocalTime.now()));
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterAddRequest.xml")
	public void testAddRequest() {
		initTestRequest();
		int reqNum = requestDAO.addGroupRequest(testRequest);
		testRequest.setNum(reqNum);
	}
	
	@Test
	@ExpectedDataSet("dbunit/AfterUpdRequest.xml")
	public void testUpdateRequest() {
		GroupRequest req = requestDAO.getGroupRequestById(1);
		req.setStatus('o');
		req.setComment("test comment upd");
		requestDAO.updateGroupRequest(1, req);
	}
	
	/*@Test
	public void testDeleteRequest() {
		requestDAO.deleteGroupRequest(3);
	}*/
	
	@Test
	public void testGetReqById() {
		GroupRequest result = requestDAO.getGroupRequestById(2);
		assertPropertyLenientEquals("rq_type", "p", result);
		assertPropertyLenientEquals("from_user", "3", result);
		assertPropertyLenientEquals("to_user", "1", result);
		assertPropertyLenientEquals("gr_id", "1", result);
		assertPropertyLenientEquals("status", "o", result);
		assertPropertyLenientEquals("rq_comment", "test comment", result);
	}
	
	@Test
	public void testGetUserIncReq() {
		List<GroupRequest> result = requestDAO.getUserIncRequests(1);
		assertPropertyLenientEquals("rq_num", Arrays.asList("2"), result);
		assertPropertyLenientEquals("gr_id", Arrays.asList("1"), result);
		assertPropertyLenientEquals("rq_comment", Arrays.asList("test comment"), result);
	}
	
	@Test
	public void testGetUserOutReq() {
		List<GroupRequest> result = requestDAO.getUserIncRequests(1);
		assertPropertyLenientEquals("rq_num", Arrays.asList("1", "3"), result);
		assertPropertyLenientEquals("gr_id", Arrays.asList("1", "5"), result);
		assertPropertyLenientEquals("rq_comment", Arrays.asList("test comment", "test comment"), result);
	}
}
