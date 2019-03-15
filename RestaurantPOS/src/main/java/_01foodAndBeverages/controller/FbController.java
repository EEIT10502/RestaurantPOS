package _01foodAndBeverages.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import _00model.MenuBean;
import _01foodAndBeverages.service.FbService;
import _01foodAndBeverages.vo.OrderForm;
import _01foodAndBeverages.vo.OrderVo;


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
		List<MenuBean>  list4 = service.getProductByCategory("菜類");
		List<MenuBean>  list5 = service.getProductByCategory("小菜類");
	
		model.addAttribute("menu", list1);
		model.addAttribute("noodle", list2);
		model.addAttribute("soup", list3);
		model.addAttribute("vegetable", list4);
		model.addAttribute("sidedish", list5);
		return "/outfield/order";
	}
	
	@RequestMapping("/order/getPrice")
	public @ResponseBody Object getPrice(@RequestParam String product) throws Exception {
		Integer productPrice = service.getProductPriceByName(product);
		return productPrice;
	}
	
	@RequestMapping("/order/payment")
	public ModelAndView payment(@ModelAttribute("orderForm") OrderForm orderForm) throws Exception {
		ModelAndView mv = new ModelAndView("/outfield/payment");
		System.out.println(orderForm);
		List<OrderVo> orderVos = orderForm.getOrderVos();
		if (orderVos != null && orderVos.size() > 0) {
			for (OrderVo v : orderVos) {
				System.out.println("itemName:" + v.getItemName());
				System.out.println("price:" + v.getPrice());
				System.out.println("qty:" + v.getQty());
				System.out.println("subTotal:" + v.getSubTotal());
				System.out.println("callNo:" + v.getCallNo());
				System.out.println("cusFlow:" + v.getCusFlow());
				System.out.println("totalAmount:" + v.getTotalAmount());
			}
		}
		return mv;
	}
	
}
