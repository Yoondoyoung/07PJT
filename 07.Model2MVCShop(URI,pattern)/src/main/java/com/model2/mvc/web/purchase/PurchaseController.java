package com.model2.mvc.web.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 Controller
@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public PurchaseController(){
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
	
	
	//@RequestMapping("/addPurchaseView.do")
	@RequestMapping(value="addPurchase",method=RequestMethod.GET)
	public String addpurchase(@RequestParam("prodNo") int prodNo, Model model, HttpSession session) throws Exception {

		System.out.println("/addpurchaseView.do");
		Product product = productService.getProduct(prodNo);
		User user = (User)session.getAttribute("user");
		model.addAttribute("product",product);
		model.addAttribute("user",user);
		return "forward:/purchase/AddPurchaseView.jsp";
	}
	
	//@RequestMapping("/addPurchase.do")
	@RequestMapping(value="addPurchase",method=RequestMethod.POST)
	public String addPurchase( @ModelAttribute("purchase") Purchase purchase,HttpServletRequest request,HttpSession session, Model model ) throws Exception {

		System.out.println("/AddPurchase.do");
		//Business Logic
		User user = (User)session.getAttribute("user");
		purchase.setBuyer(user);
		purchase.setPurchaseProd(productService.getProduct(Integer.parseInt(request.getParameter("prodNo"))));
		System.out.println(purchase);
		purchaseService.addPurchase(purchase);
		purchase = purchaseService.getPurchase2(Integer.parseInt(request.getParameter("prodNo")));
		model.addAttribute("purchase",purchase);
		return "forward:/purchase/AddPurchase.jsp";
	}
	
	//@RequestMapping("/getPurchase.do")
	@RequestMapping(value="getPurchase",method=RequestMethod.GET)
	public String getPurchase( @RequestParam("tranNo") int tranNo ,HttpServletRequest request ,HttpSession session, Model model ) throws Exception {
		
		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase(Integer.parseInt(request.getParameter("tranNo")));
		User user = (User) session.getAttribute("user");
		purchase.setBuyer(user);
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/GetPurchase.jsp";
	}
	
	//@RequestMapping("/updatePurchaseView.do")
	@RequestMapping(value="updatePurchase",method=RequestMethod.GET)
	public String updatePurchase( @RequestParam("tranNo") int tranNo , Model model ) throws Exception{

		
		System.out.println("update Purchase View Start");
		Purchase purchase = purchaseService.getPurchase(tranNo);
		System.out.println(purchase);
		model.addAttribute("purchase", purchase);
		return "forward:/purchase/UpdatePurchaseView.jsp";
	}
	
	//@RequestMapping("/updatePurchase.do")
	@RequestMapping(value="updatePurchase",method=RequestMethod.POST)
	public String updatePurchase( @ModelAttribute("purchase") Purchase purchase , Model model , HttpSession session) throws Exception{

		System.out.println("/updatepurchase.do");
		//Business Logic
		purchaseService.updatePurchase(purchase);
		
		
		return "redirect:/purchase/getPurchase?tranNo="+purchase.getTranNo();
	}
	
	
	//@RequestMapping("/listPurchase.do")
	@RequestMapping(value="listPurchase",method=RequestMethod.GET)
	public String listPurchase( @ModelAttribute("search") Search search , Model model , HttpServletRequest request,
									HttpSession session) throws Exception{
		
		System.out.println("/listPurchase.do");
		User user = (User)session.getAttribute("user");
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
		Map<String , Object> map = new HashMap<String,Object>();
		System.out.println("Clear");
		map = purchaseService.getPurchaseList(search,user.getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu",menu);
		
		return "forward:/purchase/GetPurchaseList.jsp";
	}
	
	//@RequestMapping("/listSale.do")
	@RequestMapping(value="listSale",method=RequestMethod.GET)
	public String listSale( @ModelAttribute("search") Search search , Model model , HttpServletRequest request,
									HttpSession session) throws Exception{
		
		System.out.println("/listSale.do");
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
		
		map = purchaseService.getSaleList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu",menu);
		
		return "forward:/purchase/listProduct.jsp";
	}
	//@RequestMapping("updateTranCodeByProd.do")
	@RequestMapping(value="updateTranCodeByProd",method=RequestMethod.GET)
	public String updateTranCodeByProd(@RequestParam("prodNo") int prodNo,
										@RequestParam("tranCode")String tranCo,
										@ModelAttribute("search")Search search,
										Model model , HttpServletRequest request) throws Exception {
		
		search.setCurrentPage(1);
		
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		search.setPageSize(pageSize);
		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase2(prodNo);
		purchase.setTranCode(tranCo);
		purchaseService.updateTranCode(purchase);
		Map<String, Object> map = purchaseService.getSaleList(search);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu","manage");
		return "forward:/product/listProduct.jsp";
	}
	//@RequestMapping("updateTranCode.do")
	@RequestMapping(value="updateTranCode",method=RequestMethod.GET)
	public String updateTranCode(@RequestParam("tranNo") int tranNo,
									@RequestParam("tranCode")String tranCo,
									@ModelAttribute("search")Search search,
									Model model , HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		search.setCurrentPage(1);
		
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		search.setPageSize(pageSize);
		
		
		
		Purchase purchase = new Purchase();
		
		purchase.setTranCode(tranCo);
		purchase.setTranNo(tranNo);
		purchaseService.updateTranCode(purchase);
		Map<String, Object> map = purchaseService.getPurchaseList(search, user.getUserId());
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		return "forward:/purchase/GetPurchaseList.jsp";
	}
}