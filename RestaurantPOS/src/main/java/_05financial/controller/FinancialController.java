package _05financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _00model.CumulativeTurnoverBean;
import _00model.MenuBean;
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
	public String categoryReport(Model model) {
		List<MenuBean> listMenuCate = service.getMenuCate();
		model.addAttribute("listMenuCate", listMenuCate);
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

	// 日報表
	@RequestMapping("/report/dailyReportGet")
	public String dailyReportGet(Model model, @RequestParam("dDate1") String dDate1,
			@RequestParam("dDate2") String dDate2) {
		
		model.addAttribute("dDate1", dDate1);
		model.addAttribute("dDate2", dDate2);

		List<OrderBean[]> listDailyOrder = service.getOrderByDate(dDate1, dDate2);
		model.addAttribute("listDailyOrder", listDailyOrder);

		List<CumulativeTurnoverBean> listDailyCumu = service.getCumulativeTurnoverByDate(dDate1, dDate2);
		model.addAttribute("listDailyCumu", listDailyCumu);
		System.out.println(listDailyCumu);

		return "report/dailyReport";
	}
	
	// 類別報表
	@RequestMapping("/report/categoryReportGet")
	public String categoryReportGet(Model model, @RequestParam("csDate1") String csDate1,
			@RequestParam("csDate2") String csDate2) {
		
		model.addAttribute("csDate1", csDate1);
		model.addAttribute("csDate2", csDate2);
		
//		List<OrderDetailBean[]> listCatee = service.getCateByDate(csDate1, csDate2, csSelOpt);
		List<OrderBean[]> listCatee = service.getCateByDate(csDate1, csDate2);
//		model.addAttribute("listCatee", listCatee);
		
//		System.out.println(csDate1 + " & " + csDate2 + " & " + csSelOpt);
		
		return "report/categoryReport";
	}
	
	// 營運目標報表
}
