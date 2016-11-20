package com.ithinkisam.wishlist.repository.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ithinkisam.wishlist.domain.Wish;

public class PostgresWishMapper implements RowMapper<Wish> {

	public static final PostgresWishMapper DEFAULT = new PostgresWishMapper();
	
	@Override
	public Wish mapRow(ResultSet rs, int i) throws SQLException {
		return new Wish(rs.getInt("wid"),
				rs.getString("username"),
				rs.getString("description"),
				rs.getString("purchaser") != null);
	}

}
