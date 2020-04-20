package oyw.gp.oyr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import oyw.gp.oyr.controller.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AuthControllerTests
{
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testAuthController() {
        RequestBuilder requestBuilder;

        requestBuilder = post("/register").contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":admin,\"password\":\"123456\",\"age\":20}");
        // mvc.perform(requestBuilder)
        // .andExpect(content().string(equalTo("success")));
    }
}