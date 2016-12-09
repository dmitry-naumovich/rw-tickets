package by.epam.naumovich.rw_tickets.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * This entity bean class describes the user group
 * 
 * @author Dzmitry Naumovich
 * @version 1.0
 */
public class UserGroup {

	private int id;
	private String name;
	private Date createDate;
	private Time createTime;
	private int owner;
	
	public UserGroup() {}

}
