package _05financial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinancialController {
	@RequestMapping("/sideBar")
	public String sideBar(Model model) {
		return "sideBar";
	}
	@RequestMapping("/report/categoryReport")
	public String categoryReport() {
		return "report/categoryReport";
	}
	@RequestMapping("/report/dailyReport")
	public String dailyReport() {
		return "report/dailyReport";
	}
	@RequestMapping("/report/goalReport")
	public String goalReport() {
		return "report/goalReport";
	}
	@RequestMapping("/report/productReport")
	public String productReport() {
		return "report/productReport";
	}

}
