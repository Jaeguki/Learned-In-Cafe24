package com.cafe24.jblog.vo;

public class CategoryVO {
	private Long no;
	private String name;
	private String description;
	private String regDate;
	private String blogID;
	private Long postCnt;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBlogID() {
		return blogID;
	}
	public void setBlogID(String blogID) {
		this.blogID = blogID;
	}
	public Long getPostCnt() {
		return postCnt;
	}
	public void setPostCnt(Long postCnt) {
		this.postCnt = postCnt;
	}
	@Override
	public String toString() {
		return "CategoryVO [no=" + no + ", name=" + name + ", description=" + description + ", regDate=" + regDate
				+ ", blogID=" + blogID + ", postCnt=" + postCnt + "]";
	}
	
	
}
