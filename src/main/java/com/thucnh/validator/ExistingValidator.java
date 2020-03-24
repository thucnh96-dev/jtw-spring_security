package com.thucnh.validator;

import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.util.Assert.isTrue;

@Component("existingValidator")
public class ExistingValidator {

    public void validate(Object object, String objectName) throws IllegalArgumentException {
        isTrue(object == null, "Đã tồn tại " + objectName);
    }


    public void validateNotExist(Object object, String objectName) throws IllegalArgumentException {
        isTrue(object != null, objectName + " Không tồn tại");
    }
    public void validateList(Object object, String objectName) throws IllegalArgumentException {
        isTrue((object == null || (object != null && ((List)object).size() == 0)), "Đã tồn tại " + objectName);
    }

    public void validateArrays(Object object, String objectName) throws IllegalArgumentException {
        isTrue((object == null || ((List)object).size() != 0), "Đã tồn tại " + objectName);
    }


    public void validateNullOrEmpty(Object object, String objectName) throws IllegalArgumentException {
        isTrue((object != null), objectName + " Không thể để trống");
        if(object != null) {
            isTrue((!"0".equals(object.toString()) && !"".equals(object.toString()) ), objectName + " Không thể để trống");
        }
    }
}
