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
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public int getFromUser() {
		return fromUser;
	}

	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}

	public int getToUser() {
		return toUser;
	}

	public void setToUser(int toUser) {
		this.toUser = toUser;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Time getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 5 * hash + num;
		hash = 7 * hash + type;
		hash = 11 * hash + fromUser;
		hash = 17 * hash + toUser;
		hash = 13 * hash + groupId;
		hash = 19 * hash + comment.hashCode();
		hash = 23 * hash + status;
		hash = 29 * hash + createDate.hashCode();
		hash = 31 * hash + createTime.hashCode();
		hash = 37 * hash + closeDate.hashCode();
		hash = 47 * hash + closeTime.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (obj.getClass() != getClass()) { return false; }
		
		GroupRequest req = (GroupRequest)obj;
		if (num != req.num) { 
			return false; 
		}
		if (type != req.type) {
			return false; 
		}
		if (fromUser != req.fromUser) {
			return false; 
		}
		if (toUser != req.toUser) {
			return false; 
		}
		if (groupId != req.groupId) {
			return false; 
		}
		if (status != req.status) {
			return false; 
		}
		
		if ((null == comment) ? (req.comment != null) : !comment.equals(req.comment)) {
			return false;
		}
		if ((null == createDate) ? (req.createDate != null) : !createDate.equals(req.createDate)) {
			return false;
		}
		if ((null == createTime) ? (req.createTime != null) : !createTime.equals(req.createTime)) {
			return false;
		}
		if ((null == closeDate) ? (req.closeDate != null) : !closeDate.equals(req.closeDate)) {
			return false;
		}
		if ((null == closeTime) ? (req.closeTime != null) : !closeTime.equals(req.closeTime)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" num: " + num);
	    result.append(", type: " + type);
	    result.append(", fromUser: " + fromUser);
	    result.append(", toUser: " + toUser);
	    result.append(", groupId: " + groupId);
	    if (comment != null) { result.append(", comment: " + comment); }
	    result.append(", status: " + status);
	    result.append(", createDate: " + createDate);
	    result.append(", createTime: " + createTime);
	    if (closeDate != null) { result.append(", closeDate: " + closeDate); }
	    if (closeTime != null) { result.append(", closeTime: " + closeTime); }
	    result.append("}");
		
	    return result.toString();
	}

	
}
