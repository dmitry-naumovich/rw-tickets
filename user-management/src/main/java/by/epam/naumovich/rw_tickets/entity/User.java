package by.epam.naumovich.rw_tickets.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * This entity bean class describes the user
 * 
 * @author Dzmitry Naumovich
 * @version 1.0
 */
public class User {
	
	private int id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	private Date birthDate;
	private Date regDate;
	private Time regTime;
	private char sex;
	private String phone;
	private String address;
	private String passp_ser;
	private String passp_num;
	private String country;
	private String city;
	private boolean isAdmin;
}
