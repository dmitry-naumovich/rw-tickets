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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "API for working with application users")
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserApi {

    @ApiOperation(value = "Create user")
    @PostMapping
    UserDTO create(User user) throws ServiceException;

    @ApiOperation(value = "Update user")
    @PutMapping("/{id}")
    UserDTO update(@PathVariable int id, @RequestBody User user) throws ServiceException;

    @ApiOperation(value = "Get user by id")
    @GetMapping("/{id}")
    UserDTO getById(@PathVariable int id) throws ServiceException;

    @ApiOperation(value = "Get list of all users sortedBy")
    @GetMapping
    List<User> getAll(@RequestParam String sortBy) throws ServiceException;

    @ApiOperation(value = "Search for users")
    @GetMapping("/search")
    List<User> search(@RequestParam String name, @RequestParam String login, @RequestParam String email,
                      @RequestParam String countryCode, @RequestParam String cityCode) throws ServiceException;

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) throws ServiceException;
}
