package oyw.gp.oyr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import oyw.gp.oyr.controller.AdminController;
import oyw.gp.oyr.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AdminService adminservie;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new AdminController()).build();
    }

    @Test
    public void testAdminController() throws Exception {
        RequestBuilder requestBuilder;
        JSONObject response;
        String path = new String("/");
        // 1、get查一下admin列表，应该为空
        requestBuilder = get(path);
        mvc.perform(requestBuilder).andExpect(status().isOk());
        response = JSON.parseObject(content().toString());
        Assert.assertEquals(response.get("status"), "success");
        Assert.assertEquals(response.get("code"), 200);
        Assert.assertEquals(response.get("data"), "[]");

        // 2、post提交一个admin
        requestBuilder = post(path).contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\":\"123456\",\"authority\":1}");
        mvc.perform(requestBuilder).andExpect(status().isOk());
        response = JSON.parseObject(content().toString());
        Assert.assertEquals(response.get("status"), "success");
        Assert.assertEquals(response.get("code"), 200);
        Assert.assertEquals(response.get("data"), "[]");

        // 3、get获取admin列表，应该有刚才插入的数据
        requestBuilder = get(path);
        mvc.perform(requestBuilder).andExpect(status().isOk());
        response = JSON.parseObject(content().toString());
        Assert.assertEquals(response.get("status"), "success");
        Assert.assertEquals(response.get("code"), 200);
        // JSONObject admin = JSON.parseObject(response.get("data").toString());
        // System.out.println("------------------------");
        // System.out.println("------------------------");
        // System.out.println(response.get("data").toString());
        // System.out.println("------------------------");
        // System.out.println("------------------------");
        Assert.assertEquals(response.get("data"), "[]");
    }

}
