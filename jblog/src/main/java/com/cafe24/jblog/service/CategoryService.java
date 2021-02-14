package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.CategoryDAO;
import com.cafe24.jblog.vo.CategoryVO;

@Service
public class CategoryService {
	@Autowired
	CategoryDAO categoryDAO;
	
	public List<CategoryVO> getCategoryListByID(String id) {
		return categoryDAO.getCategoryListByID(id);
	}

	public Boolean addCategoryByVO(CategoryVO categoryVO) {
		return categoryDAO.addCategoryByVO(categoryVO);
	}

	public Boolean removeCategoryByVO(CategoryVO categoryVO) {
		return categoryDAO.removeCategoryByVO(categoryVO);
	}

	public Long getCategoryNoByName(String categoryName) {
		return categoryDAO.getCategoryNoByName(categoryName);
	}

}
