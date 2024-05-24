package com.shoppingwebapp.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Model.Member;

@CrossOrigin(allowCredentials = "true", origins = "http://localhost:8080", allowedHeaders = "http://localhost:8080") // set
                                                                                                                     // CORS
@RestController // This means that this class is a Controller
@RequestMapping(path = "/auth") // This means URL's start with /demo (after Application path)
public class MemberAuthentication {
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping(path = "/login") // login
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Iterable<Member> iterable = memberRepository.findByUsername(username);

        for (Member member : iterable) {
            if (member.getPassword().equals(password)) {
                if (session.getAttribute("userId") == null) { // if login success and username is not in the session
                                                              // table
                    session.setAttribute("userId", member.getId());
                }
                return "Success!";
            }
        }
        return "Fail!";
    }

    @PostMapping(path = "/logout") // logout
    public String logout(HttpSession session) {
        if (session.getAttribute("userId") != null) {
            session.removeAttribute("userId");
            return "Success!";
        } else {
            return "Fail!";
        }
    }
}
