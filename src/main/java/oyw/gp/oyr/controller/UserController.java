package oyw.gp.oyr.controller;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.service.UserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    /**
     * 处理"/user/"的GET请求，用来获取用户列表
     *
     * @return
     */
    @GetMapping("/")
    public List<User> index() {
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        return userService.list();
    }

    /**
     * 处理"/user/"的POST请求，用来创建User
     * 
     * @param user
     * @return
     */
    @PostMapping("/")
    public Boolean create(@RequestBody User user) {
        user.setCreateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        return userService.save(user);
    }

    /**
     * 处理"/user/{id}"的GET请求，用来获取url中id值的User信息
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User show(@PathVariable Long id) {
        return userService.getById(id);
    }

    /**
     * 处理"/user/{id}"的PUT请求，用来更新url中id值的User信息
     * 
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public Boolean update(@PathVariable Long id, User user) {
        user.setId(id);
        return userService.updateById(user);
    }

    /**
     * 处理"/user/{id}"的DELETE请求，用来删除url中id值的User信息
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return userService.removeById(id);
    }
}
