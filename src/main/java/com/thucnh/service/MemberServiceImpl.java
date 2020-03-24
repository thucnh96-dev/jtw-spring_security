package com.thucnh.service;

import com.thucnh.model.Member;
import com.thucnh.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
}
