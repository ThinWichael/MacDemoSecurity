package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.restful.BaseResponse;

@RestController
public class BrowserSecurityController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	// 原請求資訊的快取及恢復
	private RequestCache requestCache = new HttpSessionRequestCache();
	// 用於重定向
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * 當需要身份認證的時候，跳轉過來
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public BaseResponse requireAuthenication(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("引發跳轉的請求是:" + targetUrl);
			
			// you should define your own redirect logic. Maybe a write list of url.
			if (StringUtils.endsWithIgnoreCase(targetUrl, ".html") ||
				StringUtils.endsWithIgnoreCase(targetUrl, "/user")) {
				redirectStrategy.sendRedirect(request, response, "/mylogin.html");
			}
		}
		return new BaseResponse("訪問的服務需要身份認證，請引導使用者到登入頁");
	}

}
