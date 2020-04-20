package oyw.gp.oyr.service;

import oyw.gp.oyr.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-19
 */
public interface UserService extends IService<User>
{
    // public Result register(User user);

    public boolean login(User user);
}
