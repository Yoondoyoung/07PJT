package com.model2.mvc.service.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.board.BoardDao;
import com.model2.mvc.service.board.BoardService;
import com.model2.mvc.service.domain.Board;

@Service("boardServiceImpl")
public class BoardServiceImpl implements BoardService {
	
	//Field
	@Autowired
	@Qualifier("boardDaoImpl")
	private BoardDao boardDao;

	public BoardServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addBoard(Board board) throws Exception {
		// TODO Auto-generated method stub
		boardDao.addBoard(board);
	}

}
