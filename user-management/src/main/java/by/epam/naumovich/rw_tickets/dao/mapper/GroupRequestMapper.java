package by.epam.naumovich.rw_tickets.dao.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.jdbc.core.RowMapper;

import by.epam.naumovich.rw_tickets.entity.GroupRequest;

public class GroupRequestMapper implements RowMapper<GroupRequest> {

	@Override
	public GroupRequest mapRow(ResultSet arg0, int arg1) throws SQLException {
		GroupRequest req = new GroupRequest();
		req.setRq_num(arg0.getInt(1));
		req.setFrom_user(arg0.getInt(2));
		req.setTo_user(arg0.getInt(3));
		req.setGr_id(arg0.getInt(4));
		req.setCreateDate(Date.valueOf(arg0.getString(5).substring(0, 10)));
		req.setCreateTime(Time.valueOf(arg0.getString(5).substring(11, 19)));
		req.setCloseDate(Date.valueOf(arg0.getString(6).substring(0, 10)));
		req.setCloseTime(Time.valueOf(arg0.getString(6).substring(11, 19)));
		req.setStatus(arg0.getString(7).charAt(0));
		req.setRq_comment(arg0.getString(8));
		return req;
	}

}
