package by.epam.naumovich.rw_tickets.controller;

import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "cities", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "API for working with application cities and countries")
public interface CityCountryApi {

    @ApiOperation(value = "Get list of all available countries")
    @GetMapping
    List<Country> getAllCountries() throws ServiceException;

    @ApiOperation(value = "Get list of all cities in the specified country")
    @GetMapping
    List<City> getAllCitiesByCountry(String countryCode) throws ServiceException;

}
