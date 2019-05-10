package bookmall.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {
	public static void main(String[] args) {
		insert("컴퓨터/IT");
		insert("수필");
		insert("소설");
		getList();
	}
	
	public static void insert(String category) {
		CategoryVo vo = new CategoryVo();
		vo.setCategory(category);
		new CategoryDao().insert(vo);
	}
	
	public static void getList() {
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}
}
