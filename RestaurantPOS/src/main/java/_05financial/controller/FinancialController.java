package _05financial.controller;

import java.util.List;

import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _00model.CumulativeTurnoverBean;
import _00model.MenuBean;
import _00model.OrderBean;
import _00model.TargetTurnoverBean;
import _05financial.repository.Result;
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
	public String productReport(Model model) {
		List<MenuBean> listMenuCate = service.getMenuCate();
		model.addAttribute("listMenuCate", listMenuCate);
		return "report/productReport";
	}

	// 日報表
	@RequestMapping("/report/dailyReportGet")
	public String dailyReportGet(Model model, @RequestParam("dDate1") String dDate1,
			@RequestParam("dDate2") String dDate2) {
		model.addAttribute("dDate1", dDate1);
		model.addAttribute("dDate2", dDate2);
		// OrderBean裡的欄位資料
		List<OrderBean[]> listDailyOrder = service.getOrderByDate(dDate1, dDate2);
		model.addAttribute("listDailyOrder", listDailyOrder);
		// TurnoverBean裡的欄位資料
		List<CumulativeTurnoverBean> listDailyCumu = service.getCumulativeTurnoverByDate(dDate1, dDate2);
		model.addAttribute("listDailyCumu", listDailyCumu);

		return "report/dailyReport";
	}

	// 類別報表
	@RequestMapping("/report/categoryReportGet")
	public String categoryReportGet(Model model, @RequestParam("csDate1") String csDate1,
			@RequestParam("csDate2") String csDate2, @RequestParam("csSelOpt") String csSelOpt) {
		model.addAttribute("csDate1", csDate1);
		model.addAttribute("csDate2", csDate2);
		model.addAttribute("csSelOpt", csSelOpt);
		// OrderBean裡的欄位資料
		List<OrderBean[]> listCatee = service.getCateByDate(csDate1, csDate2, csSelOpt);
		model.addAttribute("listCatee", listCatee);
		// 類別報表下拉選單
		List<MenuBean> listMenuCate = service.getMenuCate();
		model.addAttribute("listMenuCate", listMenuCate);

		return "report/categoryReport";
	}

	// 單品報表單品取得
	@RequestMapping("/report/productReportPro")
	@ResponseBody
	public List<MenuBean> productReportPro(Model model, @RequestParam("pcSelOpt") String Cate) {
		List<MenuBean> listMenuPro = service.getMenuProductByCate(Cate);

		return listMenuPro;
	}

	// 單品報表
	@RequestMapping("/report/productReportGet")
	public String productReportGet(Model model, @RequestParam("pDate1") String pDate1,
			@RequestParam("pDate2") String pDate2, @RequestParam("pcSelOpt") String pcSelOpt,
			@RequestParam("pSelOpt") String pSelOpt) {
		model.addAttribute("pDate1", pDate1);
		model.addAttribute("pDate2", pDate2);
		model.addAttribute("pcSelOpt", pcSelOpt);
		model.addAttribute("pSelOpt", pSelOpt);

		List<OrderBean[]> listProuct = service.getProductByDate(pDate1, pDate2, pSelOpt);
		model.addAttribute("listProuct", listProuct);
		// 單品報表類別下拉選單
		List<MenuBean> listMenuCate = service.getMenuCate();
		model.addAttribute("listMenuCate", listMenuCate);

		return "report/productReport";
	}

	// 營運目標報表
	@RequestMapping("/report/goalReportGet")
	public String goalReportGet(Model model, @RequestParam("gMonth1") String gMonth1) {
		model.addAttribute("gMonth1", gMonth1);
		// CumulativeTurnoverBean的欄位資料
		List<CumulativeTurnoverBean> listgoalCum = service.getCumulativeTurnoverByDate2(gMonth1);
		model.addAttribute("listgoalCum", listgoalCum);
		// TargetTurnoverBean的欄位資料
		List<TargetTurnoverBean> listgoalturn = service.getTargetTurnoverBeanByDate(gMonth1);
		model.addAttribute("listgoalturn", listgoalturn);

		return "report/goalReport";
	}
}
