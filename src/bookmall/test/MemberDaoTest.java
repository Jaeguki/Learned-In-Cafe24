package bookmall.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		insert("유재국", "010-6262-3125", "dev.jaeguki@gmail.com", "wornr12#");
		insert("유재경", "010-6373-6716", "rew623@naver.com", "worud12#");
		getList();
	}
	
	public static void insert(String name, String tell, String email, String password) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setTell(tell);
		vo.setEmail(email);
		vo.setPassword(password);
		new MemberDao().insert(vo);
	}
	
	public static void getList() {
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}

}
