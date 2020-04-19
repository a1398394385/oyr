package oyw.gp.oyr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController
{
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "/register";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request) {
        User user = new User();
        return userService.register(user);
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        return userService.login(request.getParameter("username"), request.getParameter("password"));
    }
}