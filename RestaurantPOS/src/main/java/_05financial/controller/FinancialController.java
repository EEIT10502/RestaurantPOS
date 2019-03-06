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
	public String categoryReport(Model model) {
		return "report/categoryReport";
	}
	@RequestMapping("/report/dailyReport")
	public String dailyReport(Model model) {
		return "report/dailyReport";
	}
	@RequestMapping("/report/goalReport")
	public String goalReport(Model model) {
		return "report/goalReport";
	}
	@RequestMapping("/report/productReport")
	public String productReport(Model model) {
		return "report/productReport";
	}

}
