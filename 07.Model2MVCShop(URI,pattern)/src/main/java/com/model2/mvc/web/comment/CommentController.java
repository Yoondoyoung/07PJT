package com.model2.mvc.web.comment;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.comment.CommentService;
import com.model2.mvc.service.comment.impl.CommentServiceImpl;
import com.model2.mvc.service.domain.Comment;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {

	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public CommentController() {
		System.out.println(this.getClass());
		System.out.println("Comment Controller");
	}
	
	@RequestMapping("/addComment")
	public String addComment(	@RequestParam("comment") String comm,
								@RequestParam("prodNo")int prodNo, HttpSession session ) throws Exception {

		System.out.println("Add Comment Action Start");
		Product product = new Product();
		Comment comment = new Comment();
		product.setProdNo(prodNo);
		comment.setProd(product);
		comment.setComment(comm);
		comment.setUser((User)session.getAttribute("user"));
		commentService.addComment(comment);
		System.out.println("Add Comment Action End");
		return "redirect:/product/getProduct?prodNo=" + prodNo;
	}
	
	
	
}