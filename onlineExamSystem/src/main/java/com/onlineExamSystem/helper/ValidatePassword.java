package com.onlineExamSystem.helper;

import org.springframework.stereotype.Component;

@Component
public class ValidatePassword {

	public boolean validateUserPassword(String password) {
	    if (password == null) {
	        return false; // Early return for null input
	    }

	    // Password constraints
	    int minLength = 8;
	    int maxLength = 20;
	    boolean specialCharNeeded = false;

	    // Regex components
	    String oneDigit = "(?=.*[0-9])";          // At least one digit
	    String lowerCase = "(?=.*[a-z])";         // At least one lowercase letter
	    String upperCase = "(?=.*[A-Z])";         // At least one uppercase letter
	    String specialChar = specialCharNeeded ? "(?=.*[@#$%^&+=])" : ""; // Optional special character
	    String noSpace = "(?=\\S+$)";             // No spaces allowed
	    String lengthConstraint = ".{" + minLength + "," + maxLength + "}"; // Length constraints

	    // Combine all constraints into a single pattern
	    String pattern = oneDigit + lowerCase + upperCase + specialChar + noSpace + lengthConstraint;

	    // Validate password against the pattern
	    return password.matches(pattern);
	}
}
