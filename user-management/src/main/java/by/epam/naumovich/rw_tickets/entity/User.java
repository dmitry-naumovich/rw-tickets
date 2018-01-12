package by.epam.naumovich.rw_tickets.entity;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This simple entity bean class describes the user.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Data
@NoArgsConstructor // Empty constructor may be used by JAXB, for instance
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
}
