package bookmall.vo;

public class OrdersBookVo {
	Long no;
	Integer count;
	Long bookNo;
	Long ordersNo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(Long ordersNo) {
		this.ordersNo = ordersNo;
	}
	@Override
	public String toString() {
		return "OrdersBookVo [no=" + no + ", count=" + count + ", bookNo=" + bookNo + ", ordersNo=" + ordersNo + "]";
	}
	
}
