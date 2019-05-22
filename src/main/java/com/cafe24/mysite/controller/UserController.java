package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(
		@ModelAttribute @Valid UserVo userVo,
		BindingResult result,
		Model model) {
		
		if( result.hasErrors() ) {
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel());	
			return "user/join";
		}
		
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
		@RequestParam(value="email", required=true, defaultValue="") String email,
		@RequestParam(value="password", required=true, defaultValue="") String password,
		HttpSession session,
		Model model) {
		
		UserVo authUser = userService.getUser( new UserVo(email, password) );
		if(authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		
		// session 처리
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping( value="/update", method=RequestMethod.GET )
	public String update(HttpSession session, Model model ){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		UserVo userVo = userService.getUser( authUser.getNo() );
		model.addAttribute( "userVo", userVo );
		return "user/update";
	}
	
	@RequestMapping( value="/update", method=RequestMethod.POST )
	public String update( HttpSession session, @ModelAttribute UserVo userVo ){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		userVo.setNo( authUser.getNo() );
		userService.updateUser( userVo );
		
		// session의 authUser 변경
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update?result=success";
	}
	
//	@ExceptionHandler( Exception.class )
//	public String handleUserDaoException() {
//		System.out.println("!!!!!!!!!!!");
//		return "error/exception";
//	}
	
}
