package oyw.gp.oyr.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController
{
    @Autowired
    HttpServletRequest httpServletRequest;

    @GetMapping(value = {"/", "/home"})
    public String home(HashMap<String, Object> map) {
        return "/home";
    }

    @GetMapping("/recycle/{id}")
    public String recycle(HashMap<String, Object> map, @PathVariable Long id) {
        return "/recycle";
    }

    @GetMapping("/account")
    public String account(HashMap<String, Object> map) {
        return "/account";
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
