package com.example.demo.security.config;

import com.example.demo.security.CustomAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.demo.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthProvider customAuthProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthProvider).eraseCredentials(false);
//        auth.inMemoryAuthentication()
//                .withUser("root")
//                .password("{noop}password")
//                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().anyRequest().authenticated();
        http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
//        http.httpBasic().and().authorizeRequests().antMatchers("/**").authenticated();
//        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated()
//                .and().formLogin().permitAll().and().csrf().disable();
    }
}
