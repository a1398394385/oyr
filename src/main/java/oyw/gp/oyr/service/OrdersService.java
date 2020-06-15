package oyw.gp.oyr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import oyw.gp.oyr.entity.Orders;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-01
 */
public interface OrdersService extends IService<Orders> {
    public List<Orders> getOrdersByUserId(Long id);

    public List<Orders> getOrders();
}
