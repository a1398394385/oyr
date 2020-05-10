package oyw.gp.oyr.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.Admin;
import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.service.AdminService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author OuYangWei
 * @since 2020-05-05
 */
@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    AdminService adminService;

    @GetMapping(value = "/")
    public Response<Object> selectAllAdmin() {
        List<Admin> list = adminService.list();
        return Response.result(200, list);
    }

    @PutMapping(value = "/{id}")
    public Response<Object> update(@PathVariable Long id, @RequestBody Admin admin) {
        admin.setId(id);
        if (adminService.updateById(admin)) { return Response.result(200); }
        return Response.error(400, "管理员不存在");
    }

    @PostMapping(value = "/")
    public Response<Object> create(@RequestBody Admin admin) {
        admin.setName(Long.toString(System.currentTimeMillis()));
        if (adminService.save(admin)) { return Response.result(200, admin); }
        return Response.error(500, "管理员创建失败");
    }

    @DeleteMapping(value = "/{id}")
    public Response<Object> delete(@PathVariable Long id) {
        if (adminService.removeById(id)) { return Response.result(200); }
        return Response.error(400, "管理员不存在");
    }

    @PostMapping(value = "/login")
    public Response<Object> login(@RequestBody Map<String, String> request) {
        if (adminService.login(request.get("name"), request.get("password"))) {
            return Response.result(200);
        }
        return Response.error(400, "用户或密码错误");
    }

}
