package oyw.gp.oyr.service.impl;

import oyw.gp.oyr.entity.Orders;
import oyw.gp.oyr.mapper.OrdersMapper;
import oyw.gp.oyr.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-01
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
