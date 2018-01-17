package by.epam.naumovich.rw_tickets.controller.impl;

import by.epam.naumovich.rw_tickets.controller.UserApi;
import by.epam.naumovich.rw_tickets.dto.UserDTO;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.ServiceFacade;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController implements UserApi {

    private final ServiceFacade serviceFacade;

    @Autowired
    public UserController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public UserDTO create(User user) throws ServiceException {
        log.debug("UseController::create invoked, args: user = {}", user);
        return serviceFacade.addUser(user);
    }

    @Override
    public UserDTO update(@PathVariable int id, User user) throws ServiceException {
        log.debug("UserController::update invoked, args: id = {}, user = {}", id, user);
        user.setId(id);
        return serviceFacade.updateUser(user);
    }

    @Override
    public UserDTO getById(@PathVariable int id) throws ServiceException {
        log.debug("UserController:getById invoked, args: id = {}", id);
        return serviceFacade.getUserById(id);
    }

    @Override
    public List<User> getAll(String sortBy) throws ServiceException {
        log.debug("UserController:getAll invoked, args: sortBy = {}", sortBy);
        return serviceFacade.getAllUsersSorted(sortBy);
    }

    @Override
    public List<User> search(String name, String login, String email, String countryCode, String cityCode) throws ServiceException {
        log.debug("UserController:search invoked, args: name = {}, login = {}", name, login);
        return serviceFacade.searchForUsers(name, login, email, countryCode, cityCode);
    }

    @Override
    public void delete(int id) throws ServiceException {
        log.debug("UserController:delete invoked, args: id = {}", id);
        serviceFacade.deleteUser(id);
    }
}
