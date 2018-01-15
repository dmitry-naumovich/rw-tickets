package by.epam.naumovich.rw_tickets.controller.impl;

import by.epam.naumovich.rw_tickets.controller.UserApi;
import by.epam.naumovich.rw_tickets.dto.UserDTO;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.ServiceFacade;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    private final ServiceFacade serviceFacade;

    @Autowired
    public UserController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public UserDTO create(User user) throws ServiceException {
        return serviceFacade.addUser(user);
    }

    @Override
    public UserDTO update(int id, User user) throws ServiceException {
        user.setId(id);
        return serviceFacade.updateUser(user);
    }

    @Override
    public UserDTO getById(int id) throws ServiceException {
        return serviceFacade.getUserById(id);
    }

    @Override
    public List<User> getAll(String sortBy) throws ServiceException {
        return serviceFacade.getAllUsersSorted(sortBy);
    }

    @Override
    public List<User> search(String name, String login, String email, String countryCode, String cityCode) throws ServiceException {
        return serviceFacade.searchForUsers(name, login, email, countryCode, cityCode);
    }

    @Override
    public void delete(int id) throws ServiceException {
        serviceFacade.deleteUser(id);
    }
}
