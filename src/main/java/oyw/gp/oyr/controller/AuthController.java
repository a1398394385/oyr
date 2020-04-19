package oyw.gp.oyr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController
{
    @RequestMapping("/register")
    public String register() {
        return "/register";
    }
}