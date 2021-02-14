package bookmall.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {
	public static void main(String[] args) {
		insert(1L, 1L, 1);
		insert(2L, 1L, 1);
		getList();
	}
	
	public static void insert(Long bookNo, Long memberNo, Integer count) {
		CartVo vo = new CartVo();
		vo.setBookNo(bookNo);
		vo.setMemberNo(memberNo);
		vo.setCount(count);
		new CartDao().insert(vo);
	}
	
	public static void getList() {
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList();
		for(CartVo vo : list) {
			System.out.println(vo);
		}
	}
}
