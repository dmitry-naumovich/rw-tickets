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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		int hash = 13;
		hash = 17 * hash + id;
		hash = 43 * hash + name.hashCode();
		hash = 53 * hash + createDate.hashCode();
		hash = 47 * hash + createTime.hashCode();
		hash = 3 * hash + owner;
		
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (obj.getClass() != getClass()) { return false; }
		
		UserGroup grp = (UserGroup)obj;
		if (id != grp.id) { 
			return false; 
		}
		if (owner != grp.owner) {
			return false; 
		}
		
		if ((null == name) ? (grp.name != null) : !name.equals(grp.name)) {
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
	    result.append(" ID: " + id);
	    result.append(", name: " + name);
	    result.append(", createDate: " + createDate);
	    result.append(", createTime: " + createTime);
	    result.append(", owner: " + owner);
	    result.append("}");

	    return result.toString();
	}
}
