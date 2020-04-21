package oyw.gp.oyr.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import oyw.gp.oyr.entity.Phone;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-21
 */
public interface PhoneService extends IService<Phone>
{
    public List<Phone> getPhonesByBrand(int brand);
}
