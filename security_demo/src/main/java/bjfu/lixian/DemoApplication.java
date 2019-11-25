package bjfu.lixian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixian
 * @create 2019-11-12
 */
//启动类注解
@SpringBootApplication
//表示此Controller提供rest服务
@RestController
public class DemoApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
//    @RequestMapping及其变体 映射http请求url到java方法
//    @RequestParam 映射请求参数到java方法的参数
//    @PageableDefault:指定分页参数默认值
    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

}