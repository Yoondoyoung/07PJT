package com.model2.mvc.web.product;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.comment.CommentService;
import com.model2.mvc.service.domain.Comment;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.sun.media.jfxmedia.logging.Logger;


//==> 회원관리 Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	@Resource(name="uploadPath")
	private String savePath;
	
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	//@RequestMapping("/addProductView.do")
	@RequestMapping(value="addProduct",method=RequestMethod.GET)
	public String addProduct() throws Exception {

		System.out.println("add Product GET");
		
		return "redirect:/Product/addProduct.jsp";
	}
	
	//@RequestMapping("/addProduct.do")
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	public String addProduct( @ModelAttribute("product") Product product,//Multipart ModelAttribute때문에 못받는중 수정바람
								@RequestParam("file")List<MultipartFile> file) throws Exception {

		System.out.println("add Product POST");
		//Business Logic
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		System.out.println(product);
		List<MultipartFile> list = file;
		String savedName = "";
		for(MultipartFile files : list) {
			savedName += "/"+uploadFile(files.getOriginalFilename(), files.getBytes());
		}
		
		product.setFileName(savedName);
		productService.addProduct(product);
		
		
		return "redirect:/product/addProduct.jsp";
	}
	
	//@RequestMapping("/getProduct.do")
	@RequestMapping(value="getProduct",method=RequestMethod.GET)
	public String getProduct( @RequestParam("prodNo") int prodNo ,HttpServletRequest request ,HttpSession session, Model model ) throws Exception {
		
		System.out.println("/getProduct.do");
		Map<String,Object> map = commentService.getCommentList(prodNo);
		//Business Logic
		Product product = productService.getProduct(prodNo);
		User user = (User) session.getAttribute("user");
		// Model 과 View 연결
		String[] files = product.getFileName().split("/");
		model.addAttribute("files",files);
		model.addAttribute("product", product);
		model.addAttribute("comment", map.get("comment"));
		model.addAttribute("user", user);
		System.out.println(map);
		return "forward:/product/getProduct.jsp";
	}
	
	//@RequestMapping("/updateProductView.do")
	@RequestMapping(value="updateProduct",method=RequestMethod.GET)
	public String updateProduct( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{

		System.out.println("/updateProductView.do");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/updateProduct.jsp";
	}
	
	//@RequestMapping("/updateProduct.do")
	@RequestMapping(value="updateProduct",method=RequestMethod.POST)
	public String updateProduct( @ModelAttribute("product") Product Product , Model model , HttpSession session) throws Exception{

		System.out.println("/updateProduct.do");
		//Business Logic
		productService.updateProduct(Product);
		
		
		return "redirect:/product/getProduct?ProdNo="+Product.getProdNo();
	}
	
	
	//@RequestMapping("/listProduct.do")
	@RequestMapping(value="listProduct",method=RequestMethod.GET)
	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listProduct.do");
		String menu = request.getParameter("menu");
		if( request.getParameter("page") == null){
			search.setCurrentPage(1);
		}else if (request.getParameter("page") != null) {
			search.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		}
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		search.setPageSize(pageSize);
		System.out.println(search);
		// Business logic 수행
		Map<String , Object> map=null;
		if(menu.equals("manage")) {
			map = purchaseService.getSaleList(search);
		}else {
			map = productService.getProductList(search);
		}
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu",menu);
		
		return "forward:/product/listProduct.jsp";
	}
	
	private String uploadFile(String originalName, byte[] fileData) throws Exception {

		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(savePath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}