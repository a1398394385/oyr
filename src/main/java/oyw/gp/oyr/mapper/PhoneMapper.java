package oyw.gp.oyr.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import oyw.gp.oyr.entity.Phone;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-21
 */
public interface PhoneMapper extends BaseMapper<Phone>
{
    List<Phone> getPhonesByBrand(int brand);
}
