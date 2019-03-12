package _05financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _00model.OrderBean;
import _05financial.service.FinancialService;

@Controller
public class FinancialController {
	@Autowired
	FinancialService service;

	
//  
//	@RequestMapping("/report/categoryReport")  <-這句話的/report 可以寫在上面的類別名稱上方 如:@RequestMapping("/report) 效果和現在寫的相同
//	public String categoryReport() {
//		return "/report/categoryReport";   <--return的字串裡面開頭不需要再加上"/" 視圖解析器會幫忙加上去
//	}
	
	
	

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

	// 查詢日期
	@RequestMapping("/report/dailyReportGetDate")
	public String dailyReportGetDate(Model model, @RequestParam("dDate1") String dDate1,
			@RequestParam("dDate2") String dDate2) {

		List<OrderBean[]> list = service.getOrderByDate(dDate1, dDate2);
		model.addAttribute("dailyList", list);
//		System.out.println(list);

		return "report/dailyReport";
	}

}
