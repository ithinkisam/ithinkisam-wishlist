package com.ithinkisam.wishlist.repository.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ithinkisam.wishlist.domain.Event;
import com.ithinkisam.wishlist.domain.User;

public class PostgresEventMapper implements RowMapper<Event> {

	public static final PostgresEventMapper DEFAULT = new PostgresEventMapper();
	
	@Override
	public Event mapRow(ResultSet rs, int i) throws SQLException {
		Event event = new Event(rs.getInt("eid"));
		event.setName(rs.getString("name"));
		event.setDescription(rs.getString("description"));
		event.setDate(rs.getTimestamp("date").toLocalDateTime());
		
		User admin = new User();
		admin.setUsername(rs.getString("admin"));
		event.setAdmin(admin);
		return event;
	}

}
