package com.thucnh.payload.request;

import lombok.Data;


@Data
public class LoginRequest {

    private String email;

    private String passWord;

}
