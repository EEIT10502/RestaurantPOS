package _05financial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FinancialController {
	//頁面跳轉
	@RequestMapping("/sideBar")
	public String sideBar(Model model) {
		return "sideBar";
	}
	@RequestMapping("/categoryReport")
	public String categoryReport() {
		return "/report/categoryReport";
	}
	@RequestMapping("/dailyReport")
	public String dailyReport() {
		return "/report/dailyReport";
	}
	@RequestMapping("/goalReport")
	public String goalReport() {
		return "/report/goalReport";
	}	
//	@RequestMapping("/report/goalReport")
//	public String goalReport() {
//		return "report/goalReport";
//	}
	@RequestMapping("/productReport")
	public String productReport() {
		return "/report/productReport";
	}
	
	//查詢日期
	@RequestMapping("/dailyReportGetDate")
	public String dailyReportGetDate(Model model, @RequestParam("dDate1") String dDate1, @RequestParam("dDate2") String dDate2) {
		model.addAttribute("dDateBegin", dDate1);
		model.addAttribute("dDateEnd", dDate2);
		return "/report/dailyReport";
	}
	
	
	

}
