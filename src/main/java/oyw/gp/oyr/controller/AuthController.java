package oyw.gp.oyr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.service.UserService;

@RestController
public class AuthController
{
    @Autowired
    private UserService userService;

    // @GetMapping("/register")
    // public ModelAndView showRegisterPage() {
    // ModelAndView modelAndView = new ModelAndView("/register");
    // return modelAndView;
    // }

    // @PostMapping("/register")
    // public Result register(User user) {
    // user.setCreateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
    // return userService.register(user);
    // }

    // @GetMapping("/login")
    // public ModelAndView showLoginPage() {
    // ModelAndView modelAndView = new ModelAndView("/login");
    // return modelAndView;
    // }

    @PostMapping("/login")
    public String login(final HttpServletRequest request) {
        return userService.login(request.getParameter("username"),
                request.getParameter("password"));
    }
}