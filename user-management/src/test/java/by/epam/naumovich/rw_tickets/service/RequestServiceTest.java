package by.epam.naumovich.rw_tickets.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import by.epam.naumovich.rw_tickets.dao.iface.IRequestDAO;
import by.epam.naumovich.rw_tickets.dao.impl.RequestDAOImpl;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.IRequestService;
import by.epam.naumovich.rw_tickets.service.impl.RequestServiceImpl;

/**
 * Tests the IRequestService interface implementation which is injected by the Spring IOC technology.
 * Test class is set up with the help of the Mockito framework.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class RequestServiceTest {
	
	private static boolean setUpIsDone = false;
	private static IRequestDAO dao;
	private static IRequestService service = new RequestServiceImpl();
	
	private static GroupRequest expectedRequest;
	private static List<GroupRequest> expectedReqList;
	
	@Before
	public void init() {
		if (setUpIsDone) {
			return;
		}
		dao = mock(RequestDAOImpl.class);
		((RequestServiceImpl)service).setRequestDAO(dao);
		initTestRequest();
		initRequestCollection();
		setUpIsDone = true;
	}

	private void initTestRequest() {
		expectedRequest = new GroupRequest();
		expectedRequest.setRq_num(1);
		expectedRequest.setFrom_user(2);
		expectedRequest.setTo_user(3);
		expectedRequest.setGrId(4);
	}
	
	private void initRequestCollection() {
		expectedReqList = new ArrayList<>();
		
		GroupRequest req = new GroupRequest();
		req.setRq_num(2);
		req.setFrom_user(3);
		
		GroupRequest req2 = new GroupRequest();
		req2.setRq_num(3);
		req2.setFrom_user(4);
		
		expectedReqList.add(expectedRequest);
		expectedReqList.add(req);
		expectedReqList.add(req2);
	}
	
	@Test
	public void testAddRequest() throws ServiceException {
		when(dao.addRequest(expectedRequest)).thenAnswer(invocation -> {
				GroupRequest request = (GroupRequest) invocation.getArguments()[0];
				return request.getRq_num();
			});
		
		assertEquals(1, service.addRequest(expectedRequest));
		verify(dao).addRequest(expectedRequest);
		verifyNoMoreInteractions(dao);
	}
	
	@Test
	public void testUpdateRequest() throws ServiceException {
		service.updateRequest(2, 'c');
		verify(dao).updateRequest(2, 'c');
	}
	
	@Test
	public void testDeleteRequest() throws ServiceException {
		service.deleteRequest(20);
		verify(dao).deleteRequest(20);
	}
	
	@Test(expected=ServiceException.class)
	public void testGetRequestByNum() throws ServiceException {
		when(dao.getRequestByNum(anyInt())).thenReturn(expectedRequest);
		
		GroupRequest actual = service.getRequestByNum(30);
		verify(dao).getRequestByNum(30);
		
		assertEquals(expectedRequest, actual);
	
		service.getRequestByNum(-1);
		verify(dao).getRequestByNum(-1);
		verifyNoMoreInteractions(dao);
	}
	
	@Test
	public void testGetIncomingRequests() throws ServiceException {
		when(dao.getUserIncRequests(anyInt())).thenReturn(expectedReqList);
		List<GroupRequest> actual = service.getUserIncRequests(3);
		assertThat(actual, is(expectedReqList));
		verify(dao).getUserIncRequests(3);
	}
	
	@Test
	public void testGetOutcomingRequests() throws ServiceException {
		when(dao.getUserOutRequests(anyInt())).thenReturn(expectedReqList);
		List<GroupRequest> actual = service.getUserOutRequests(4);
		assertThat(actual, is(expectedReqList));
		verify(dao).getUserOutRequests(4);
	}
	

}
