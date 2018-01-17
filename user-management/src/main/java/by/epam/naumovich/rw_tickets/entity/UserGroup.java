package by.epam.naumovich.rw_tickets.entity;

import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This simple entity bean class describes the user group.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGroup {

	private int id;
	private String name;
	private Date createDate;
	private Time createTime;
	private int owner;
}
