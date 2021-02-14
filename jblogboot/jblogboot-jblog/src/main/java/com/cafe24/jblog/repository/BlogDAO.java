package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVO;

@Repository
public class BlogDAO {
	@Autowired
	private SqlSession sqlSession;
	public BlogVO getBlogInfo(String id) {
		return sqlSession.selectOne( "blog.getBlogByID", id );
	}
	public Boolean setBlogInfo(BlogVO blogVO) {
		return 1 == sqlSession.insert( "blog.setBlogByVO", blogVO);
	}
		
}
