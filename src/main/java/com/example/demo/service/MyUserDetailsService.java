package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("使用者的使用者名稱: {}", username);
		// TODO 根據使用者名稱，查詢到對應的密碼，與許可權 
		// (In real practice, we need to query database by username to get authorities and password)
		// 封裝使用者資訊，並返回。引數分別是：使用者名稱，密碼，使用者許可權

		User user = new User(username, passwordEncoder.encode("123456"),
		AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return user;
	}

}
