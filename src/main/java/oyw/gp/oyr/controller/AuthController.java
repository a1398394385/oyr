package oyw.gp.oyr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import oyw.gp.oyr.entity.Result;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
public class AuthController
{
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView= new ModelAndView("/register");
        return modelAndView;
    }


    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(User user) {
        user.setCreateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
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