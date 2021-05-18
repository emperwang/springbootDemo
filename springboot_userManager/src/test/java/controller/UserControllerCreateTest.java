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


public class UserControllerCreateTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNull(){
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @MockBean
    private UserService userService;

    @Test
    public void createUserTrue(){
        try {
            Mockito.when(userService.addUser(Mockito.any())).thenReturn(true);
            mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
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
    public void createUserTwiceTrue(){
        try {
            Mockito.when(userService.addUser(Mockito.any())).thenReturn(true);
            mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
                    .contentType("application/json")
                    .content("{" +
                            "\"userName\":\"zhangsan\"," +
                            "\"password\":\"1234567\"" +
                            "}"));

            Mockito.when(userService.addUser(Mockito.any())).thenReturn(false);
            mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
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
    public void createUserFalse(){
        try {
            Mockito.when(userService.addUser(Mockito.any())).thenReturn(false);
            mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
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
    public void createUserBadMethod(){
        try {
            Mockito.when(userService.addUser(Mockito.any())).thenReturn(false);
            mockMvc.perform(MockMvcRequestBuilders.get("/user/create")
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
    public void createUserContentType(){
        try {
            Mockito.when(userService.addUser(Mockito.any())).thenReturn(false);
            mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
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
