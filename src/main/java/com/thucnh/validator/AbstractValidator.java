package com.thucnh.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractValidator<INPUT, EXCEPTION extends Exception> implements AbstractValidatorThrow<INPUT, EXCEPTION> {

    @Autowired
    @Qualifier("existingValidator")
    protected ExistingValidator existingValidator;

    @Override
    public abstract void valid(INPUT t) throws EXCEPTION;

}
