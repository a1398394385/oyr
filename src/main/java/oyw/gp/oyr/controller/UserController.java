package oyw.gp.oyr.controller;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.service.UserService;

/**
 * @author OuYangWei
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 处理"/user/"的GET请求，用来获取用户列表
     *
     * @return
     */
    @GetMapping("/")
    public Response index() {
        return Response.result(200, userService.list());
    }

    /**
     * 处理"/user/"的POST请求，用来创建User
     * 
     * @param user
     * @return
     */
    @PostMapping("/")
    public Response create(@RequestBody User user) {

        return userService.register(user);

    }

    /**
     * 处理"/user/{id}"的GET请求，用来获取url中id值的User信息
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Response show(@PathVariable Long id) {

        try {
            User user = userService.getById(id);
            return Response.result(200, user);
        } catch (Exception e) {
            return Response.error(404, "用户不存在");
        }
    }

    /**
     * 处理"/user/{id}"的PUT请求，用来更新url中id值的User信息
     * 
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public Response update(@PathVariable Long id, User user) {

        try {
            user.setId(id);
            userService.updateById(user);
            return Response.result(200, user);
        } catch (Exception e) {
            return Response.error(404, "用户不存在");
        }
    }

    /**
     * 处理"/user/{id}"的DELETE请求，用来删除url中id值的User信息
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {

        try {
            userService.removeById(id);
            return Response.result(204);
        } catch (Exception e) {
            return Response.error(404, "用户不存在");
        }
    }
}
