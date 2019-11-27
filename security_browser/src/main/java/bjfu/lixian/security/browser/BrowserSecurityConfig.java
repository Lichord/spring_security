package bjfu.lixian.security.browser;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author lixian
 * @create 2019-11-26
 */
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
//    有三个不同的configure函数可以覆盖
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        安全=认证+授权
        http.formLogin()//认证方式：表单登录
        .and()//
        .authorizeRequests()//请求授权方式：
        .anyRequest()//对任何请求
        .authenticated();//授权都需要身份认证
    }
}
