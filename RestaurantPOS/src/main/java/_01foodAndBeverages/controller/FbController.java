package _01foodAndBeverages.controller;



import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _00model.MenuBean;
import _01foodAndBeverages.service.FbService;


@Controller
public class FbController {
	
	@Autowired
	FbService service;
	@Autowired
	ServletContext context;
	
//	@RequestMapping("/outfield/order")
//	public String toOrder() {
//		return "/outfield/order";
//	}
	
	
	//在index頁面使用這個連結進入點餐畫面
	@RequestMapping("/outfield/order")
	public String order(Model model) {
		List<MenuBean>  list1 = service.getProductByCategory("飯類");
		List<MenuBean>  list2 = service.getProductByCategory("麵類");
		List<MenuBean>  list3 = service.getProductByCategory("湯類");
		List<MenuBean>  list4 = service.getProductByCategory("餃類");
		
	
		model.addAttribute("menu", list1);
		model.addAttribute("noodle", list2);
		model.addAttribute("soup", list3);
		model.addAttribute("dump", list4);
		
		return "/outfield/order";
	}
	
	@RequestMapping("/order/getPrice")
	public @ResponseBody Object getPrice(@RequestParam String product) throws Exception {
		Integer productPrice = service.getProductPriceByName(product);
		return productPrice;
	}
	
	
	
}
