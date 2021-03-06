package by.epam.naumovich.rw_tickets.controller;

import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "API for working with application cities and countries")
@RequestMapping(value = "geo", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CityCountryApi {

    @ApiOperation(value = "Get list of all available countries")
    @GetMapping("/country")
    List<Country> getAllCountries() throws ServiceException;

    @ApiOperation(value = "Get list of all cities in the specified country")
    @GetMapping("/country/{code}/city")
    List<City> getAllCitiesByCountry(@PathVariable("code") String countryCode) throws ServiceException;

}
