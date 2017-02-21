package by.epam.naumovich.soap.entity;

import java.sql.Date;
import java.sql.Time;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import by.epam.naumovich.soap.webservice.util.CharacterAdapter;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket {
	
	@XmlAttribute(required = true)
	private int num;
	
	@XmlElement(required = true)
	private String depCity;
	
	@XmlElement(required = true)
	private String depCountry;
	
	@XmlElement(required = true)
	private String arrCity;
	
	@XmlElement(required = true)
	private String arrCountry;
	
	@XmlElement(required = true)
	private Date departDate;
	private Time departTime;
	private Date arrDate;
	private Time arrTime;
	
	@XmlElement(required = true)
	private short price;
	
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(CharacterAdapter.class)
	private Character status;
	
	@XmlElement(required = true)
	private int passenger;
	
	public Ticket() {
		// Empty constructor may be used by JAXB, for instance
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDepCity() {
		return depCity;
	}
	public void setDepCity(String cityFrom) {
		this.depCity = cityFrom;
	}
	public String getDepCountry() {
		return depCountry;
	}
	public void setDepCountry(String countryFrom) {
		this.depCountry = countryFrom;
	}
	public String getArrCity() {
		return arrCity;
	}
	public void setArrCity(String cityTo) {
		this.arrCity = cityTo;
	}
	public String getArrCountry() {
		return arrCountry;
	}
	public void setArrCountry(String countryTo) {
		this.arrCountry = countryTo;
	}
	public Date getDepartDate() {
		return departDate;
	}
	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}
	public Time getDepartTime() {
		return departTime;
	}
	public void setDepartTime(Time departTime) {
		this.departTime = departTime;
	}
	public Date getArrDate() {
		return arrDate;
	}
	public void setArrDate(Date arrivalDate) {
		this.arrDate = arrivalDate;
	}
	public Time getArrTime() {
		return arrTime;
	}
	public void setArrTime(Time arrivalTime) {
		this.arrTime = arrivalTime;
	}
	public short getPrice() {
		return price;
	}
	public void setPrice(short price) {
		this.price = price;
	}
	
	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public int getPassenger() {
		return passenger;
	}

	public void setPassenger(int passenger) {
		this.passenger = passenger;
	}

	@Override
	public int hashCode() {
		int hash = 49;
		hash = 13 * hash + num;
		hash = 11 * hash + depCity.hashCode();
		hash = 7 * hash + depCountry.hashCode();
		hash = 17 * hash + arrCity.hashCode();
		hash = 67 * hash + arrCountry.hashCode();
		hash = 61 * hash + departDate.hashCode();
		hash = 5 * hash + departTime.hashCode();
		hash = 19 * hash + arrDate.hashCode();
		hash = 23 * hash + arrTime.hashCode();
		hash = 19 * hash + price;
		hash = 31 * hash + status;
		hash = 3 * hash + passenger;
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
		
		Ticket tick = (Ticket)obj;
		if (num != tick.num) { 
			return false; 
		}
		if (price != tick.price) {
			return false; 
		}
		if (status != tick.status) {
			return false; 
		}
		if (passenger != tick.passenger) {
			return false; 
		}
		
		if ((null == depCity) ? (tick.depCity != null) : !depCity.equals(tick.depCity)) {
			return false;
		}
		if ((null == depCountry) ? (tick.depCountry != null) : !depCountry.equals(tick.depCountry)) {
			return false;
		}
		if ((null == arrCity) ? (tick.arrCity != null) : !arrCity.equals(tick.arrCity)) {
			return false;
		}
		if ((null == arrCountry) ? (tick.arrCountry != null) : !arrCountry.equals(tick.arrCountry)) {
			return false;
		}
		if ((null == departDate) ? (tick.departDate != null) : !departDate.equals(tick.departDate)) {
			return false;
		}
		if ((null == departTime) ? (tick.departTime != null) : !departTime.equals(tick.departTime)) {
			return false;
		}
		if ((null == arrDate) ? (tick.arrDate != null) : !arrDate.equals(tick.arrDate)) {
			return false;
		}
		if ((null == arrTime) ? (tick.arrTime != null) : !arrTime.equals(tick.arrTime)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" Number: " + num);
	    result.append(", departure city: " + depCity);
	    result.append(", departure country: " + depCountry);
	    result.append(", arrival city: " + arrCity);
	    result.append(", arrival country: " + arrCountry);
	    result.append(", departure date: " + departDate);
	    result.append(", departure time: " + departTime);
	    result.append(", arrival date: " + arrDate);
	    result.append(", arrival time: " + arrTime);
	    result.append(", price: " + price);
	    result.append(", status: " + status);
	    result.append(", passenger ID: " + passenger);
	    result.append("}");

	    return result.toString();
	}
}
