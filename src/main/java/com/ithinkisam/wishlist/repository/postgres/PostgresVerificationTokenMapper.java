package com.ithinkisam.wishlist.repository.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.security.VerificationToken;

public class PostgresVerificationTokenMapper implements RowMapper<VerificationToken> {

	public static final PostgresVerificationTokenMapper DEFAULT = new PostgresVerificationTokenMapper();
	
	@Override
	public VerificationToken mapRow(ResultSet rs, int i) throws SQLException {
		return new VerificationToken(rs.getString("token"),
				rs.getTimestamp("expiration_date").toLocalDateTime(),
				new User(rs.getString("username"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("email"),
						rs.getBoolean("enabled")));
	}

}
