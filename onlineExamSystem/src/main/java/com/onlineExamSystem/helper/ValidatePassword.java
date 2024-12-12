package com.onlineExamSystem.helper;

import org.springframework.stereotype.Component;

@Component
public class ValidatePassword {

	public boolean validatePassword(String password) {
		boolean result = false;
		try {
			if (password != null) {
				String MIN_LENGTH = "8";
				String MAX_LENGTH = "20";
				boolean SPECIAL_CHAR_NEEDED = false;

				String ONE_DIGIT = "(?=.*[0-9])";
				String LOWER_CASE = "(?=.*[a-z])";
				String UPPER_CASE = "(?=.*[A-Z])";
				String SPECIAL_CHAR = SPECIAL_CHAR_NEEDED ? "(?=.*[@#$%^&+=])" : "";
				String NO_SPACE = "(?=\\S+$)";

				String MIN_MAX_CHAR = ".{" + MIN_LENGTH + "," + MAX_LENGTH + "}";
				String PATTERN = ONE_DIGIT + LOWER_CASE + UPPER_CASE + SPECIAL_CHAR + NO_SPACE + MIN_MAX_CHAR;

				result = password.matches(PATTERN);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
}
