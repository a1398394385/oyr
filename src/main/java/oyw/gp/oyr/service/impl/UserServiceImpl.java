package oyw.gp.oyr.service.impl;

import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.mapper.UserMapper;
import oyw.gp.oyr.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
