package by.epam.naumovich.rw_tickets.controller;

import by.epam.naumovich.rw_tickets.dto.UserDTO;
import by.epam.naumovich.rw_tickets.entity.User;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "API for working with application users")
public interface UserApi {

    @ApiOperation(value = "Create user")
    @PostMapping
    UserDTO create(User user) throws ServiceException;

    @ApiOperation(value = "Update user")
    @PostMapping
    UserDTO update(int id, User user) throws ServiceException;

    @ApiOperation(value = "Get user by id")
    @GetMapping
    UserDTO getById(int id) throws ServiceException;

    @ApiOperation(value = "Get list of all users sortedBy")
    @GetMapping
    List<User> getAll(String sortBy) throws ServiceException;

    @ApiOperation(value = "Search for users")
    @GetMapping
    List<User> search(String name, String login, String email, String countryCode, String cityCode) throws ServiceException;

    @ApiOperation(value = "Delete user")
    @DeleteMapping
    void delete(int id) throws ServiceException;
}
