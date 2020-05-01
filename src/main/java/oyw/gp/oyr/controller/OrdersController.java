package oyw.gp.oyr.controller;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.Orders;
import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.service.OrdersService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-01
 */
@RestController
@RequestMapping("/orders")
public class OrdersController
{
    @Autowired
    OrdersService ordersService;

    @GetMapping(value = "/")
    public Response<Object> index() {
        return Response.result(200, ordersService.list());
    }

    @PostMapping(value = "/")
    public Response<Object> create(@RequestBody Orders order) {
        order.setCreateTime(LocalDateTime.now());
        if (ordersService.save(order))
            return Response.result(200);
        else
            return Response.error(500, null);
    }
}

