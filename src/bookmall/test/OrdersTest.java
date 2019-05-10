package bookmall.test;

import java.util.List;

import bookmall.dao.OrdersBookDao;
import bookmall.dao.OrdersDao;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class OrdersTest {
	public static void main(String[] args) {
		insert("서울특별시 인헌1길 55-11 닷컴오피스텔 402호", 1L);
		getOrdersList();
		getOrdersBookList();
	}
	
	public static void insert(String address, Long memberNo) {
		OrdersVo vo = new OrdersVo();
		vo.setAddress(address);
		vo.setMemberNo(memberNo);
		new OrdersDao().insert(vo);
		
		OrdersBookVo obVo = new OrdersBookVo();
		obVo.setCount(1);
		obVo.setBookNo(1L);
		obVo.setOrdersNo(1L);
		new OrdersBookDao().insert(obVo);
		obVo.setCount(1);
		obVo.setBookNo(2L);
		obVo.setOrdersNo(1L);
		new OrdersBookDao().insert(obVo);
		
		
	}
	
	public static void getOrdersList() {
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getList();
		for(OrdersVo vo : list) {
			System.out.println(vo);
		}
	}
	public static void getOrdersBookList() {
		OrdersBookDao dao = new OrdersBookDao();
		List<OrdersBookVo> list = dao.getList();
		for(OrdersBookVo vo : list) {
			System.out.println(vo);
		}
	}
}
