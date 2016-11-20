package com.ithinkisam.wishlist.repository.postgres;

import java.sql.Timestamp;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.repository.UserRepository;
import com.ithinkisam.wishlist.security.VerificationToken;

@Repository("postgresUserRepository")
public class PostgresUserRepository extends AbstractPostgresRepository implements UserRepository {

	@Override
	public User findByEmail(String email) {
		String sql = "select * from users where email = :email";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("email", email);
		
		try {
			return jdbcTemplate.queryForObject(sql, parameters, PostgresUserMapper.DEFAULT);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public User findByUsername(String username) {
		String sql = "select * from users where username = :username";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username);
		
		try {
			return jdbcTemplate.queryForObject(sql, parameters, PostgresUserMapper.DEFAULT);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void save(User user) {
		String sql = "insert into users (username, password, first_name, last_name, email, enabled)"
				+ " values (:username, :password, :first_name, :last_name, :email, :enabled)";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", user.getUsername())
				.addValue("password", user.getPassword())
				.addValue("first_name", user.getFirstName())
				.addValue("last_name", user.getLastName())
				.addValue("email", user.getEmail())
				.addValue("enabled", Boolean.FALSE);
		
		jdbcTemplate.update(sql, parameters);
		
		sql = "insert into authorities (username, authority) values (:username, :authority)";
		for (String role : user.getRoles()) {
			parameters = new MapSqlParameterSource()
					.addValue("username", user.getUsername())
					.addValue("authority", role);
			jdbcTemplate.update(sql, parameters);
		}
	}

	@Override
	public void enable(String username) {
		String sql = "update users set enabled = true where username = :username";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username);
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void disable(String username) {
		String sql = "update users set enabled = false where username = :username";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username);
		
		jdbcTemplate.update(sql, parameters);
	}
	
	@Override
	public void updateInformation(User user) {
		String sql = "update users set "
				+ ",first_name = :first_name "
				+ ",last_name = :last_name "
				+ ",email = :email "
				+ "where username = :username";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", user.getUsername())
				.addValue("first_name", user.getFirstName())
				.addValue("last_name", user.getLastName())
				.addValue("email", user.getEmail());
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void updatePassword(String username, String existingPassword, String newPassword) {
		String sql = "update users set password = :newPassword where username = :username and password = :existingPassword";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username)
				.addValue("existingPassword", new BCryptPasswordEncoder().encode(existingPassword))
				.addValue("newPassword", new BCryptPasswordEncoder().encode(newPassword));
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void persistVerificationToken(VerificationToken token) {
		String sql = "update users set token = :token, expiration_date = :expirationDate where username = :username";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("token", token.getToken())
				.addValue("expirationDate", Timestamp.valueOf(token.getExpiryDate()))
				.addValue("username", token.getUser().getUsername());
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public VerificationToken getVerificationToken(String token) {
		String sql = "select * from users where token = :token";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("token", token);
		
		try {
			return jdbcTemplate.queryForObject(sql, parameters, PostgresVerificationTokenMapper.DEFAULT);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
