package bjfu.lixian.Service;

import org.springframework.stereotype.Service;

/**
 * @author lixian
 * @create 2019-11-30
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello"+name;
    }
}
