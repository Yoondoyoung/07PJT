package com.model2.mvc.service.domain;

public class Board {
	
	//Field
	private User user;
	private int boardNum;
	private String boardTitle;
	private String board;
	private Comment cmt;
	private int viewCount;

	public Board() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public Comment getCmt() {
		return cmt;
	}

	public void setCmt(Comment cmt) {
		this.cmt = cmt;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	@Override
	public String toString() {
		return "Board [user=" + user + ", boardNum=" + boardNum + ", boardTitle=" + boardTitle + ", board=" + board
				+ ", cmt=" + cmt + ", viewCount=" + viewCount + "]";
	}

}
