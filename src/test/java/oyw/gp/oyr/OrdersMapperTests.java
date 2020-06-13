package oyw.gp.oyr;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import oyw.gp.oyr.entity.Orders;
import oyw.gp.oyr.mapper.OrdersMapper;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrdersMapperTests {
    @Autowired
    OrdersMapper ordersMapper;

    @Test
    public void testGetOrdersByUserId() {
        List<Orders> orders = ordersMapper.getOrdersByUserId(new Long(3));
        log.error("===========================================", "");
        log.error(orders.get(0).toString(), "");
        Assert.assertEquals(orders.size(), 4);
    }

}
