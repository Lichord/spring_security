package bjfu.lixian.controller;

import bjfu.lixian.dao.User;
import bjfu.lixian.dao.UserQueryCondition;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixian
 * @create 2019-11-27
 */
@RestController
public class UserController {
//    传入基本类型
    @GetMapping(value="/user")
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam String username){
        List<User> list=new ArrayList<>();
        list.add(new User());list.add(new User());list.add(new User());
        return list;
    }
//传入封装对象类型
    @GetMapping(value="/user1")
    public List<User> query1(UserQueryCondition condition){
//      作为参数传递过来,封装成user,不需要使用@RequestBody解析
        System.out.println(condition.getUsername());
//      利用反射工具将传入信息转成String类型
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> list=new ArrayList<>();
        list.add(new User());list.add(new User());list.add(new User());
        return list;
    }

    @GetMapping(value="/user2")
//    如果Http请求没有传入分页信息，@PageableDefault将使用默认的分页配置(page=1,size=5,sort="username,asc")
    public List<User> query2(@PageableDefault(page=1,size=5,sort="username,asc") Pageable pageable){
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        List<User> list=new ArrayList<>();
        list.add(new User());list.add(new User());list.add(new User());
        return list;
    }
//通过正则表达式限制接收只能是数字
    @GetMapping(value="user/{id:\\d+}")
    public User getInfo(@PathVariable String id){
        User user=new User();
        user.setUsername("lixian");
        return user;
    }

    @PostMapping("/create")
    public User createUser(@Valid @RequestBody User user,BindingResult errors){
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
//  如果不使用@RequestBody注解,将无法获取请求体user
//  作为参数传递给java方法时，不需要@RequestBody解析，但是作为json字符串的形式传递参数时，必须用@RequestBody解析
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        user.setUsername("li");
        return user;
    }
    @PutMapping("/update/{id:\\d+}")
    public User update(@Valid @RequestBody User user,BindingResult errors){
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError=(FieldError)error;
//                打印错误具体在哪个字段以及是哪种错误
                String message=fieldError.getField()+error.getDefaultMessage();
                System.out.println(message);
            });
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        user.setUsername("li");
        return user;
    }

    @DeleteMapping("/delete/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }
}
