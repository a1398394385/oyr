package oyw.gp.oyr.service.impl;

import oyw.gp.oyr.entity.Order;
import oyw.gp.oyr.mapper.OrderMapper;
import oyw.gp.oyr.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
