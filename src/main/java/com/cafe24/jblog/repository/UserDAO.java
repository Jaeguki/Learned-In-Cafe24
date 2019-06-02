package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public UserVO getUser(UserVO userVO) {
		return sqlSession.selectOne( "user.getUser", userVO );
	}
	
	public UserVO getUser(String id) {
		return sqlSession.selectOne( "user.getUserByID", id);
	}

	public Boolean join(UserVO userVO) {
		int count = sqlSession.insert( "user.joinUser", userVO );
		return 1 == count;
	}

	

}
