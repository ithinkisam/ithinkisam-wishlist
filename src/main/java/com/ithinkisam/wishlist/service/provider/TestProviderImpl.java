package com.ithinkisam.wishlist.service.provider;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.service.mapper.UserMapper;

@Service("testProvider")
public class TestProviderImpl extends AbstractServiceProvider implements TestProvider {

	@Override
	public List<User> getUsers() {
		return jdbcTemplate.query("select * from users", new UserMapper());
	}
	
}
