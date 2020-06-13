package oyw.gp.oyr;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import oyw.gp.oyr.entity.Admin;
import oyw.gp.oyr.mapper.AdminMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TimeAutoTest {

    @Autowired
    AdminMapper adminMapper;

    @Test
    @Commit
    public void testInsert() {
        Admin admin = new Admin("name", "password", "1");
        adminMapper.insert(admin);
        // Assert.assertNotNull(admin.getCreateTime());
        // Assert.assertNotNull(admin.getUpdateTime());
        // Assert.assertEquals(result, 1);
    }

    @Test
    @Rollback
    public void testUpdate() {
        final Admin admin = new Admin("name", "password", "1");
        adminMapper.insert(admin);
        admin.setName("xxxxx");
        final int result = adminMapper.updateById(admin);
        Assert.assertEquals(admin.getName(), "xxxxx");
        Assert.assertEquals(result, 1);
    }
}
