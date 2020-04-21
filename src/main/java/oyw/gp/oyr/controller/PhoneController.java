package oyw.gp.oyr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.Phone;
import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.service.PhoneService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/phone")
public class PhoneController
{
    @Autowired
    PhoneService phoneService;

    @GetMapping("/brand/{brand}")
    public Response getPhonesByBrand(@PathVariable int brand) {
        List<Phone> phones = phoneService.getPhonesByBrand(brand);
        if (phones == null)
            return new Response<>().error(400, "手机品牌不存在");
        return new Response<>().result(200, phones);
    }

    @GetMapping("/{id}")
    public Response show(@PathVariable Long id) {

        try {
            Phone phone = phoneService.getById(id);
            return new Response<>().result(200, phone);
        } catch (Exception e) {
            return new Response<>().error(404, "手机型号不存在");
        }
    }
}
