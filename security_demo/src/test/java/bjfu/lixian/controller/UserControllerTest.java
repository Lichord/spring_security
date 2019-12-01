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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lixian
 * @create 2019-11-18
 * 写代码时测试先行
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result=mockMvc.perform(get("/user").param("username","lixian").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
        .andReturn().getResponse().getContentAsString();//把服务器返回的json变量转化为字符串
//        jsonPath用于解析返回的json里面的内容，此处表示期望返回数据是集合,长度为3
//        jsonPath写法文档：https://github.com/json-path/JsonPath
        System.out.println(result);
    }

    @Test
    public void whenQuerySuccessCondition() throws Exception {
        mockMvc.perform(get("/user1").
                param("username","lixian").
                param("age","18").
                param("ageTo","20").
                contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void whenQuerySuccessPage() throws Exception {
//        请求时携带分页信息
        mockMvc.perform(get("/user2").
                param("size","10").
                param("page","3").
                param("sort","age,desc").//查询第3页的数据，每页返回10条，按照年龄降序排序
                contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }
//查询具体user信息
    @Test
    public void whenQueryInfoSuccess() throws Exception {
        String result=mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("lixian"))
        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void whenQueryInfoFailed() throws Exception {
        mockMvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
//        创建新用户
//        日期类型参数的处理前后端之间最好是使用时间戳的方式传递，后端将时间戳传给前端，以什么形式展示是前端的事情，这样就省去了各种格式之间的复杂转换
        Date date =new Date();
        String content="{\"username\":\"lixian\",\"password\":\"123\",\"birthday\":"+date.getTime()+"}";
        String result=mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))//使用post方式需要传输要创建的内容
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("li"))
        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
//      更新用户信息
//      当前时间加一年，默认时区，转换成毫秒
        Date date =new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String content="{\"username\":\"lixian\",\"password\":\"123\",\"birthday\":"+date.getTime()+"}";
        String result=mockMvc.perform(put("/update/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))//使用post方式需要传输要创建的内容
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("li"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(delete("/delete/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
