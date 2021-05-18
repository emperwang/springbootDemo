package controller;


import com.test.wk.entity.Role;
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

public class UserControllerRoleTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNull(){
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @MockBean
    private UserService userService;

    @Test
    public void roleCreateTrue(){
        try {
            Mockito.when(userService.addRole((Role) Mockito.any())).thenReturn(true);
            mockMvc.perform(MockMvcRequestBuilders.post("/role/create")
                    .contentType("application/json")
                    .content("{" +
                            "\"roleName\":\"zhangsan\"" +
                            "}"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void roleCreateFalse(){
        try {
            Mockito.when(userService.addRole((Role) Mockito.any())).thenReturn(false);
            mockMvc.perform(MockMvcRequestBuilders.post("/role/create")
                    .contentType("application/json")
                    .content("{" +
                            "\"roleName\":\"zhangsan\"" +
                            "}"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void roleCreateBadHeader(){
        try {
            Mockito.when(userService.addRole((Role) Mockito.any())).thenReturn(true);
            mockMvc.perform(MockMvcRequestBuilders.post("/role/create")
                    //.contentType("application/json")
                    .content("{" +
                            "\"roleName\":\"zhangsan\"" +
                            "}"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void roleCreateBadMethod(){
        try {
            Mockito.when(userService.addRole((Role) Mockito.any())).thenReturn(true);
            mockMvc.perform(MockMvcRequestBuilders.put("/role/create")
                    .contentType("application/json")
                    .content("{" +
                            "\"roleName\":\"zhangsan\"" +
                            "}"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
