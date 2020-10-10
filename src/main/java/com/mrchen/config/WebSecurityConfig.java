package com.mrchen.config;

import com.mrchen.authentication.MyAuthenticationFailHandler;
import com.mrchen.authentication.MyAuthenticationProvider;
import com.mrchen.authentication.MyAuthenticationSuccessHandler;
import com.mrchen.authentication.filter.MyAuthenticationFilter;
import com.mrchen.authorization.CustomAccessDecisionManager;
import com.mrchen.authorization.CustomSecurityMetadataSource;
import com.mrchen.authorization.MyAccessDeniedHandler;
import com.mrchen.authorization.MySecurityInterceptor;
import com.mrchen.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/8/10 19:02
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private MyAuthenticationProvider provider;
    @Autowired
    private CustomAccessDecisionManager customAccessDecisionManager;
    @Autowired
    private CustomSecurityMetadataSource customSecurityMetadataSource;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.
//                inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("admin").password("admin").roles("ADMIN")
//                .and().withUser("normal").roles("NORMAL").password("normal");
        //添加provider
        auth.userDetailsService(myUserDetailsService);
        auth.authenticationProvider(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .requestMatchers()
//                .antMatchers("/oauth/**")
//                .and()
                .authorizeRequests()
                .antMatchers("/login/all").permitAll()
                .antMatchers("/login/getInfo").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //设置表单登陆
                .csrf()
                .disable()
                .formLogin()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .and()
                .logout()
                .permitAll();
        //添加自定义过滤器
        MyAuthenticationFilter myAuthenticationFilter = new MyAuthenticationFilter();
        myAuthenticationFilter.setAuthenticationManager(authenticationManager());
        myAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        myAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailHandler);
        //设置自己的登陆
        //myAuthenticationFilter.setFilterProcessesUrl("/filterLogin");
        http.addFilterBefore(myAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        //添加url拦截器
        MySecurityInterceptor mySecurityInterceptor = new MySecurityInterceptor();
        mySecurityInterceptor.setAccessDecisionManager(customAccessDecisionManager);
        mySecurityInterceptor.setCustomSecurityMetadataSource(customSecurityMetadataSource);
        http.addFilterAt(mySecurityInterceptor, FilterSecurityInterceptor.class);

        //登录过后访问无权限的接口时自定义响应内容
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }
}
