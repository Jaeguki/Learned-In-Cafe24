package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVO;

@Repository
public class CategoryDAO {
	@Autowired
	private SqlSession sqlSession;
	public List<CategoryVO> getCategoryListByID(String id) {
		return sqlSession.selectList( "category.getCategoryListByID", id );
	}
	public Boolean addCategoryByVO(CategoryVO categoryVO) {
		return 1 == sqlSession.insert( "category.addCategoryByVO", categoryVO);
	}
	public Boolean removeCategoryByVO(CategoryVO categoryVO) {
		return 1 == sqlSession.delete( "category.removeCategoryByVO", categoryVO);
	}
	public Long getCategoryNoByName(String categoryName) {
		return sqlSession.selectOne( "category.getCategoryNoByName", categoryName);
	}

}
