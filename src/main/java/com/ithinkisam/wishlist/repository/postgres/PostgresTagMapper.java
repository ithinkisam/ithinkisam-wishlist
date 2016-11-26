package com.ithinkisam.wishlist.repository.postgres;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ithinkisam.wishlist.domain.Tag;

public class PostgresTagMapper implements RowMapper<Tag> {

	public static final PostgresTagMapper DEFAULT = new PostgresTagMapper();

	@Override
	public Tag mapRow(ResultSet rs, int i) throws SQLException {
		Tag tag = new Tag(rs.getInt("wid"));
		try {
			tag.setUrl(new URL(rs.getString("tag")));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
	}

}
