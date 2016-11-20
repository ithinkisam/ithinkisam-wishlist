package com.ithinkisam.wishlist.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ithinkisam.wishlist.domain.User;

public class UserMapper implements RowMapper<User> {

	public static final UserMapper DEFAULT = new UserMapper();
	
	@Override
	public User mapRow(ResultSet rs, int i) throws SQLException {
		return new User(rs.getString("username"),
				rs.getString("password"),
				rs.getString("first_name"),
				rs.getString("last_name"),
				rs.getString("email"),
				rs.getBoolean("enabled"));
	}

}
