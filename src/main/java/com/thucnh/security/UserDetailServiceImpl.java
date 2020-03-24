package com.thucnh.security;

import com.thucnh.model.Member;
import com.thucnh.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw  new UsernameNotFoundException("UsernameNotFound");
        }
        return UserDetailImpl.build(member);
    }
}
