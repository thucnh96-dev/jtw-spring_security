package com.thucnh.validator;

public  interface AbstractValidatorThrow< INPUT extends Object, EXEPTION extends Exception>   {
    void valid(INPUT t) throws EXEPTION;
}
