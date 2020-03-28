package com.thucnh.controllers;

import com.thucnh.model.Member;
import com.thucnh.response.ResponseUtil;
import com.thucnh.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public abstract class AbstractController {

    @Autowired
    protected ResponseUtil responseUtil;

    @Autowired
    private MemberService memberService;

    protected Member memberAuth(Principal principal){
        Member member = memberService.findByEmail(principal.getName());
        return member;
    }

}
