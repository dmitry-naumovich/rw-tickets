package by.epam.naumovich.rw_tickets.controller.impl;

import by.epam.naumovich.rw_tickets.controller.CityCountryApi;
import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;
import by.epam.naumovich.rw_tickets.service.ServiceFacade;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityCountryController implements CityCountryApi {

    private final ServiceFacade serviceFacade;

    @Autowired
    public CityCountryController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public List<Country> getAllCountries() throws ServiceException {
        return serviceFacade.getAllCountries();
    }

    @Override
    public List<City> getAllCitiesByCountry(String countryCode) throws ServiceException {
        return serviceFacade.getAllCitiesByCountry(countryCode);
    }
}
