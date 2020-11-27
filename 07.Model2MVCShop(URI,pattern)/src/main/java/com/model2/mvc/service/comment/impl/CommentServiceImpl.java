package com.model2.mvc.service.comment.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.comment.CommentDao;
import com.model2.mvc.service.comment.CommentService;
import com.model2.mvc.service.domain.Comment;

@Service("commentServiceImpl")
public class CommentServiceImpl implements CommentService {
	//Field
	@Autowired
	@Qualifier("commentDaoImpl")
	private CommentDao commentDao;

	public CommentServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("Comment Service impl");
	}
	

	@Override
	public void addComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Add Comment Impl Start");
		commentDao.addComment(comment);
	}

	@Override
	public Comment getComment(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getCommentList(int prodNo) throws Exception {
		System.out.println("Get Comment List Impl");
		Map<String,Object> map = commentDao.getCommentList(prodNo);
		return map;
	}

	@Override
	public void updateComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Update Comment Impl");
		commentDao.updateComment(comment);
	}

}
