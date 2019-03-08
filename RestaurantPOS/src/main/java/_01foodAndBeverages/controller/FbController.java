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
		List<MenuBean>  list = service.getAllProducts();
	
		model.addAttribute("menu", list);
		
		return "/outfield/order";
	}
	
	
	
	
	
	
}
