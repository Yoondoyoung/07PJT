package com.model2.mvc.service.domain;

import java.sql.Date;

public class Comment {
	//Field
	private User user;
	private Product prod;
	private int comNo;
	private String comment;
	private Date regDate;
	
	

	public Comment() {
		// TODO Auto-generated constructor stub
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Product getProd() {
		return prod;
	}



	public void setProd(Product prod) {
		this.prod = prod;
	}



	public int getComNo() {
		return comNo;
	}



	public void setComNo(int comNo) {
		this.comNo = comNo;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public Date getRegDate() {
		return regDate;
	}



	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [user=");
		builder.append(user);
		builder.append(", prod=");
		builder.append(prod);
		builder.append(", comNo=");
		builder.append(comNo);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", reg_date=");
		builder.append(regDate);
		builder.append("]");
		return builder.toString();
	}

}
