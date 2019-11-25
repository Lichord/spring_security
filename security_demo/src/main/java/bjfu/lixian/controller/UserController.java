package bjfu.lixian.controller;

import bjfu.lixian.dao.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lixian
 * @create 2019-11-18
 */
@RestController
public class UserController {
    @RequestMapping(value="/user",method = RequestMethod.GET)
    public List<User> query(){
     return null;
    }
}
