package bookmall.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {

		insert(1L, "톰캣 최종 분석", 28000L);
		insert(2L, "들판의 소나무", 5000L);
		insert(3L, "당신, 거기 있어줄래요?", 9800L);
		
		getList();
	}
	
	public static void insert(Long categoryNo, String title, Long price) {
		BookVo vo = new BookVo();
		vo.setCategoryNo(categoryNo);
		vo.setTitle(title);
		vo.setPrice(price);
		new BookDao().insert(vo);
	}
	
	public static void getList() {
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
}
