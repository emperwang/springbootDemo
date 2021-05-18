package controller;


import com.test.wk.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UserControllerAuthTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void testNull(){
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @MockBean
    private UserService userService;

    @Test
    public void AuthTrue(){
        try {
            Mockito.when(userService.authenticate(Mockito.any())).thenReturn("1234567890");
            mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                    .contentType("application/json")
                    .content("{" +
                            "\"userName\":\"zhangsan\"," +
                            "\"password\":\"1234567\"" +
                            "}"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AuthFalse(){
        try {
            Mockito.when(userService.authenticate(Mockito.any())).thenReturn(null);
            mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                    .contentType("application/json")
                    .content("{" +
                            "\"userName\":\"zhangsan\"," +
                            "\"password\":\"1234567\"" +
                            "}"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
