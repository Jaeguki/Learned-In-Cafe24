package bookmall.vo;

public class BookVo {
	private Long no;
	private Long categoryNo;
	private String title;
	private Long price;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", categoryNo=" + categoryNo + ", title=" + title + ", price=" + price + "]";
	}
}
