package by.epam.naumovich.rw_tickets.controller.impl;

import by.epam.naumovich.rw_tickets.controller.GroupRequestApi;
import by.epam.naumovich.rw_tickets.dto.GroupRequestDTO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
import by.epam.naumovich.rw_tickets.service.ServiceFacade;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GroupRequestController implements GroupRequestApi {

    private final ServiceFacade serviceFacade;

    @Autowired
    public GroupRequestController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public GroupRequestDTO create(GroupRequest request) throws ServiceException {
        log.debug("GroupRequestController::create invoked, args: request = {}", request);
        return serviceFacade.addGroupRequest(request);
    }

    @Override
    public GroupRequestDTO getById(int reqNum) throws ServiceException {
        log.debug("GroupRequestController::getById invoked, args: reqNum = {}", reqNum);
        return serviceFacade.getRequestByNum(reqNum);
    }

    @Override
    public List<GroupRequestDTO> getIncomingRequests(int userId) throws ServiceException {
        log.debug("GroupRequestController::getIncomingRequests invoked, args: userId = {}", userId);
        return serviceFacade.getIncomingRequests(userId);
    }

    @Override
    public List<GroupRequestDTO> getOutcomingRequests(int userId) throws ServiceException {
        log.debug("GroupRequestController::getOutcomingRequests invoked, args: userId = {}", userId);
        return serviceFacade.getOutcomingRequests(userId);
    }

    @Override
    public void accept(int reqNum) throws ServiceException {
        log.debug("GroupRequestController::accept invoked, args: reqNum = {}", reqNum);
        serviceFacade.acceptRequest(reqNum);
    }

    @Override
    public void cancel(int reqNum) throws ServiceException {
        log.debug("GroupRequestController::cancel invoked, args: reqNum = {}", reqNum);
        serviceFacade.cancelRequest(reqNum);
    }

    @Override
    public void reject(int reqNum) throws ServiceException {
        log.debug("GroupRequestController::reject invoked, args: reqNum = {}", reqNum);
        serviceFacade.rejectRequest(reqNum);
    }

    @Override
    public void delete(int reqNum) throws ServiceException {
        log.debug("GroupRequestController::delete invoked, args: reqNum = {}", reqNum);
        serviceFacade.deleteRequest(reqNum);
    }
}
