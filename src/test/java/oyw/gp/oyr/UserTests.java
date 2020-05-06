package oyw.gp.oyr;

import oyw.gp.oyr.controller.UserController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests
{
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUsetController() throws Exception {
        RequestBuilder requestBuilder;
        String path = new String("/controller/user/");
        // 1、get查一下user列表，应该为空
        requestBuilder = get(path);
        mvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        // 2、post提交一个user
        requestBuilder = post(path).contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"测试大师\",\"age\":20}");
        mvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));

        // 3、get获取user列表，应该有刚才插入的数据
        requestBuilder = get(path);
        mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content()
                .string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

        // 4、put修改id为1的user
        requestBuilder = put(path + "1").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"测试终极大师\",\"age\":30}");
        mvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));

        // 5、get一个id为1的user
        requestBuilder = get(path + "1");
        mvc.perform(requestBuilder).andExpect(content().string(
                equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));

        // 6、del删除id为1的user
        requestBuilder = delete(path + "1");
        mvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));

        // 7、get查一下user列表，应该为空
        requestBuilder = get(path);
        mvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }
}

