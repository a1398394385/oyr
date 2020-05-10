package oyw.gp.oyr.service;

import oyw.gp.oyr.entity.Admin;
import oyw.gp.oyr.entity.Response;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-05
 */
public interface AdminService extends IService<Admin> {
    Boolean login(String name, String password);
}
