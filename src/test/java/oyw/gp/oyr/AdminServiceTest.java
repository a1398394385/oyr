package oyw.gp.oyr;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import oyw.gp.oyr.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminServiceTest {

    @Autowired
    private AdminService adminservie;

    @Test
    public void testLogin() {
        String n = "oyr";
        String p = "xiha123";
        // Assert.assertEquals(true, adminservie.login(n, p));
        System.out.println(adminservie.login(n, p));
    }
}
