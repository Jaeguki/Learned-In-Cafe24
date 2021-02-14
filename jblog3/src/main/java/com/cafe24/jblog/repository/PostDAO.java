package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cafe24.jblog.vo.PostVO;

@Repository
public class PostDAO {
	@Autowired
	private SqlSession sqlSession;
	public List<PostVO> getPostListByID(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList( "post.getPostListByID", id );
	}
	
	public List<PostVO> getPostListByCategoryNo(Long categoryNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList( "post.getPostListByCategoryNo", categoryNo);
	}
	
	public PostVO getLatelyPost(String id) {
		return sqlSession.selectOne( "post.getLatelyPostByID", id);
	}
	public Boolean removeAllPostByCategory(Long no) {
		return 1 == sqlSession.delete( "post.removeAllPostByCategory", no);
	}
	public Boolean addPostByVO(PostVO postVO) {
		return 1 == sqlSession.insert( "post.addPostByVO", postVO);
	}

	public PostVO getPostByVO(PostVO postVO) {
		return sqlSession.selectOne( "post.getPostByVO", postVO);
	}
}
