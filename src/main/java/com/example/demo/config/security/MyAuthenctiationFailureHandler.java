package com.example.demo.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.demo.beans.restful.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("myAuthenctiationFailureHandler")
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ObjectMapper objectMapper;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	AuthenticationException exception) throws IOException, ServletException {
	logger.info("Authentication 失敗");
	response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	response.setContentType("application/json;charset=UTF-8");
	response.getWriter().write(objectMapper.writeValueAsString(new BaseResponse(exception.getMessage())));
	
	// go default
	// super.onAuthenticationFailure(request, response, exception);
	}

}
