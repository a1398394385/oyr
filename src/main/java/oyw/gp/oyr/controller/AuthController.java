package oyw.gp.oyr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
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
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return userService.login(username, password);
    }

}