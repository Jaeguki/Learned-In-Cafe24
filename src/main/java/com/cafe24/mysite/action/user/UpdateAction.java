package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub 
		
		UserVo authUser = (UserVo)request.getSession().getAttribute("authUser");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

		UserVo vo = new UserVo();
		vo.setNo(authUser.getNo());
		vo.setName(name);
		vo.setEmail(authUser.getEmail());
		vo.setPassword(password);
		vo.setGender(gender);
		
		System.out.println(vo.toString());
		new UserDao().update(vo);
		
		authUser.setName(name);
		authUser.setPassword(password);
		authUser.setGender(gender);
		WebUtil.redirect(request, response, request.getContextPath());
	}

}
