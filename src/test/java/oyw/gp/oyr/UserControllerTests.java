package oyw.gp.oyr;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import oyw.gp.oyr.controller.UserController;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerTests
{
    @Autowired
    private UserService userService;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username", "test").eq("password", "test")
                .eq("telephone", "test")
                .eq("address", "test");
        userService.remove(queryWrapper);
    }

    @Test
    public void testUserControllerCreate() throws Exception {
        RequestBuilder requestBuilder;

        requestBuilder = post("/user/").contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"username\":test,\"password\":\"test\",\"telephone\":test,\"address\":test}");
        mvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("true")));
    }

    @Test
    public void testUserController() throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", "666");
        Assert.assertEquals(null, userService.getOne(queryWrapper, false));
    }

    @Test
    public void testUserControllerUpdate() throws Exception {
        RequestBuilder requestBuilder;

        requestBuilder = put("/user/10").contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"username\":test,\"password\":\"test\",\"telephone\":test,\"address\":test}");
    }
}