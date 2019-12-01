package bjfu.lixian.validator;

import bjfu.lixian.Service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * @author lixian
 * @create 2019-11-30
 */
//自定义校验实现的类需要继承ConstraintValidator<A,T>接口,第一个泛型是自定义的注解，第二个泛型是能够使用该注解的类型,Object,String等
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,String> {
//    该类不需要用@Component之类的注解声明,继承接口时已经默认做了这个步骤
//    该类可以注入各种类用于帮助实现验证逻辑
    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
//        初始化
        System.out.println("my valiadtor initial");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("lixian");
//        真正的校验逻辑, String s是传入的校验的值，constraintValidatorContext校验的上下文
        System.out.println(s);
//        返回值表示校验结果是否正确
        return false;
    }
}
