package oyw.gp.oyr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-19
 */
public interface UserService extends IService<User> {
    public User login(User user);

    public Response<Object> register(User user);
}
