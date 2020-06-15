package oyw.gp.oyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oyw.gp.oyr.entity.Admin;
import oyw.gp.oyr.mapper.AdminMapper;
import oyw.gp.oyr.service.AdminService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-05
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Boolean login(String name, String password) {

        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.ne("authority", 0).eq("name", name).eq("password", password);
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin != null) {
            return true;
        }
        return false;
    }
}
