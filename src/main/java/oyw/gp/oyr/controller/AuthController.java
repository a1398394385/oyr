package oyw.gp.oyr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.service.UserService;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

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
    public Response login(@RequestBody User user) {
        HttpSession httpSession = httpServletRequest.getSession();
        if (userService.login(user)){
            httpSession.setAttribute("id", user.getId());
            httpSession.setAttribute("telephone", user.getTelephone());
            httpSession.setAttribute("username", user.getUsername());
            //System.out.println(httpSession.getId());
            return new Response().result(200);
        }else {
            return new Response().error(300, "手机号或密码错误!");
        }
    }
}