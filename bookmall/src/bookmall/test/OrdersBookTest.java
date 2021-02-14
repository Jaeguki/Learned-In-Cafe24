package bookmall.test;

import java.util.List;

import bookmall.dao.OrdersBookDao;
import bookmall.vo.OrdersBookVo;

public class OrdersBookTest {
	public static void main(String[] args) {

		getList();
	}
	
	public static void insert(Integer count, Long bookNo, Long ordersNo) {
		OrdersBookVo vo = new OrdersBookVo();
		vo.setCount(count);
		vo.setBookNo(bookNo);
		vo.setOrdersNo(ordersNo);
		new OrdersBookDao().insert(vo);
	}
	
	public static void getList() {
		OrdersBookDao dao = new OrdersBookDao();
		List<OrdersBookVo> list = dao.getList();
		for(OrdersBookVo vo : list) {
			System.out.println(vo);
		}
	}

}
