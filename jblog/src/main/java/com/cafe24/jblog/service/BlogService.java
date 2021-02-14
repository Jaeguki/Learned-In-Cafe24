package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDAO;
import com.cafe24.jblog.vo.BlogVO;

@Service
public class BlogService {
	@Autowired
	BlogDAO blogDAO;

	public BlogVO getBlogInfo(String id) {
		return blogDAO.getBlogInfo(id);
	}

	public Boolean setBlogInfo(BlogVO blogVO) {
		return blogDAO.setBlogInfo(blogVO);
	}
}
