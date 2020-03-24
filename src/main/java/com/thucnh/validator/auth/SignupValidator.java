package com.thucnh.validator.auth;

import com.thucnh.payload.request.SignupRequest;
import com.thucnh.validator.AbstractValidator;
import com.thucnh.validator.comon.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.springframework.util.Assert.isTrue;

@Component("signupValidator")
public class SignupValidator extends AbstractValidator<SignupRequest, IllegalArgumentException> {

    static final String PASSWORD_REGEX = ".{8,}";

    @Autowired
    @Qualifier("emailValidator")
    EmailValidator emailValidator;

    @Override
    public void valid(SignupRequest signupRequest) throws IllegalArgumentException {
        existingValidator.validateNullOrEmpty(signupRequest, "SignupRequest");
        existingValidator.validateNullOrEmpty(signupRequest.getEmail(), "email");
        existingValidator.validateNullOrEmpty(signupRequest.getPassword(), "password");
        emailValidator.valid(signupRequest.getEmail());
        isTrue(signupRequest.getPassword().matches(PASSWORD_REGEX), "Password length >= 8");
    }
}
