package _02employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {

	@RequestMapping("/outfield/order")
	public String order(Model model) {
		return "/outfield/orderorder";
	}

}
