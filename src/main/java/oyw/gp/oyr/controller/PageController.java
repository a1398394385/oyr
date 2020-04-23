package oyw.gp.oyr.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import oyw.gp.oyr.entity.Auth;

@Controller
public class PageController
{
    @Autowired
    HttpServletRequest httpServletRequest;

    @GetMapping(value = { "/", "/home" })
    public String home(HashMap<String, Object> map) {
        map.putAll(new Auth(httpServletRequest).getSession());
        return "/home";
    }

    @GetMapping("/recycle/{id}")
    public String recycle(HashMap<String, Object> map, @PathVariable Long id) {
        map.putAll(new Auth(httpServletRequest).getSession());
        return "/recycle";
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