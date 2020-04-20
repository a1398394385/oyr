package oyw.gp.oyr;

import org.junit.Test;

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
        mvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));
    }
}