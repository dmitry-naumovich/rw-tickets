package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;

/**
 * Defines methods that receive parameters from ServiceFacade layer, verify them, and then either pass them to the DAO layer 
 * or additionally does some logic possibly getting some objects or primitive values back and passing them further back to the ServiceFacade.
 * This class operates with the City and Country entities.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public interface ICityCountryService {

	public Country getCountryByCode(String code) throws ServiceException;
	public Country getCountryByName(String name) throws ServiceException;
	public City getCityByCode(String cityCode, String countryCode) throws ServiceException;
	public List<Country> getAllCountries();
	public List<City> getAllCities();
	public List<City> getAllCitiesInCountry(String countryCode) throws ServiceException;
}
