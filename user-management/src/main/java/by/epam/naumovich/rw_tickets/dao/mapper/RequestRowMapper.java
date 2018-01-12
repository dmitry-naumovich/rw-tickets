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
	public GroupRequest mapRow(ResultSet arg0, int arg1) throws SQLException {
		GroupRequest req = new GroupRequest();
		req.setRq_num(arg0.getInt(1));
		req.setFrom_user(arg0.getInt(2));
		req.setTo_user(arg0.getInt(3));
		req.setGrId(arg0.getInt(4));
		req.setCreateDate(Date.valueOf(arg0.getString(5).substring(0, 10)));
		req.setCreateTime(Time.valueOf(arg0.getString(5).substring(11, 19)));
		req.setCloseDate(Date.valueOf(arg0.getString(6).substring(0, 10)));
		req.setCloseTime(Time.valueOf(arg0.getString(6).substring(11, 19)));
		req.setStatus(arg0.getString(7).charAt(0));
		req.setComment(arg0.getString(8));
		return req;
	}

}
