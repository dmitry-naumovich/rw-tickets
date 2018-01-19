package by.epam.naumovich.rw_tickets.controller.impl;

import by.epam.naumovich.rw_tickets.controller.GroupRequestApi;
import by.epam.naumovich.rw_tickets.dto.GroupRequestDTO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.service.ServiceFacade;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupRequestController implements GroupRequestApi {

    private final ServiceFacade serviceFacade;

    @Autowired
    public GroupRequestController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public GroupRequestDTO create(GroupRequest request) throws ServiceException {
        return serviceFacade.addGroupRequest(request);
    }

    @Override
    public GroupRequestDTO getById(int reqNum) throws ServiceException {
        return serviceFacade.getRequestByNum(reqNum);
    }

    @Override
    public List<GroupRequestDTO> getIncomingRequests(int userId) throws ServiceException {
        return serviceFacade.getIncomingRequests(userId);
    }

    @Override
    public List<GroupRequestDTO> getOutcomingRequests(int userId) throws ServiceException {
        return serviceFacade.getOutcomingRequests(userId);
    }

    @Override
    public void accept(int reqNum) throws ServiceException {
        serviceFacade.acceptRequest(reqNum);
    }

    @Override
    public void cancel(int reqNum) throws ServiceException {
        serviceFacade.cancelRequest(reqNum);
    }

    @Override
    public void reject(int reqNum) throws ServiceException {
        serviceFacade.rejectRequest(reqNum);
    }

    @Override
    public void delete(int reqNum) throws ServiceException {
        serviceFacade.deleteRequest(reqNum);
    }
}
