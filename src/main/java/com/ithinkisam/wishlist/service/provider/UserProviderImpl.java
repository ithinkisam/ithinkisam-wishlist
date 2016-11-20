package com.ithinkisam.wishlist.service.provider;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.service.mapper.UserMapper;

@Service("userProvider")
public class UserProviderImpl extends AbstractServiceProvider implements UserProvider {

	@Override
	public void addNewUser(User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into users (username, password, first_name, last_name, email, enabled)");
		sql.append(" values (:username, :password, :first_name, :last_name, :email, :enabled)");
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", user.getUsername())
				.addValue("password", new BCryptPasswordEncoder().encode(user.getPassword()))
				.addValue("first_name", user.getFirstName())
				.addValue("last_name", user.getLastName())
				.addValue("email", user.getEmail())
				.addValue("enabled", Boolean.FALSE);
		
		jdbcTemplate.update(sql.toString(), parameters);
	}

	@Override
	public User getUserByUsername(String username) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from users where username = :username");
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username);
		
		return jdbcTemplate.queryForObject(sql.toString(), parameters, UserMapper.DEFAULT);
	}

	@Override
	public User getUserByEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from users where email = :email");
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("email", email);
		
		return jdbcTemplate.queryForObject(sql.toString(), parameters, UserMapper.DEFAULT);
	}

}
