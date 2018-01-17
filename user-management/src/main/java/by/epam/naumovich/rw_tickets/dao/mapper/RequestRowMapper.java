package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.jdbc.core.RowMapper;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;

/**
 * Spring JDBC RowMapper implementation which maps GroupRequest table in database on GroupRequest entity.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class RequestRowMapper implements RowMapper<GroupRequest> {

	@Override
	public GroupRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		return GroupRequest.builder()
				.rq_num(rs.getInt(1))
				.from_user(rs.getInt(2))
				.to_user(rs.getInt(3))
				.grId(rs.getInt(4))
				.createDate(Date.valueOf(rs.getString(5).substring(0, 10)))
				.createTime(Time.valueOf(rs.getString(5).substring(11, 19)))
				.closeDate(Date.valueOf(rs.getString(6).substring(0, 10)))
				.closeTime(Time.valueOf(rs.getString(6).substring(11, 19)))
				.status(rs.getString(7).charAt(0))
				.comment(rs.getString(8))
				.build();
	}

}
