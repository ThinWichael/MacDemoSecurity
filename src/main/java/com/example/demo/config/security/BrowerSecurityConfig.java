package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Autowired
	AuthenticationFailureHandler myAuthenctiationFailureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin() // 定義當需要使用者登入時候，轉到的登入頁面。
		        .loginPage("/authentication/require")       // login landing page  // "/mylogin.html"
		        .loginProcessingUrl("/user/login")  // self-defined login entry url
		        .successHandler(myAuthenticationSuccessHandler) // 自定義 Authentication成功處理 
				.failureHandler(myAuthenctiationFailureHandler) // 自定義 Authentication失敗處理
		        .and()
		    .logout()
		        .logoutUrl("/logout")
		        .and()
			.authorizeRequests() // 定義哪些URL需要被保護、哪些不需要被保護
				.antMatchers("/mylogin.html","/authentication/require").permitAll()  // all people can go to login page
				.antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.anyRequest() // 任何請求,登入後可以訪問
				.authenticated()
				.and()
			.csrf().disable()
				; // turn off csrf protection
	}

}
