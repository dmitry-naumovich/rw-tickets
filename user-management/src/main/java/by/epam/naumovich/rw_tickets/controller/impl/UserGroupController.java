package by.epam.naumovich.rw_tickets.controller.impl;

import by.epam.naumovich.rw_tickets.controller.UserGroupApi;
import by.epam.naumovich.rw_tickets.dto.UserGroupDTO;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.ServiceFacade;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserGroupController implements UserGroupApi {

    private final ServiceFacade serviceFacade;

    @Autowired
    public UserGroupController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public UserGroupDTO create(UserGroup group) throws ServiceException {
        return serviceFacade.addUserGroup(group);
    }

    @Override
    public UserGroupDTO update(int id, UserGroup group) throws ServiceException {
        group.setId(id);
        return serviceFacade.updateUserGroup(group);
    }

    @Override
    public UserGroupDTO getById(int id) throws ServiceException {
        return serviceFacade.getGroupById(id);
    }

    @Override
    public void delete(int id) throws ServiceException {
        serviceFacade.deleteGroup(id);
    }

    @Override
    public UserGroupDTO deleteGroupMember(int groupId, int userId) throws ServiceException {
        return serviceFacade.removeGroupMember(userId, groupId);
    }
}
