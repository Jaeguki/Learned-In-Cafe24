package com.cafe24.jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.PostDAO;
import com.cafe24.jblog.vo.PostVO;

@Service
public class PostService {
	@Autowired
	PostDAO postDAO;
	
	public List<PostVO> getPostListByID(String id) {
		return postDAO.getPostListByID(id);
	}
	
	public List<PostVO> getPostListByCategoryNo(Long categoryNo) {
		return postDAO.getPostListByCategoryNo(categoryNo);
	}

	public PostVO getLatelyPost(String id) {
		return postDAO.getLatelyPost(id);
	}

	public Boolean removeAllPostByCategory(Long no) {
		return postDAO.removeAllPostByCategory(no);
	}

	public Boolean addPostByByVo(PostVO postVO) {
		return postDAO.addPostByVO(postVO);
	}

	public PostVO getPostByVO(PostVO postVO) {
		return postDAO.getPostByVO(postVO);
	}

	

}
