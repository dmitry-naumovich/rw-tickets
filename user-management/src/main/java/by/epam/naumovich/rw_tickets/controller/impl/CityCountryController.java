package by.epam.naumovich.rw_tickets.controller.impl;

import by.epam.naumovich.rw_tickets.controller.CityCountryApi;
import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;
import by.epam.naumovich.rw_tickets.service.ServiceFacade;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CityCountryController implements CityCountryApi {

    private final ServiceFacade serviceFacade;

    @Autowired
    public CityCountryController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public List<Country> getAllCountries() throws ServiceException {
        log.debug("CityCountryController::getAllCountries invoked");
        return serviceFacade.getAllCountries();
    }

    @Override
    public List<City> getAllCitiesByCountry(String countryCode) throws ServiceException {
        log.debug("CityCountryController::getAllCitiesByCountry invoked, args: countryCode = {}", countryCode);
        return serviceFacade.getAllCitiesByCountry(countryCode);
    }
}
