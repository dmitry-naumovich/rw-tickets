package by.epam.naumovich.rw_tickets.dao.iface;

import java.util.List;

import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;

/**
 * Defines methods to be implemented in the DAO layer for City and Country entities.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public interface ICityCountryDAO {

	/**
	 * Gets country by its unique code from the data source
	 * 
	 * @param code country code
	 * @return found Country entity
	 */
	Country getCountryByCode(String code);
	
	/**
	 * Gets country by its unique name from the data source
	 * 
	 * @param name country name
	 * @return found Country entity
	 */
	Country getCountryByName(String name);
	
	/**
	 * Gets city by its country and city codes from the data source
	 * 
	 * @param cityCode city code
	 * @param countryCode country code
	 * @return found City entity
	 */
	City getCityByCode(String cityCode, String countryCode);
	
	/**
	 * Gets all countries from the data source
	 * 
	 * @return the list containing all countries
	 */
	List<Country> getAllCountries();
	
	/**
	 * Gets all cities from the data source
	 * 
	 * @return the list containing all cities
	 */
	List<City> getAllCities();
	
	/**
	 * Gets all cities in the country specified from the data source
	 * 
	 * @param countryCode country code
	 * @return the list containing all cities in the country
	 */
	List<City> getAllCitiesInCountry(String countryCode);
	
}
