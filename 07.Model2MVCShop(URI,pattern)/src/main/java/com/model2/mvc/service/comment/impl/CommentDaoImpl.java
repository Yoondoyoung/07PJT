package com.model2.mvc.service.comment.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.comment.CommentDao;
import com.model2.mvc.service.domain.Comment;

@Repository("commentDaoImpl")
public class CommentDaoImpl implements CommentDao {
	
	//Field
		@Autowired
		@Qualifier("sqlSessionTemplate")
		SqlSession sqlSession;
		
	public CommentDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("Comment Dao Impl");
	}

	@Override
	public void addComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("CommentMapper.addComment",comment);
	}

	@Override
	public Map<String, Object> getCommentList(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		List<Comment> comment = sqlSession.selectList("CommentMapper.getCommentList",prodNo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("comment", comment);
		return map;
	}

	@Override
	public void updateComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub

	}

}
