package com.thucnh.controllers.memberAPI;

import com.thucnh.controllers.AbstractController;
import com.thucnh.helper.SecurityHelper;
import com.thucnh.model.Member;
import com.thucnh.model.Role;
import com.thucnh.model.enums.ROLE;
import com.thucnh.payload.request.LoginRequest;
import com.thucnh.payload.request.MessageResponse;
import com.thucnh.payload.request.SignupRequest;
import com.thucnh.payload.response.JwtResponse;
import com.thucnh.security.UserDetailImpl;
import com.thucnh.security.jtw.JwtUtils;
import com.thucnh.service.MemberService;
import com.thucnh.service.RoleService;
import com.thucnh.validator.auth.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController extends AbstractController {

    @Autowired(required = true)
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    MemberService memberService;

    @Autowired
    RoleService roleService;

    @Autowired
    @Qualifier("loginValidator")
    LoginValidator loginValidator;

    @Autowired
    SecurityHelper securityHelper;

    @Autowired
    MessageSource messageSource;

    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        loginValidator.valid(loginRequest);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassWord()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return responseUtil.successResponse(new JwtResponse(
                jwt,
                userDetails.getUsername(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {
        Member member = memberService.findByEmail(signupRequest.getEmail());
        if (member != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is exist"));
        }
        Member saveMember = new Member();
        Set<Role> memberRoles = new HashSet<>();

        saveMember.setEmail(signupRequest.getEmail());
        saveMember.setPassword(encoder.encode(signupRequest.getPassword()));

        if (signupRequest.getRoles() == null || signupRequest.getRoles().size() == 0) {
            Role role = roleService.findByName(ROLE.ROLE_USER); // ROLE_USER
            memberRoles.add(role);

        } else {
            signupRequest.getRoles().stream().forEach(roleRequest -> {
                Role role;
                switch (roleRequest) {
                    case "admin":
                        role = roleService.findByName(ROLE.ROLE_ADMIN); // ROLE_ADMIN
                        break;
                    case "master":
                        role = roleService.findByName(ROLE.ROLE_MASTER); // ROLE_MASTER
                        break;
                    default:
                        role = roleService.findByName(ROLE.ROLE_USER); // ROLE_USER
                        break;
                }
                memberRoles.add(role); // add role
            });
        }
        saveMember.addMemberRoles(memberRoles);

        saveMember = memberService.save(saveMember);

        String msg =  messageSource.getMessage("member.register.done",new Object[]{saveMember.getEmail()}, Locale.getDefault());

        return responseUtil.successResponse(new MessageResponse(msg));
    }
    @GetMapping(value = "/auth/wso2/complete")
    public ResponseEntity<?> login(){

        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = (HashMap<String, String>) authentication.getUserAuthentication().getDetails();
        // TODO
        // check request OAuth2Authentication for  data.
        String email= map.get("open_id") + "wso2.com";
        Member member =memberService.findByEmail(email);
        if (member == null){

            // save member
        }else{
            // save member
        }
        // generate token for user
        securityHelper.autologin(member.getEmail(),member.getPassword());
        String msg =  messageSource.getMessage("member.oauth2.done",null, Locale.getDefault());
        return responseUtil.successResponse(new MessageResponse(msg));
    }
    @GetMapping(value = "/github/complete")
    public ResponseEntity<?> git(){

        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = (HashMap<String, String>) authentication.getUserAuthentication().getDetails();
        // TODO
        // check request OAuth2Authentication for  data.
        String email= map.get("open_id") + "wso2.com";
        Member member =memberService.findByEmail(email);
        if (member == null){

            // save member
        }else{
            // save member
        }
        // generate token for user
        securityHelper.autologin(member.getEmail(),member.getPassword());
        String msg =  messageSource.getMessage("member.oauth2.done",null, Locale.getDefault());
        return responseUtil.successResponse(new MessageResponse(msg));
    }

}
