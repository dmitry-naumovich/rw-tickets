package by.epam.naumovich.rw_tickets.controller;

import by.epam.naumovich.rw_tickets.dto.GroupRequestDTO;
import by.epam.naumovich.rw_tickets.entity.GroupRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api("Api for working with applicaation group requests")
@RequestMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
public interface GroupRequestApi {

    @ApiOperation("Create group request")
    @PostMapping
    GroupRequestDTO create(GroupRequest request) throws ServiceException;

    @ApiOperation("Get group request by num")
    @GetMapping("/{id}")
    GroupRequestDTO getById(@PathVariable("id") int reqNum) throws ServiceException;

    @ApiOperation("Get incoming group requests by user id")
    @GetMapping("/incoming")
    List<GroupRequestDTO> getIncomingRequests(@RequestParam("userId") int userId) throws ServiceException;

    @ApiOperation("Get outcoming group requests by user id")
    @GetMapping("/outcoming")
    List<GroupRequestDTO> getOutcomingRequests(@RequestParam("userId") int userId) throws ServiceException;

    @ApiOperation("Accept group request")
    @PutMapping("/{id}/accept")
    void accept(@PathVariable("id") int reqNum) throws ServiceException;

    @ApiOperation("Cancel group request")
    @PutMapping("/{id}/cancel")
    void cancel(@PathVariable("id") int reqNum) throws ServiceException;

    @ApiOperation("Reject group request")
    @PutMapping("/{id}/reject")
    void reject(@PathVariable("id") int reqNum) throws ServiceException;

    @ApiOperation("Delete group request")
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int reqNum) throws ServiceException;
}
