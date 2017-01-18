package by.epam.naumovich.rw_tickets.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * This simple entity bean class describes the user group.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class UserGroup {

	private int gr_id;
	private String gr_name;
	private Date createDate;
	private Time createTime;
	private int owner_id;
	
	public int getGr_id() {
		return gr_id;
	}

	public void setGr_id(int id) {
		this.gr_id = id;
	}

	public String getGr_name() {
		return gr_name;
	}

	public void setGr_name(String name) {
		this.gr_name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Time getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner) {
		this.owner_id = owner;
	}

	@Override
	public int hashCode() {
		int hash = 13;
		hash = 17 * hash + gr_id;
		hash = 43 * hash + gr_name.hashCode();
		hash = 53 * hash + createDate.hashCode();
		hash = 47 * hash + createTime.hashCode();
		hash = 3 * hash + owner_id;
		
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
		
		UserGroup grp = (UserGroup)obj;
		if (gr_id != grp.gr_id) { 
			return false; 
		}
		if (owner_id != grp.owner_id) {
			return false; 
		}
		
		if ((null == gr_name) ? (grp.gr_name != null) : !gr_name.equals(grp.gr_name)) {
			return false;
		}
		if ((null == createDate) ? (grp.createDate != null) : !createDate.equals(grp.createDate)) {
			return false;
		}
		if ((null == createTime) ? (grp.createTime != null) : !createTime.equals(grp.createTime)) {
			return false;
		}
		return true;

	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" ID: " + gr_id);
	    result.append(", name: " + gr_name);
	    result.append(", createDate: " + createDate);
	    result.append(", createTime: " + createTime);
	    result.append(", owner: " + owner_id);
	    result.append("}");

	    return result.toString();
	}
}
