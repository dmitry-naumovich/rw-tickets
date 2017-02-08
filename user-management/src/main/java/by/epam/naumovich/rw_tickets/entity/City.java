package by.epam.naumovich.rw_tickets.entity;

/**
 * This simple entity bean class describes the city.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class City {

	private String code;
	private String country;
	private String city_name;
	
	public City() {
		// Empty constructor may be used by JAXB, for instance
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	@Override
	public int hashCode() {
		int hash = 49;
		hash = 17 * hash + code.hashCode();
		hash = 11 * hash + country.hashCode();
		hash = 5 * hash + city_name.hashCode();
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		
		City city = (City)obj;
		if ((null == code) ? (city.code != null) : !code.equals(city.code)) {
			return false;
		}
		if ((null == country) ? (city.country != null) : !country.equals(city.country)) {
			return false;
		}
		if ((null == city_name) ? (city.city_name != null) : !city_name.equals(city.city_name)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" code: " + code);
	    result.append(", country: " + country);
	    result.append(", city_name: " + city_name);
	    return result.toString();	
	}
}
