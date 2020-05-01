package oyw.gp.oyr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrdersController
{
    @Autowired
    OrdersService orderService;

    @GetMapping(value = "/")
    public Response<Object> index() {
        return Response.result(200, orderService.list());
    }

    @PostMapping(value = "/")
    public Response<Object> create(@RequestBody Orders order) {
        log.error(order.toString(), "");
        return Response.result(200);
        // if (orderService.save(order))
        // return Response.result(200);
        // else
        // return Response.error(500, null);
    }
}

