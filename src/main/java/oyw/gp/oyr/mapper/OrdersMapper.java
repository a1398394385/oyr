package oyw.gp.oyr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oyw.gp.oyr.entity.Orders;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-01
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    public List<Orders> getOrdersByUserId(Long id);

    public List<Orders> getOrders();
}
