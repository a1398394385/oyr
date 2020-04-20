package oyw.gp.oyr.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController
{
    @Autowired
    HttpServletRequest httpServletRequest;

    @GetMapping("/home")
    public String home(HashMap<String, Object> map) {
        HttpSession session = httpServletRequest.getSession();
        map.put("id", session.getAttribute("id"));
        map.put("telephone", session.getAttribute("telephone"));
        map.put("username", session.getAttribute("username"));
        return "/home";
    }

    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

}