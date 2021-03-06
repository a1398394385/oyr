package oyw.gp.oyr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @Autowired
    HttpServletRequest httpServletRequest;

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "/home";
    }

    @GetMapping("/recycle/{id}")
    public String recycle(@PathVariable Long id) {
        return "/recycle";
    }

    @GetMapping(value = "/order")
    public String order() {
        return "/order";
    }

    @GetMapping("/account")
    public String account() {
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

    /**
     * Admin Pages
     */
    @GetMapping(value = "/manage/{page}")
    public String adminPages(@PathVariable String page) {
        return "/manage/" + page;
    }
}
