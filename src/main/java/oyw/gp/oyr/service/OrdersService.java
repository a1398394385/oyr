package oyw.gp.oyr.service;

import oyw.gp.oyr.entity.Orders;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-01
 */
public interface OrdersService extends IService<Orders>
{
    public List<Orders> getOrdersByUserId(Long id);
}
