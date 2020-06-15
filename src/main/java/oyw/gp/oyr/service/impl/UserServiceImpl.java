package oyw.gp.oyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.mapper.UserMapper;
import oyw.gp.oyr.service.UserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", user.getTelephone()).eq("password", user.getPassword());
        User loginUser = userMapper.selectOne(queryWrapper);
        if (loginUser == null) {
            return null;
        }
        return loginUser;
    }

    @Override
    public Response<Object> register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", user.getTelephone());
        System.err.println(userMapper.selectOne(queryWrapper));
        if (userMapper.selectOne(queryWrapper) == null) {
            try {
                userMapper.insert(user);
                return Response.result(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Response.error(500, "用户已存在");
    }
}
