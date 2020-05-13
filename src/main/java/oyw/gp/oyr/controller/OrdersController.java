package oyw.gp.oyr.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.Orders;
import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.service.OrdersService;
import org.springframework.web.bind.annotation.RequestParam;



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

    @GetMapping(value = "/user/{id}")
    public Response<Object> getOrdersByUserId(@PathVariable Long id) {
        List<Orders> orders = ordersService.getOrdersByUserId(id);
        if (orders != null)
            return Response.result(200, orders);
        else
            return Response.error(500, "用户id不存在");
    }

    @DeleteMapping(value = "/{id}")
    public Response<Object> delete(@PathVariable Long id) {
        if (ordersService.removeById(id)) { return Response.result(200); }
        return Response.error(400, "管理员不存在");
    }

    /**
     * 带有model的级联查询
     * 
     * @param param
     * @return
     */
    @GetMapping(value = "/all")
    public Response<Object> getOr() {
        return Response.result(200, ordersService.getOrdes());
    }



    @PostMapping(value = "/")
    public Response<Object> create(@RequestBody Orders order) {
        order.setCreateTime(LocalDateTime.now());
        if (ordersService.save(order))
            return Response.result(200);
        else
            return Response.error(500, null);
    }

    @PutMapping(value = "/{id}")
    public Response<Object> update(@PathVariable Long id, @RequestBody Orders order) {
        order.setId(id);
        if (ordersService.updateById(order)) { return Response.result(200); }
        return Response.error(400, "管理员不存在");
    }
}

