package oyw.gp.oyr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.DeleteMapping;
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> 1dce8e1decff2b4dd8d604b761de02126c310601
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.Phone;
import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.service.PhoneService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-21
 */
@CrossOrigin(value = {"*"})
@RestController
@RequestMapping("/phone")
public class PhoneController
{
    @Autowired
    PhoneService phoneService;

    @GetMapping("/brand/{brand}")
    public Response<Object> getPhonesByBrand(@PathVariable int brand) {
        List<Phone> phones = phoneService.getPhonesByBrand(brand);
        if (phones == null)
            return Response.error(400, "手机品牌不存在");
        return Response.result(200, phones);
    }

    @GetMapping("/{id}")
    public Response<Object> show(@PathVariable Long id) {
        try {
            Phone phone = phoneService.getById(id);
            return Response.result(200, phone);
        } catch (Exception e) {
            return Response.error(404, "手机型号不存在");
        }
    }

    @GetMapping(value = "/")
    public Response<Object> getAllPhone() {
        return Response.result(200, phoneService.list());
    }

    @DeleteMapping(value = "/{id}")
    public Response<Object> delete(@PathVariable Long id) {
        if (phoneService.removeById(id)) { return Response.result(200); }
        return Response.error(400, "手机不存在");
    }

    @PutMapping(value = "/{id}")
    public Response<Object> putMethodName(@PathVariable Long id,
            @RequestBody Phone phone) {
        phone.setId(id);
        if (phoneService.updateById(phone)) { return Response.result(200); }
        return Response.error(400, "手机不存在");

    }

}
