package oyw.gp.oyr.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import oyw.gp.oyr.entity.Area;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-30
 */
@CacheConfig(cacheNames = "area")
public interface AreaService extends IService<Area> {
    @Cacheable(key = "#pid")
    public List<Area> getAreaByPid(int pid);
}
