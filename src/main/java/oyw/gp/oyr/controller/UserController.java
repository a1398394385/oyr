package oyw.gp.oyr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.service.UserService;

/**
 * @author OuYangWei
 * @since 2020-04-19
 */
@CrossOrigin(value = {"*"})
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
    @GetMapping(value = "/")
    public Response<Object> index() {
        return Response.result(200, userService.list());
    }

    /**
     * 处理"/user/"的POST请求，用来创建User
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    public Response<Object> create(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 处理"/user/{id}"的GET请求，用来获取url中id值的User信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Response<Object> show(@PathVariable Long id) {
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
    public Response<Object> update(@PathVariable Long id, @RequestBody User user) {
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
    public Response<Object> delete(@PathVariable Long id) {

        try {
            userService.removeById(id);
            return Response.result(200);
        } catch (Exception e) {
            return Response.error(404, "用户不存在");
        }
    }
}
