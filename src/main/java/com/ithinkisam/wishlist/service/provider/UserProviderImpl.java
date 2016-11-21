package com.ithinkisam.wishlist.service.provider;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ithinkisam.wishlist.config._Constants;
import com.ithinkisam.wishlist.domain.NewUser;
import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.repository.UserRepository;
import com.ithinkisam.wishlist.security.VerificationToken;
import com.ithinkisam.wishlist.service.UserProvider;
import com.ithinkisam.wishlist.service.exception.EmailExistsException;
import com.ithinkisam.wishlist.service.exception.UsernameExistsException;

@Service("userProvider")
public class UserProviderImpl implements UserProvider {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier(_Constants.REPOSITORY_TYPE + "UserRepository")
	private UserRepository userRepository;

	@Override
	public User registerNewUserAccount(NewUser newUser) throws EmailExistsException, UsernameExistsException {
		if (emailExists(newUser.getEmail())) {
			throw new EmailExistsException(
					"There is already an account with that email address: " + newUser.getEmail());
		}
		if (usernameExists(newUser.getUsername())) {
			throw new UsernameExistsException(
					"There is already an account with that username: " + newUser.getUsername());
		}
		
		User preparedUser = prepareNewUser(newUser);
		userRepository.save(preparedUser);
		return preparedUser;
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken verificationToken = new VerificationToken(token, user);
		userRepository.persistVerificationToken(verificationToken);
	}

	@Override
	public VerificationToken getVerificationToken(String token) {
		VerificationToken verificationToken = userRepository.getVerificationToken(token);
		return verificationToken == null ? null : verificationToken;
	}

	@Override
	public void enableNewUser(String username) {
		userRepository.enable(username);
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	private boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	private boolean usernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}

	private User prepareNewUser(NewUser newUser) {
		User user = new User();
		user.setUsername(newUser.getUsername());
		user.setEmail(newUser.getEmail());
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setRoles(Arrays.asList(_Constants.ROLE_USER));
		return user;
	}

}
