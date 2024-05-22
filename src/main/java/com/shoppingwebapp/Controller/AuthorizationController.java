package com.shoppingwebapp.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Model.Member;

//@CrossOrigin(allowCredentials = "true", origins = "http://localhost:8080", allowedHeaders = "http://localhost:8080") // set
// CORS
@RestController // This means that this class is a Controller
public class AuthorizationController {
    @Autowired
    MemberRepository memberRepository;

    @PostMapping(path = "authorize")
    public String authorize(HttpSession httpSession) {
        Object memberId = httpSession.getAttribute("userId");
        if (memberId == null) {// The session is not binded from a user.
            return "Fail!";
        } else {
            String identity;
            Member member;
            member = memberRepository.findById(Integer.parseInt(memberId.toString())).get();
            identity = member.getAdmin() ? "admin" : "member";
            return identity;
        }
    }
}
