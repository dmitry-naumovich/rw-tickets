package by.epam.naumovich.rw_tickets.service.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;

public interface ICityCountryService {

	public Country getCountryByCode(String code) throws ServiceException;
	public Country getCountryByName(String name) throws ServiceException;
	public City getCityByCode(String cityCode, String countryCode) throws ServiceException;
	public List<Country> getAllCountries();
	public List<City> getAllCities();
	public List<City> getAllCitiesInCountry(String countryCode) throws ServiceException;
}
