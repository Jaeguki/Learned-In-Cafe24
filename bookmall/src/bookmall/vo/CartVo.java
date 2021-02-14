package bookmall.vo;

public class CartVo {
	private Long bookNo;
	private Long memberNo;
	private Integer count;
	private Long price;
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long no) {
		this.bookNo = no;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CartVo [bookNo=" + bookNo + ", memberNo=" + memberNo + ", count=" + count + ", price=" + price + "]";
	}
	
}
