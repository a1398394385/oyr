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
public class AuthController
{
    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        user = userService.login(user);
        if (user == null)
            return Response.error(300, "手机号或密码错误!");
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("id", user.getId());
        httpSession.setAttribute("telephone", user.getTelephone());
        httpSession.setAttribute("username", user.getUsername());
        return Response.result(200);
    }

    @PostMapping(value = "/logout")
    public Response logout() {
        HttpSession httpSession = httpServletRequest.getSession(false);
        httpSession.invalidate();
        return Response.result(200, "登出成功");
    }

}