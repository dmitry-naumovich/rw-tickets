package by.epam.naumovich.ticket_web_service.dao;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.naumovich.ticket_web_service.entity.Ticket;



/**
 * ITicketDAO implementation for Oracle database which uses Spring JDBC framework to connect to the DB and perform all operations,
 * which might be needed by the service layer. This class works with Ticket entity and relevant table in the DB.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class TicketDAOImpl implements ITicketDAO {

	public static final String INSERT_NEW_TICKET = "INSERT INTO tickets (dp_country, dp_city, ar_country, ar_city, dp_datetime, ar_datetime, price, status, passenger) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_TICKET = "UPDATE tickets SET status = ? WHERE t_num = ?";
	public static final String DELETE_TICKET = "DELETE FROM tickets WHERE t_num = ?";
	public static final String SELECT_TICKET_BY_NUM = "SELECT * FROM tickets WHERE t_num = ?";
	public static final String SELECT_ALL_TICKETS = "SELECT * FROM tickets";
	public static final String SELECT_RECENTLY_ADDED_TICKET_NUM = "SELECT t_num FROM tickets WHERE dp_city = ? AND ar_city = ? AND dp_datetime = ? AND ar_datetime = ? AND passenger = ?";
	
	private JdbcTemplate jdbcTemplate;
	
	public TicketDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int addTicket(Ticket ticket) {
		String departureDateTime = ticket.getArrDate().toString().concat(ticket.getArrTime().toString());
		String arrivalDateTime = ticket.getArrDate().toString().concat(ticket.getArrTime().toString());
		
		Object[] params = new Object[] {ticket.getDepCountry(), ticket.getDepCity(), ticket.getArrCountry(), ticket.getArrCity(), departureDateTime, 
				arrivalDateTime, ticket.getPrice(), ticket.getStatus(), ticket.getPassenger()};
		int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.CHAR, Types.INTEGER};
		jdbcTemplate.update(INSERT_NEW_TICKET, params, types);
		return getRecentlyAddedTicketNumber(ticket); 
	}
	
	@Override
	public void updateStatus(int ticketNum, char status) {
		Object[] params = new Object[] {status, ticketNum};
		int[] types = new int[] {Types.VARCHAR, Types.INTEGER};
		jdbcTemplate.update(UPDATE_TICKET, params, types);
	}

	@Override
	public void deleteTicket(int ticketNum) {
		Object[] params = new Object[] {ticketNum};
		jdbcTemplate.update(DELETE_TICKET, params);
	}

	@Override
	public Ticket getTicketByNum(int num) {
		Object[] params = new Object[] {num};
		List<Ticket> tickets = jdbcTemplate.query(SELECT_TICKET_BY_NUM, params, new TicketRowMapper());
		return tickets.get(0);
	}

	private int getRecentlyAddedTicketNumber(Ticket ticket) {
		Object[] params = new Object[] {ticket.getDepCity(), ticket.getArrCity(), ticket.getDepartDate(), ticket.getArrDate(), ticket.getPassenger()};
		int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
		return jdbcTemplate.query(SELECT_RECENTLY_ADDED_TICKET_NUM, params, types, new IntegerRowMapper()).get(0);
	}

	@Override
	public List<Ticket> getAllTickets() {
		return jdbcTemplate.query(SELECT_ALL_TICKETS, new TicketRowMapper());
	}
}
