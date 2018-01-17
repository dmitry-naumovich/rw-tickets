package by.epam.naumovich.rw_tickets.entity;

import java.sql.Date;
import java.sql.Time;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This simple entity bean class describes the group request.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Data
@NoArgsConstructor // Empty constructor may be used by JAXB, for instance
@Builder
public class GroupRequest {

	private int rq_num;
	private int from_user;
	private int to_user;
	private int grId;
	private String comment;
	private char status;
	private Date createDate;
	private Time createTime;
	private Date closeDate;
	private Time closeTime;
}
