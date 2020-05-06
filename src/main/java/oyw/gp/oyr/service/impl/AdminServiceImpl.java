package oyw.gp.oyr.service.impl;

import oyw.gp.oyr.entity.Admin;
import oyw.gp.oyr.mapper.AdminMapper;
import oyw.gp.oyr.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-05
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService
{

}
