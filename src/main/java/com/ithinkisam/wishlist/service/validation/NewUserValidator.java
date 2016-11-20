package com.ithinkisam.wishlist.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ithinkisam.wishlist.domain.NewUser;

public class NewUserValidator implements Validator {

	private static final int USERNAME_MAX_LENGTH = 50;
	private static final int PASSWORD_MIN_LENGTH = 8;
	private static final int PASSWORD_MAX_LENGTH = 50;
	private static final int FIRST_NAME_MAX_LENGTH = 40;
	private static final int LAST_NAME_MAX_LENGTH = 50;
	private static final int EMAIL_MAX_LENGTH = 100;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	@Override
	public boolean supports(Class<?> clazz) {
		return NewUser.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		if (obj == null) {
			errors.reject("user", "validation.newuser.null");
		}
		NewUser user = (NewUser) obj;
		validateUsername(user.getUsername(), errors);
		validatePassword(user.getPassword(), errors);
		validateMatchingPassword(user.getPassword(), user.getMatchingPassword(), errors);
	}

	void validateUsername(String username, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "validation.newuser.username.empty");
		if (StringUtils.hasLength(username) && username.length() > USERNAME_MAX_LENGTH) {
			errors.rejectValue("username", "validation.newuser.username.tooLong", new Object[] { USERNAME_MAX_LENGTH },
					null);
		}
	}

	void validatePassword(String password, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validation.newuser.password.empty");
		if (StringUtils.hasLength(password)) {
			if (password.length() < PASSWORD_MIN_LENGTH) {
				errors.rejectValue("password", "validation.newuser.password.tooShort",
						new Object[] { PASSWORD_MIN_LENGTH }, null);
			} else if (password.length() > PASSWORD_MAX_LENGTH) {
				errors.rejectValue("password", "validation.newuser.password.tooLong", new Object[] { PASSWORD_MAX_LENGTH },
						null);
			}
		}
	}

	void validateMatchingPassword(String password, String matchingPassword, Errors errors) {
		if (StringUtils.hasLength(password) && StringUtils.hasLength(matchingPassword)
				&& !password.equals(matchingPassword)) {
			errors.reject("validation.newuser.password.doesNotMatch");
		}
	}

	void validateFirstName(String firstName, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "validation.newuser.firstName.empty");
		if (StringUtils.hasLength(firstName) && firstName.length() > FIRST_NAME_MAX_LENGTH) {
			errors.rejectValue("firstName", "validation.newuser.firstName.tooLong", new Object[] { FIRST_NAME_MAX_LENGTH },
					null);
		}
	}

	void validateLastName(String lastName, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "validation.newuser.lastName.empty");
		if (StringUtils.hasLength(lastName) && lastName.length() > LAST_NAME_MAX_LENGTH) {
			errors.rejectValue("lastName", "validation.newuser.lastName.tooLong", new Object[] { LAST_NAME_MAX_LENGTH },
					null);
		}
	}

	void validateEmail(String email, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "validation.newuser.email.empty");
		if (StringUtils.hasLength(email)) {
			if (email.length() > EMAIL_MAX_LENGTH) {
				errors.rejectValue("email", "validation.newuser.email.tooLong", new Object[] { EMAIL_MAX_LENGTH }, null);
			} else {
				Pattern pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher matcher = pattern.matcher(email);
				if (!matcher.matches()) {
					errors.rejectValue("email", "validation.newuser.email.invalidFormat");
				}
			}
		}
	}

}
