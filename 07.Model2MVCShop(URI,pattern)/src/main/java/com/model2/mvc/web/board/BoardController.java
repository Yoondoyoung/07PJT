package com.model2.mvc.web.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.board.BoardService;
import com.model2.mvc.service.comment.CommentService;
import com.model2.mvc.service.domain.Board;
import com.model2.mvc.service.domain.Comment;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService boardService;
	
	public BoardController() {
		System.out.println(this.getClass());
		System.out.println("Board Controller");
	}
	
	@RequestMapping(value="addBoard", method=RequestMethod.GET)
	public String addBoard( HttpSession session ) throws Exception {

		System.out.println("Add Board Action Start");
		User user = (User)session.getAttribute("user");
		System.out.println("Add Board Action End");
		return "redirect:/board/addBoard.jsp";
	}
	
	@RequestMapping(value="addBoard", method=RequestMethod.POST)
	public String addBoard(@ModelAttribute("board") Board board,@RequestParam("file")MultipartFile file, HttpSession session ) throws Exception {

		System.out.println("Add Board Action Start");
		User user = (User)session.getAttribute("user");
		boardService.addBoard(board);
		System.out.println("Add Board Action End");
		return "redirect:/board/listBoard?role="+user.getRole();
	}
	
	
	
}