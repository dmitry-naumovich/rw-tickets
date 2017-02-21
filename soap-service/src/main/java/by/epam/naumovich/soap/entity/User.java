package by.epam.naumovich.soap.entity;

import java.sql.Date;

/**
 * This simple entity bean class describes the user.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class User {
	
	private int id;
	private String login;
	private String pwd;
	private String fname;
	private String sname;
	private String email;
	private Date birthDate;
	private String phone;
	private String address;
	private String passport;
	private String country;
	private String city;
	private boolean isAdmin;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String password) {
		this.pwd = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String name) {
		this.fname = name;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String surname) {
		this.sname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		int hash = 11;
		hash = 7 * hash + id;
		hash = 13 * hash + login.hashCode();
		hash = 3 * hash + pwd.hashCode();
		hash = 47 * hash + fname.hashCode();
		hash = 31 * hash + sname.hashCode();
		hash = 61 * hash + email.hashCode();
		hash = 37 * hash + birthDate.hashCode();
		hash = 11 * hash + phone.hashCode();
		hash = 5 * hash + address.hashCode();
		hash = 19 * hash + passport.hashCode();
		hash = 17 * hash + country.hashCode();
		hash = 23 * hash + city.hashCode();
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
		
		User us = (User)obj;
		if (id != us.id) { 
			return false; 
		}
		if (isAdmin != us.isAdmin) {
			return false; 
		}
		
		if ((null == login) ? (us.login != null) : !login.equals(us.login)) {
			return false;
		}
		if ((null == pwd) ? (us.pwd != null) : !pwd.equals(us.pwd)) {
			return false;
		}
		if ((null == fname) ? (us.fname != null) : !fname.equals(us.fname)) {
			return false;
		}
		if ((null == sname) ? (us.sname != null) : !sname.equals(us.sname)) {
			return false;
		}
		if ((null == email) ? (us.email != null) : !email.equals(us.email)) {
			return false;
		}
		if ((null == birthDate) ? (us.birthDate != null) : !birthDate.equals(us.birthDate)) {
			return false;
		}
		if ((null == phone) ? (us.phone != null) : !phone.equals(us.phone)) {
			return false;
		}
		if ((null == address) ? (us.address != null) : !address.equals(us.address)) {
			return false;
		}
		if ((null == passport) ? (us.passport != null) : !passport.equals(us.passport)) {
			return false;
		}
		if ((null == country) ? (us.country != null) : !country.equals(us.country)) {
			return false;
		}
		if ((null == city) ? (us.city != null) : !city.equals(us.city)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {		
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" ID: " + id);
	    result.append(", login: " + login);
	    result.append(", password: " + pwd);
	    result.append(", name: " + fname);
	    result.append(", surname: " + sname);
	    result.append(", email: " + email);
	    if (phone != null) { 
	    	result.append(", phone: " + phone); 
	    }
	    if (country != null) { 
	    	result.append(", country: " + country); 
	    }
	    if (city != null) { 
	    	result.append(", city: " + city); 
	    }
	    if (address != null) { 
	    	result.append(", address: " + address); 
	    }
	    if (passport != null) { 
	    	result.append(", passport: " + passport); 
	    }
	    result.append(", isAdmin: " + isAdmin);
	    result.append("}");

		
	    return result.toString();
	}
}
