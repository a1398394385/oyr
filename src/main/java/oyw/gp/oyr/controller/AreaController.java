package oyw.gp.oyr.controller;


import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.Area;
import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.service.AreaService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-30
 */
@RestController
@RequestMapping("/area")
public class AreaController
{
    @Autowired
    AreaService areaService;

    @GetMapping(value = "/{pid}")
    public Response<Object> getAreaByPid(@PathVariable int pid) {
        List<Area> areaData = areaService.getAreaByPid(pid);
        if (areaData != null)
            return Response.result(200, areaData.size() == 0 ? null : areaData);
        else
            return Response.error(400, "地区编号不存在");
    }
}
