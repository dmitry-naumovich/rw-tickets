package by.epam.naumovich.rw_tickets.entity;

/**
 * This simple entity bean class describes the country.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class Country {

	private String code;
	private String name;
	
	public Country() {
		// Empty constructor may be used by JAXB, for instance
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String country) {
		this.name = country;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 13 * hash + code.hashCode();
		hash = 17 * hash + name.hashCode();
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
		
		Country country = (Country)obj;
		if ((null == code) ? (country.code != null) : !code.equals(country.code)) {
			return false;
		}
		if ((null == name) ? (country.name != null) : !name.equals(country.name)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" code: " + code);
	    result.append(", name: " + name);
		
	    return result.toString();
	}
}
