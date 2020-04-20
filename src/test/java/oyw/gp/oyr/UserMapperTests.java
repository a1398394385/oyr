package oyw.gp.oyr;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.mapper.UserMapper;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests
{
    @Autowired
    UserMapper userMapper;

    @Test
    public void testUserMapper() {
        int count = userMapper.selectCount(null);
        Assert.assertEquals(0, count);
    }

    @Test
    public void testFindUserByName(){
        //User user = userMapper.findUserByName("admin");
        LocalDateTime localDateTime = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        /*User user = new User("test", "123456", 12341131L, "sss",localDateTime);
        userMapper.insertUser(user);
        System.out.println(user);*/
    }

}