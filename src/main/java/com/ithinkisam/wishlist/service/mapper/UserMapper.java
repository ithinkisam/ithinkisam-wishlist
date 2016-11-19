package com.ithinkisam.wishlist.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ithinkisam.wishlist.domain.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int i) throws SQLException {
		return new User(rs.getInt("uid"),
				rs.getString("emailAddress"),
				rs.getString("first_name"),
				rs.getString("last_name"));
	}

}
