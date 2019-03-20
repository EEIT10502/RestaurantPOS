package _01foodAndBeverages.controller;




import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import _00model.MenuBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _01foodAndBeverages.service.FbService;
import _01foodAndBeverages.util.SeqUtils;
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
		System.out.println("進入回首頁");
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
	public @ResponseBody Object getProductList(@RequestParam String product) throws Exception {
		MenuBean getProduct = service.getProductListByName(product);
		return getProduct;
	}
	
	@RequestMapping("/order/payment")
	public ModelAndView payment(@ModelAttribute("orderForm") OrderForm orderForm) throws Exception {
		ModelAndView mv = new ModelAndView("/outfield/payment");
		//System.out.println(orderForm);
		List<OrderVo> orderVos = orderForm.getOrderVos();
		List<OrderVo> newOrderVos = new ArrayList<OrderVo>();  //解決修改數量未點選修改的問題

		Integer newTotalAmount = 0;

		for (OrderVo v : orderVos) {
			System.out.println("price:" + v.getPrice());
			System.out.println("qty:" + v.getQty());
		
			if(v.getQty() != null | v.getPrice() !=null) {
				
			int subTotal = v.getQty() * Integer.parseInt(v.getPrice());
			v.setSubTotal(subTotal);
			newOrderVos.add(v);
			newTotalAmount += subTotal;
			}
		}
				
		mv.addObject("orderVos",newOrderVos);
		mv.addObject("callNo",orderForm.getCallNo());
		mv.addObject("cusFlow",orderForm.getCusFlow());
		mv.addObject("totalAmount",newTotalAmount);
		
		mv.setViewName("/outfield/payment");
	
		
//		if (orderVos != null && orderVos.size() > 0) {
//			for (OrderVo v : orderVos) {
//				System.out.println("itemName:" + v.getItemName());
//				System.out.println("price:" + v.getPrice());
//				System.out.println("qty:" + v.getQty());
//				System.out.println("subTotal:" + v.getSubTotal());
//				System.out.println("category:" + v.getCategory());
//				System.out.println("producetno:" + v.getProductNo());
//				//System.out.println("totalAmount:" + v.getTotalAmount());
//			}
//		}
		return mv;
	}
	
	@RequestMapping("/order/confirmPayment")
	public ModelAndView confirmPayment(@ModelAttribute("orderForm") OrderForm orderForm) throws Exception {
		System.out.println("進入結帳控制器");
		ModelAndView mv = new ModelAndView("redirect:/manage/getLastOne");
//		ModelAndView mv = new ModelAndView("/outfield/order");
		System.out.println(orderForm);
		List<OrderVo> orderVos1 = orderForm.getOrderVos1();
		
		String callNo = orderForm.getCallNo();
		System.out.println("callNo:"+callNo);
		Integer cusFlow = orderForm.getCusFlow();
		System.out.println("cusFlow:"+cusFlow);
		Integer totalPrice = orderForm.getTotalAmount();
		System.out.println("totalPrice:"+totalPrice);
		Timestamp orderTime = new Timestamp(System.currentTimeMillis()); 
		String orderNo = SeqUtils.getSeqNo();
		OrderBean ob = new OrderBean(null,orderNo,cusFlow,orderTime,totalPrice,callNo);
		
		Set<OrderDetailBean> items = new HashSet<OrderDetailBean>();
		
		for(OrderVo v:orderVos1) {
			
			OrderDetailBean oib = new OrderDetailBean();
			oib.setProductName(v.getItemName());
			oib.setProductPrice(v.getSubTotal());
			oib.setQty(v.getQty());
			oib.setCategory(v.getCategory());
			oib.setProductNo(v.getProductNo());
			oib.setOrderBean(ob);
			
			
			items.add(oib);
		}
		
		ob.setOrderDetailBean(items);
		
		service.insertOrder(ob);
		
		
		

		
//		
//		if (orderVos1 != null && orderVos1.size() > 0) {
//			for (OrderVo v : orderVos1) {
//				System.out.println("itemName:" + v.getItemName());
//				System.out.println("price:" + v.getPrice());
//				System.out.println("qty:" + v.getQty());
//				System.out.println("subTotal:" + v.getSubTotal());
//										}
//								}
//		System.out.println("totalAmount:" + orderForm.getTotalAmount());
//		System.out.println("cusFlow:" + orderForm.getCusFlow());
//		System.out.println("callNo:" + orderForm.getCallNo());
		return mv;
	}

	
	
	@RequestMapping("/outfield/cancelOrder")
	public String cancel(Model model) {
		System.out.println("進入回首頁");
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
	
}
	
