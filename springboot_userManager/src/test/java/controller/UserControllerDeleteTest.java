package controller;

import com.test.wk.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class UserControllerDeleteTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNull(){
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @MockBean
    private UserService userService;
    @Test
    public void deleteUserTrue(){
        try {
            Mockito.when(userService.deleteUser(Mockito.any())).thenReturn(true);
            mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete")
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
    public void deleteUserBadmethod(){
        try {
            Mockito.when(userService.deleteUser(Mockito.any())).thenReturn(false);
            mockMvc.perform(MockMvcRequestBuilders.post("/user/delete")
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

    @Test
    public void deleteUserFalse(){
        try {
            Mockito.when(userService.deleteUser(Mockito.any())).thenReturn(false);
            mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete")
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

    @Test
    public void deleteUserBadContentType(){
        try {
            Mockito.when(userService.deleteUser(Mockito.any())).thenReturn(false);
            mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete")
                    //.contentType("application/json")
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
