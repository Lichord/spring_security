package bjfu.lixian.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lixian
 * @create 2019-11-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(fileUpload("/file")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(
                get("/user").param("username", "jojo").param("age", "18").param("ageTo", "60").param("xxx", "yyy")
                        // .param("size", "15")
                        // .param("page", "3")
                        // .param("sort", "age,desc")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
//        jsonPath里面表达式写法可以参照github里面jsonpath的一个repository
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}
