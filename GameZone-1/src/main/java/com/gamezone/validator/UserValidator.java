package com.gamezone.validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gamezone.pojo.Gamer;

@Component
public class UserValidator implements Validator{
	   @Override
	    public boolean supports(Class<?> arg0) {

	        // return true;//works for all scenarios
	        //check for user class and all of its sub classes
	        return Gamer.class.isAssignableFrom(arg0);
	    }

	    @Override
	    public void validate(Object o, Errors errors) {
	        //if there is empty or whitespace below is triggered
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gamerName", "error-name", "Enter Name");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error-email", "Enter Email");
	    }
}
