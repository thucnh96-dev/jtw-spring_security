package com.thucnh.validator.comon;

import com.thucnh.payload.request.LoginRequest;
import com.thucnh.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import static org.springframework.util.Assert.isTrue;

@Component("emailValidator")
public class EmailValidator extends AbstractValidator<String, IllegalArgumentException> {

    static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    @Override
    public void valid(String email) throws IllegalArgumentException {
      existingValidator.validateNullOrEmpty(email,"email");
        isTrue(!email.matches(EMAIL_REGEX), "Email not valid");

    }
}
