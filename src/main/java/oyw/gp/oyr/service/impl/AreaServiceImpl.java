package oyw.gp.oyr.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oyw.gp.oyr.entity.Area;
import oyw.gp.oyr.mapper.AreaMapper;
import oyw.gp.oyr.service.AreaService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-30
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Autowired
    AreaMapper areaMapper;

    @Override
    public List<Area> getAreaByPid(int pid) {
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        return areaMapper.selectList(queryWrapper);
    }
}
