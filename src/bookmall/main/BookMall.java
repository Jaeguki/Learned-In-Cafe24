package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrdersBookDao;
import bookmall.dao.OrdersDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class BookMall {

	public static void main(String[] args) {
		getMemberList();
		getCategoryList();
		getBookList();
		getCartList();
		getOrdersList();
		getOrdersBookList();
	}
	
	public static void getMemberList() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t start get member list");
		System.out.println();
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		for(MemberVo vo : list) {
			System.out.println("\t\t" + vo);
		}
		System.out.println();
		System.out.println("\t\t\t\t\t\t end get member list");
		System.out.println();
		System.out.println();
	}
	
	public static void getCategoryList() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t start get category list");
		System.out.println();
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		for(CategoryVo vo : list) {
			System.out.println("\t\t\t\t\t" + vo);
		}
		System.out.println();
		System.out.println("\t\t\t\t\t\t end get category list");
		System.out.println();
		System.out.println();
	}
	
	public static void getBookList() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t start get book list");
		System.out.println();
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		for(BookVo vo : list) {
			System.out.println("\t\t\t\t" + vo);
		}
		System.out.println();
		System.out.println("\t\t\t\t\t\t end get book list");
		System.out.println();
		System.out.println();
	}
	
	public static void getCartList() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t start get cart list");
		System.out.println();
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList();
		for(CartVo vo : list) {
			System.out.println("\t\t\t\t" + vo);
		}
		System.out.println();
		System.out.println("\t\t\t\t\t\t end get cart list");
		System.out.println();
		System.out.println();
	}
	
	public static void getOrdersList() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t start get orders list");
		System.out.println();
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getList();
		for(OrdersVo vo : list) {
			System.out.println(vo);
		}
		System.out.println();
		System.out.println("\t\t\t\t\t\t end get orders list");
		System.out.println();
		System.out.println();
	}
	
	public static void getOrdersBookList() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t start get orders_book list");
		System.out.println();
		OrdersBookDao dao = new OrdersBookDao();
		List<OrdersBookVo> list = dao.getList();
		for(OrdersBookVo vo : list) {
			System.out.println("\t\t\t\t" + vo);
		}
		System.out.println();
		System.out.println("\t\t\t\t\t\t end get orders_book list");
		System.out.println();
		System.out.println();
		
	}
}
