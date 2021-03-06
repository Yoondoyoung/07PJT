package com.model2.mvc.service.comment;

import java.util.Map;

import com.model2.mvc.service.domain.Comment;

public interface CommentService {
	
	public void addComment(Comment comment) throws Exception;
	
	public Comment getComment(int prodNo) throws Exception;
	
	public Map<String,Object> getCommentList(int prodNo) throws Exception;
	
	public void updateComment(Comment comment) throws Exception;
	
}
