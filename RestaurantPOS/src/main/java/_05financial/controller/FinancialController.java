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

	// 頁面跳轉
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

	@RequestMapping("/productReport")
	public String productReport() {
		return "/report/productReport";
	}

	// 查詢日期
	@RequestMapping("/dailyReportGetDate")
	public String dailyReportGetDate(Model model, @RequestParam("dDate1") String dDate1,
			@RequestParam("dDate2") String dDate2) {

		List<OrderBean> list = service.getOrderByDate(dDate1, dDate2);
		model.addAttribute("dailyList", list);
		System.out.println(list);

		return "/report/dailyReport";
	}

}
