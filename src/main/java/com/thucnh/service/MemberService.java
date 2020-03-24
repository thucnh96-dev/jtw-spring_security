package com.thucnh.service;

import com.thucnh.model.Member;

public interface MemberService {
    Member save(Member member);
    Member findByEmail(String email);
    Member findById(Long id);
}
