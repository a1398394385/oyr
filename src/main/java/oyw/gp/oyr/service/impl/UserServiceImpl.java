package oyw.gp.oyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.mapper.UserMapper;
import oyw.gp.oyr.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService
{
    @Autowired
    UserMapper userMapper;



    // @Override
    // public Result register(User user) {
    // Result response = new Result();
    // response.setSuccess(false);
    // response.setDetail(null);
    // QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    // queryWrapper.eq("username", user.getUsername());
    // User result = userMapper.selectOne(queryWrapper);

    // try {

    // if (result != null) {
    // response.setMsg("账号已被注册");
    // } else {
    // userMapper.insert(user);
    // response.setMsg("注册成功");
    // }
    // } catch (Exception e) {
    // response.setMsg(e.getMessage());
    // e.printStackTrace();
    // }

    // return response;
    // }

    @Override
    public boolean login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", user.getTelephone()).eq("password", user.getPassword());
        List<User> users = userMapper.selectList(queryWrapper);

        if (users.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

}
