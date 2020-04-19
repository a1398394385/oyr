package oyw.gp.oyr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController
{
    @GetMapping("/register")
    public String showRegisterPage() {
        return "/register";
    }

    @PostMapping("/register")
    public String register() {

    }

}