package by.epam.naumovich.rw_tickets.controller;

import by.epam.naumovich.rw_tickets.dto.UserGroupDTO;
import by.epam.naumovich.rw_tickets.entity.UserGroup;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "API for working with application user groups")
@RequestMapping(value = "group", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserGroupApi {

    @ApiOperation("Create user group")
    @PostMapping
    UserGroupDTO create(UserGroup group) throws ServiceException;

    @ApiOperation("Update user group")
    @PutMapping("/{id}")
    UserGroupDTO update(@PathVariable int id, @RequestBody UserGroup group) throws ServiceException;

    @ApiOperation("Get user group by id")
    @GetMapping("/{id}")
    UserGroupDTO getById(@PathVariable int id) throws ServiceException;

    @ApiOperation("Delete user group by id")
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) throws ServiceException;

    @ApiOperation("Delete user from group")
    @PutMapping
    UserGroupDTO deleteGroupMember(@RequestParam("groupId") int groupId, @RequestParam("userId") int userId) throws ServiceException;
}
