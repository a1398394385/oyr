package oyw.gp.oyr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oyw.gp.oyr.entity.Phone;
import oyw.gp.oyr.mapper.PhoneMapper;
import oyw.gp.oyr.service.PhoneService;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author OuYangWei
 * @since 2020-04-21
 */
@Slf4j
@Service
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements PhoneService {
    @Autowired
    PhoneMapper phoneMapper;

    @Override
    public List<Phone> getPhonesByBrand(int brand) {

        try {
            return phoneMapper.getPhonesByBrand(brand);
        } catch (Exception e) {
            log.error("手机品牌不存在：" + brand, e.getClass());
            return null;
        }
    }
}
