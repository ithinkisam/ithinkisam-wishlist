package com.ithinkisam.wishlist.repository.postgres;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ithinkisam.wishlist.domain.Wish;
import com.ithinkisam.wishlist.repository.WishRepository;

@Repository("postgresWishRepository")
public class PostgresWishRepository extends AbstractPostgresRepository implements WishRepository {

	@Override
	public Wish findById(int id) {
		String sql = "select * from wishes where wid = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", id);
		
		try {
			return jdbcTemplate.queryForObject(sql, parameters, PostgresWishMapper.DEFAULT);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Wish> findByUser(String username) {
		String sql = "select * from wishes where username = :username order by wid";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username);
		
		return jdbcTemplate.query(sql, parameters, PostgresWishMapper.DEFAULT);
	}

	@Override
	public List<Wish> findByFulfiller(String username) {
		String sql = "select * from wishes where fulfiller = :username order by wid";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username);
		
		return jdbcTemplate.query(sql, parameters, PostgresWishMapper.DEFAULT);
	}

	@Override
	public Wish save(String description, String username) {
		String sql = "insert into wishes (username, description) values (:username, :description)";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username)
				.addValue("description", description);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(sql, parameters, keyHolder, new String[] { "wid" });
		return findById(keyHolder.getKey().intValue());
	}

	@Override
	public void update(int id, String description) {
		String sql = "update wishes set description = :description where wid = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("description", description)
				.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void fulfill(int id, String username) {
		String sql = "update wishes set fulfiller = :username where wid = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username)
				.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void unfulfill(int id) {
		String sql = "update wishes set fulfiller = null where wid = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void remove(int id) {
		String sql = "delete from wishes where wid = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

}
