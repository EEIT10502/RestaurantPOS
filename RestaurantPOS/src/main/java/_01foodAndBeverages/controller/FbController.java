package _01foodAndBeverages.controller;



import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import _00model.MenuBean;
import _01foodAndBeverages.service.FbService;

@Controller
public class FbController {
	
	@Autowired
	FbService service;
	@Autowired
	ServletContext context;
	
	
	@RequestMapping("/order")
	public String order(Model model) {
		List<MenuBean>  list = service.getProductByCategory("飯類");
		model.addAttribute("menu", list);
		
		return "/outfield/order";
	}
	
	@RequestMapping("/noodle")
	public String noodle(Model model) {
		List<MenuBean>  list = service.getProductByCategory("麵類");
		model.addAttribute("menu", list);
		
		return "/outfield/order";
	}
	
	@RequestMapping("/soup")
	public String soup(Model model) {
		List<MenuBean>  list = service.getProductByCategory("湯類");
		model.addAttribute("menu", list);
		
		return "/outfield/order";
	}
	
	
}
