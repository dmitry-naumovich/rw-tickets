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

	private int rq_num;
	private int from_user;
	private int to_user;
	private int gr_id;
	private String rq_comment;
	private char status;
	private Date createDate;
	private Time createTime;
	private Date closeDate;
	private Time closeTime;
	
	
	public GroupRequest() {}
	
	public int getRq_num() {
		return rq_num;
	}

	public void setRq_num(int num) {
		this.rq_num = num;
	}

	public int getFrom_user() {
		return from_user;
	}

	public void setFrom_user(int fromUser) {
		this.from_user = fromUser;
	}

	public int getTo_user() {
		return to_user;
	}

	public void setTo_user(int toUser) {
		this.to_user = toUser;
	}

	public int getGr_id() {
		return gr_id;
	}

	public void setGr_id(int groupId) {
		this.gr_id = groupId;
	}

	public String getRq_comment() {
		return rq_comment;
	}

	public void setRq_comment(String comment) {
		this.rq_comment = comment;
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
		hash = 5 * hash + rq_num;
		hash = 11 * hash + from_user;
		hash = 17 * hash + to_user;
		hash = 13 * hash + gr_id;
		hash = 19 * hash + rq_comment.hashCode();
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
		if (rq_num != req.rq_num) { 
			return false; 
		}
		if (from_user != req.from_user) {
			return false; 
		}
		if (to_user != req.to_user) {
			return false; 
		}
		if (gr_id != req.gr_id) {
			return false; 
		}
		if (status != req.status) {
			return false; 
		}
		
		if ((null == rq_comment) ? (req.rq_comment != null) : !rq_comment.equals(req.rq_comment)) {
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
	    result.append(" num: " + rq_num);
	    result.append(", fromUser: " + from_user);
	    result.append(", toUser: " + to_user);
	    result.append(", groupId: " + gr_id);
	    if (rq_comment != null) { result.append(", comment: " + rq_comment); }
	    result.append(", status: " + status);
	    result.append(", createDate: " + createDate);
	    result.append(", createTime: " + createTime);
	    if (closeDate != null) { result.append(", closeDate: " + closeDate); }
	    if (closeTime != null) { result.append(", closeTime: " + closeTime); }
	    result.append("}");
		
	    return result.toString();
	}

	
}
