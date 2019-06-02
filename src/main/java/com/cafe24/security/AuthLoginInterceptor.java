package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVO;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		
		UserService userService = ac.getBean(UserService.class);
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO.setPassword(password);
		UserVO authUser = userService.getUser( userVO );
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false; // true시 통과시키고 404에러페이지가 나
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath()+"/");
		return false;
	}

}
