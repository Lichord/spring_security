package bjfu.lixian.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lixian
 * @create 2019-11-30
 */
//可以作用于方法和字段
@Target({ElementType.METHOD,ElementType.FIELD})
//运行时注解
@Retention(RetentionPolicy.RUNTIME)
//由MyConstraintValidator完成校验逻辑
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
//    自定义注解至少需要这几个参数
    String message();

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
