package com.thucnh.validator.auth;


import com.thucnh.payload.request.LoginRequest;
import com.thucnh.validator.AbstractValidator;
import com.thucnh.validator.comon.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.springframework.util.Assert.isTrue;

@Component("loginValidator")
public class LoginValidator extends AbstractValidator<LoginRequest, IllegalArgumentException> {

    static final String PASSWORD_REGEX = ".{8,}";

    @Autowired
    @Qualifier("emailValidator")
    EmailValidator emailValidator;

    @Override
    public void valid(LoginRequest loginRequest) throws IllegalArgumentException {

        existingValidator.validateNullOrEmpty(loginRequest, "LoginRequest");
        existingValidator.validateNullOrEmpty(loginRequest.getEmail(), "email");
        existingValidator.validateNullOrEmpty(loginRequest.getPassWord(), "password");
        emailValidator.valid(loginRequest.getEmail());
        isTrue(loginRequest.getPassWord().matches(PASSWORD_REGEX), "Password length >= 8");

    }
}
