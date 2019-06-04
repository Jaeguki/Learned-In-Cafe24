package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.UserDAO;
import com.cafe24.jblog.vo.UserVO;


@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public UserVO getUser(UserVO userVO) {
		return userDAO.getUser(userVO);
	}

	public Boolean join(UserVO userVO) {
		return userDAO.join(userVO);
	}

	public Boolean existID(String id) {
		UserVO userVO = userDAO.getUser(id);
		return userVO != null;
	}

}
