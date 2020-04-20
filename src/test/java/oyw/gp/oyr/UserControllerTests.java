package oyw.gp.oyr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import oyw.gp.oyr.controller.UserController;
import oyw.gp.oyr.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerTests
{
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserControllerCreate() throws Exception {
        RequestBuilder requestBuilder;

        requestBuilder = post("/user/").contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"username\":admin,\"password\":\"123456\",\"telephone\":15390848355,\"address\":123456}");
        mvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("true")));
    }
}