package by.epam.naumovich.rw_tickets.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.rw_tickets.dao.iface.ICityCountryDAO;
import by.epam.naumovich.rw_tickets.dao.mapper.CityRowMapper;
import by.epam.naumovich.rw_tickets.dao.mapper.CountryRowMapper;
import by.epam.naumovich.rw_tickets.entity.City;
import by.epam.naumovich.rw_tickets.entity.Country;

public class CityCountryDAOImpl implements ICityCountryDAO {

	public static final String SELECT_COUNTRY_BY_CODE = "SELECT * FROM Countries WHERE code = ?";
	public static final String SELECT_COUNTRY_BY_NAME = "SELECT * FROM Countries WHERE country = ?";
	public static final String SELECT_CITY_BY_CODE = "SELECT * FROM Cities WHERE code = ? AND country = ?";
	public static final String SELECT_ALL_COUNTRIES = "SELECT * FROM Countries";
	public static final String SELECT_ALL_CITIES = "SELECT * FROM Cities";
	public static final String SELECT_CITIES_BY_COUNTRY = "SELECT * FROM Cities WHERE country = ?";
	
	private JdbcTemplate jdbcTemplate;
	
	public CityCountryDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Country getCountryByCode(String code) {
		Object[] params = new Object[] {code};
		List<Country> countries = jdbcTemplate.query(SELECT_COUNTRY_BY_CODE, params, new CountryRowMapper());
		return countries.get(0);
	}

	@Override
	public Country getCountryByName(String name) {
		Object[] params = new Object[] {name};
		List<Country> countries = jdbcTemplate.query(SELECT_COUNTRY_BY_NAME, params, new CountryRowMapper());
		return countries.get(0);
	}

	@Override
	public City getCityByCode(String cityCode, String countryCode) {
		Object[] params = new Object[] {cityCode, countryCode};
		int[] types = new int[] {Types.VARCHAR, Types.VARCHAR};
		List<City> cities = jdbcTemplate.query(SELECT_CITY_BY_CODE, params, types, new CityRowMapper());
		return cities.get(0);
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
