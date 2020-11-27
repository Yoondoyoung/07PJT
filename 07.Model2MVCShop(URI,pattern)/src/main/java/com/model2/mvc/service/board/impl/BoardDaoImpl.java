package com.model2.mvc.service.board.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.board.BoardDao;
import com.model2.mvc.service.domain.Board;
@Repository("boardDaoImpl")
public class BoardDaoImpl implements BoardDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public BoardDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("Board Dao Impl");
	}

	@Override
	public void addBoard(Board board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("BoardMapper.addBoard",board);
	}

}
