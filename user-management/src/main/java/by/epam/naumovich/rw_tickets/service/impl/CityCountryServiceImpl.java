package by.epam.naumovich.rw_tickets.service.impl;

import java.security.InvalidParameterException;
import java.util.List;

import by.epam.naumovich.rw_tickets.dao.iface.ICityCountryDAO;
import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import by.epam.naumovich.rw_tickets.service.iface.ICityCountryService;
import by.epam.naumovich.rw_tickets.service.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ICityCountryService implementation which validates input parameters using the Validator class and invokes the methods from DAO 
 * which is injected into this class by the Spring Framework IoC framework.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 * @see Validator
 */
@Service
public class CityCountryServiceImpl implements ICityCountryService {

	private static final String INVALID_INPUT_PARAMS = "Invalid input parameters passed into method";
	
	private final ICityCountryDAO cityCountryDAO;

	@Autowired
	public CityCountryServiceImpl(ICityCountryDAO cityCountryDAO) {
		this.cityCountryDAO = cityCountryDAO;
	}

	@Override
	public Country getCountryByCode(String code) throws ServiceException {
		if (!Validator.validateStrings(code)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		Country country = cityCountryDAO.getCountryByCode(code);
		if (country == null) {
		    throw new InvalidParameterException("No country found with the code " + code);
        }
		return country;
	}

	@Override
	public Country getCountryByName(String name) throws ServiceException {
		if (!Validator.validateStrings(name)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
        Country country = cityCountryDAO.getCountryByName(name);
        if (country == null) {
            throw new InvalidParameterException("No country found with the name " + name);
        }
		return country;
	}

	@Override
	public City getCityByCode(String cityCode, String countryCode) throws ServiceException {
		if (!Validator.validateStrings(cityCode, countryCode)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		City city = cityCountryDAO.getCityByCode(cityCode, countryCode);
		if (city == null) {
		    throw new InvalidParameterException("No city found with the code " + cityCode + " in country with code " + countryCode);
        }
		return city;
	}

	@Override
	public List<Country> getAllCountries() {
		return cityCountryDAO.getAllCountries();
	}

	@Override
	public List<City> getAllCities() {
		return cityCountryDAO.getAllCities();
	}

	@Override
	public List<City> getAllCitiesInCountry(String countryCode) throws ServiceException {
		if (!Validator.validateStrings(countryCode)) {
			throw new ServiceException(INVALID_INPUT_PARAMS);
		}
		return cityCountryDAO.getAllCitiesInCountry(countryCode);
	}

}
