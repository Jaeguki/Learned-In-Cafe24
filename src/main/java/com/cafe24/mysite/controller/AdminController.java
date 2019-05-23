package com.cafe24.mysite.controller;

import com.cafe24.security.Auth;

@Auth(Role="ADMIN")
public class AdminController {
	
	public String site() {
		return "";
	}

	public String user() {
		return "";
	}

}
