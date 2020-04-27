package oyw.gp.oyr.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import oyw.gp.oyr.entity.Phone;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-21
 */
@CacheConfig(cacheNames = "phones")
public interface PhoneService extends IService<Phone>
{
    // @Cacheable(key = "#brand")
    public List<Phone> getPhonesByBrand(int brand);
}
