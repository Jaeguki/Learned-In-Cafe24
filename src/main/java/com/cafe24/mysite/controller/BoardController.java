package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Auth
	@RequestMapping("/write")
	public String write() {
		return "board/write";
	}
}
