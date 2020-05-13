package oyw.gp.oyr.mapper;

import oyw.gp.oyr.entity.Orders;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-01
 */
public interface OrdersMapper extends BaseMapper<Orders>
{
    public List<Orders> getOrdersByUserId(Long id);

    public List<Orders> getOrders();
}
