package by.epam.naumovich.rw_tickets.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * This entity bean class describes the group request
 * 
 * @author Dzmitry Naumovich
 * @version 1.0
 */
public class GroupRequest {

	private int num;
	private char type;
	private int fromUser;
	private int toUser;
	private int groupId;
	private String comment;
	private char status;
	private Date createDate;
	private Time createTime;
	private Date closeDate;
	private Time closeTime;
	
	
	public GroupRequest() {}

}
