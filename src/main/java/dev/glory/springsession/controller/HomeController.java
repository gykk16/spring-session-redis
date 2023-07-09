package dev.glory.springsession.controller;

import java.security.Principal;

import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final String HOME_VIEW_COUNT = "homeViewCount";

    @GetMapping("/")
    public String home(Principal principal, HttpSession session) {
        incrementCount(session, HOME_VIEW_COUNT);
        return "Hello, " + principal.getName() + "!";
    }

    @GetMapping("/count")
    public String count(HttpSession session) {
        return "Home view count: " + session.getAttribute(HOME_VIEW_COUNT);
    }

    private void incrementCount(HttpSession session, String attr) {
        int homeViewCount = session.getAttribute(attr) == null ? 0 : (Integer)session.getAttribute(attr);
        session.setAttribute(attr, ++homeViewCount);
    }
}
