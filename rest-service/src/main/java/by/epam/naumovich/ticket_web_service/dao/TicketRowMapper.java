package by.epam.naumovich.ticket_web_service.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.jdbc.core.RowMapper;

import by.epam.naumovich.ticket_web_service.entity.Ticket;



/**
 * Spring JDBC RowMapper implementation which maps Ticket table in database on Ticket entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class TicketRowMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setNum(rs.getInt(1));
		ticket.setDepCountry(rs.getString(2));
		ticket.setDepCity(rs.getString(3));
		ticket.setArrCountry(rs.getString(4));
		ticket.setArrCity(rs.getString(5));
		ticket.setDepartDate(Date.valueOf(rs.getString(6).substring(0, 10)));
		ticket.setDepartTime(Time.valueOf(rs.getString(6).substring(11, 19)));
		ticket.setArrDate(Date.valueOf(rs.getString(7).substring(0, 10)));
		ticket.setArrTime(Time.valueOf(rs.getString(7).substring(11, 19)));
		ticket.setPrice(rs.getShort(8));
		ticket.setStatus(rs.getString(9).charAt(0));
		ticket.setPassenger(rs.getInt(10));
		return ticket;
	}

	

}
