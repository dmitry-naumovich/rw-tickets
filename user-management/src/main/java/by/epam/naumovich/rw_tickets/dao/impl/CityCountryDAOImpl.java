package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.ICityCountryDAO;
import by.epam.naumovich.rw_tickets.dao.mapper.CityRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.CountryRowMapper;
import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;
import org.springframework.stereotype.Repository;

/**
 * ICityCountryDAO implementation for Oracle database which uses Spring JDBC framework to connect to the DB and perform all operations,
 * which might be needed by the service layer. This class works with City and Country entities and relevant tables in the DB.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Repository
public class CityCountryDAOImpl implements ICityCountryDAO {

	private static final String SELECT_COUNTRY_BY_CODE = "SELECT * FROM country WHERE code = ?";
    private static final String SELECT_COUNTRY_BY_NAME = "SELECT * FROM country WHERE country = ?";
    private static final String SELECT_CITY_BY_CODE = "SELECT * FROM city WHERE code = ? AND country = ?";
    private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country";
    private static final String SELECT_ALL_CITIES = "SELECT * FROM city";
    private static final String SELECT_CITIES_BY_COUNTRY = "SELECT * FROM city WHERE country = ?";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CityCountryDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Country getCountryByCode(String code) {
		Object[] params = new Object[] {code};
		List<Country> countries = jdbcTemplate.query(SELECT_COUNTRY_BY_CODE, params, new CountryRowMapper());
		return countries.isEmpty() ? null : countries.get(0);
	}

	@Override
	public Country getCountryByName(String name) {
		Object[] params = new Object[] {name};
		List<Country> countries = jdbcTemplate.query(SELECT_COUNTRY_BY_NAME, params, new CountryRowMapper());
        return countries.isEmpty() ? null : countries.get(0);
	}

	@Override
	public City getCityByCode(String cityCode, String countryCode) {
		Object[] params = new Object[] {cityCode, countryCode};
		int[] types = new int[] {Types.VARCHAR, Types.VARCHAR};
		List<City> cities = jdbcTemplate.query(SELECT_CITY_BY_CODE, params, types, new CityRowMapper());
        return cities.isEmpty() ? null : cities.get(0);
	}

	@Override
	public List<Country> getAllCountries() {
		return jdbcTemplate.query(SELECT_ALL_COUNTRIES, new CountryRowMapper());
	}

	@Override
	public List<City> getAllCities() {
		return jdbcTemplate.query(SELECT_ALL_CITIES, new CityRowMapper());
	}

	@Override
	public List<City> getAllCitiesInCountry(String countryCode) {
		Object[] params = new Object[] {countryCode};
		return jdbcTemplate.query(SELECT_CITIES_BY_COUNTRY, params, new CityRowMapper());
	}

}
