package _05financial.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _00model.CumulativeTurnoverBean;
import _00model.MenuBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _00model.TargetTurnoverBean;
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
		// 類別報表的類別下拉選單
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
		// 單品報表的類別下拉選單
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
		// 欄位資料:日期,單數,人數,金額
		List<OrderBean[]> listDailyOrder = service.getOrderByDate(dDate1, dDate2);
		model.addAttribute("listDailyOrder", listDailyOrder);
		// TurnoverBean裡的欄位資料
		List<CumulativeTurnoverBean> listDailyCumu = service.getCumulativeTurnoverByDate(dDate1, dDate2);
		model.addAttribute("listDailyCumu", listDailyCumu);

		return "report/dailyReport";
	}

	// 日報表Excel
	@RequestMapping(value = "/report/DailyReportGetExcel", method = RequestMethod.POST, produces = "application/vnd.ms-excel")
	public String DailyReportGetExcel(Model model, @RequestParam("dDate1") String dDate1,
			@RequestParam("dDate2") String dDate2) {
		model.addAttribute("dDate1", dDate1);
		model.addAttribute("dDate2", dDate2);
		// OrderBean裡的欄位資料:日期,單數,人數,金額
		List<OrderBean[]> listDailyOrder = service.getOrderByDate(dDate1, dDate2);
//		model.addAttribute("listDailyOrder", listDailyOrder);
		// TurnoverBean裡的欄位資料
		List<CumulativeTurnoverBean> listDailyCumu = service.getCumulativeTurnoverByDate(dDate1, dDate2);
		model.addAttribute("listDailyCumu", listDailyCumu);
		// 新建MAP並將List<OrderBean[]>放入
		List<Map<String, Object>> listDailyOrderExcel = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();

		int i = 0;
		for (Object[] o : listDailyOrder) {
			// 日期
			map.put("date" + i, o[0]);
			// 單數
			map.put("totalList" + i, o[1]);
			// 人數
			map.put("cusFlow" + i, o[2]);
			// 金額
			map.put("price" + i, o[3]);

			listDailyOrderExcel.add(map);
			i++;
			// 計算回圈次數
			model.addAttribute("countI", i);
		}
		model.addAttribute("listDailyOrderExcel", listDailyOrderExcel);

		return "dailyReport/excel";
	}

	// 類別報表
	@RequestMapping("/report/categoryReportGet")
	public String categoryReportGet(Model model, @RequestParam("csDate1") String csDate1,
			@RequestParam("csDate2") String csDate2, @RequestParam("csSelOpt") String csSelOpt) {
		model.addAttribute("csDate1", csDate1);
		model.addAttribute("csDate2", csDate2);
		model.addAttribute("csSelOpt", csSelOpt);
		// 欄位資料:日期,數量,金額
		List<OrderDetailBean[]> listCatee = service.getCateByDate(csDate1, csDate2, csSelOpt);
		model.addAttribute("listCatee", listCatee);
		// 類別報表下拉選單
		List<MenuBean> listMenuCate = service.getMenuCate();
		model.addAttribute("listMenuCate", listMenuCate);

		return "report/categoryReport";
	}

	// 類別報表Excel
	@RequestMapping(value = "/report/categoryReportGetExcel", method = RequestMethod.POST, produces = "application/vnd.ms-excel")
	public String categoryReportGetExcel(Model model, @RequestParam("csDate1") String csDate1,
			@RequestParam("csDate2") String csDate2, @RequestParam("csSelOpt") String csSelOpt) {
		model.addAttribute("csDate1", csDate1);
		model.addAttribute("csDate2", csDate2);
		model.addAttribute("csSelOpt", csSelOpt);
		// 欄位資料:日期,數量,金額
		List<OrderDetailBean[]> listCatee = service.getCateByDate(csDate1, csDate2, csSelOpt);
//		model.addAttribute("listCatee", listCatee);
		// 類別報表下拉選單
		List<MenuBean> listMenuCate = service.getMenuCate();
		model.addAttribute("listMenuCate", listMenuCate);
		// 新建MAP並將List<OrderDetailBean[]>放入
		List<Map<String, Object>> listCateExcel = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();

		int i = 0;
		for (Object[] o : listCatee) {
			// 日期
			map.put("date" + i, o[0]);
			// 數量
			map.put("qty" + i, o[1]);
			// 金額
			map.put("price" + i, o[2]);
			listCateExcel.add(map);

			i++;
			// 計算回圈次數
			model.addAttribute("countI", i);
		}
		model.addAttribute("listCateExcel", listCateExcel);
		return "categoryReport/excel";
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
		// 欄位資料:日期,數量,金額
		List<OrderBean[]> listProuct = service.getProductByDate(pDate1, pDate2, pSelOpt);
		model.addAttribute("listProuct", listProuct);
		// 單品報表類別下拉選單
		List<MenuBean> listMenuCate = service.getMenuCate();
		model.addAttribute("listMenuCate", listMenuCate);
		// 單品報表單品下拉選單
		List<MenuBean> listMenuProduct = service.getMenuProductByCate(pcSelOpt);
		for(MenuBean Product:listMenuProduct) {
			System.out.println(Product.getProductName());
			if(Product.getProductName().equals(pSelOpt)) {
				listMenuProduct.remove(Product);
				break;
			}
		}
		
		model.addAttribute("listMenuProduct", listMenuProduct);

		return "report/productReport";
	}

	// 單品報表Excel
	@RequestMapping(value = "/report/productReportGetExcel", method = RequestMethod.POST, produces = "application/vnd.ms-excel")
	public String productReportGetExcel(Model model, @RequestParam("pDate1") String pDate1,
			@RequestParam("pDate2") String pDate2, @RequestParam("pcSelOpt") String pcSelOpt,
			@RequestParam("pSelOpt") String pSelOpt) {
		model.addAttribute("pDate1", pDate1);
		model.addAttribute("pDate2", pDate2);
		model.addAttribute("pcSelOpt", pcSelOpt);
		model.addAttribute("pSelOpt", pSelOpt);
		// 欄位資料:日期,數量,金額
		List<OrderBean[]> listProuct = service.getProductByDate(pDate1, pDate2, pSelOpt);
//		model.addAttribute("listProuct", listProuct);
		// 類別報表下拉選單
		List<MenuBean> listMenuCate = service.getMenuCate();
		model.addAttribute("listMenuCate", listMenuCate);
		// 單品報表單品下拉選單
		List<MenuBean> listMenuProduct = service.getMenuProductByCate(pcSelOpt);
		model.addAttribute("listMenuProduct", listMenuProduct);
		// 新建MAP並將List<OrderDetailBean[]>放入
		List<Map<String, Object>> listProductExcel = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();

		int i = 0;
		for (Object[] o : listProuct) {
			// 日期
			map.put("date" + i, o[0]);
			// 數量
			map.put("qty" + i, o[1]);
			// 金額
			map.put("price" + i, o[2]);
			listProductExcel.add(map);

			i++;
			// 計算回圈次數
			model.addAttribute("countI", i);
		}
		model.addAttribute("listProductExcel", listProductExcel);
		return "productReport/excel";
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
	// 營運目標PDF
//	@RequestMapping(value = "/report/goalReportGetPDF", method = RequestMethod.GET)
//	public ModelAndView goalReportGetPDF(Model model, @RequestParam("gMonth1") String gMonth1) {
//		// CumulativeTurnoverBean的欄位資料
//		List<CumulativeTurnoverBean> listgoalCum = service.getCumulativeTurnoverByDate2(gMonth1);
//		model.addAttribute("listgoalCum", listgoalCum);
//		// TargetTurnoverBean的欄位資料
//		List<TargetTurnoverBean> listgoalturn = service.getTargetTurnoverBeanByDate(gMonth1);
//		model.addAttribute("listgoalturn", listgoalturn);
//		//兩個list合併成一個list
//		List<Object> listGoalReportPdf = new ArrayList<Object>();
//		for(int i=0; i<listgoalCum.size(); i++) {
//			listGoalReportPdf.add(listgoalCum.get(i));
//		}
//		for(int i=0; i<listgoalturn.size(); i++) {
//			listGoalReportPdf.add(listgoalturn.get(i));
//		}
//		
//		
////		listGoalReportPdf.add(listgoalCum);
////		listGoalReportPdf.add(listgoalturn);
//		
//		return new ModelAndView("report/goalReport");
//	}

//	@RequestMapping(value = "/report/goalReportGetPDF", method = RequestMethod.POST, produces = "application/pdf")
//	public String goalReportGetPDF(Model model, @RequestParam("gMonth1") String gMonth1) {
//		model.addAttribute("gMonth1", gMonth1);
//		
//		System.out.println(gMonth1);
//		// CumulativeTurnoverBean的欄位資料
//		List<CumulativeTurnoverBean> listgoalCum = service.getCumulativeTurnoverByDate2(gMonth1);
//		model.addAttribute("listgoalCum", listgoalCum);
//		// TargetTurnoverBean的欄位資料
//		List<TargetTurnoverBean> listgoalturn = service.getTargetTurnoverBeanByDate(gMonth1);
//		model.addAttribute("listgoalturn", listgoalturn);
//		return "report/goalReport";
//	}
//	// 類別報表PDF
//	@RequestMapping(value = "/report/categoryReportGetPDF", method = RequestMethod.GET, produces = "application/pdf")
//	public String goalReportGetPDF(Model model, @RequestParam("csDate1") String csDate1,
//			@RequestParam("csDate2") String csDate2, @RequestParam("csSelOpt") String csSelOpt) {
//		model.addAttribute("csDate1", csDate1);
//		model.addAttribute("csDate2", csDate2);
//		model.addAttribute("csSelOpt", csSelOpt);
//		System.out.println(csDate1 + " PDF TEST!!!");
//
//		// OrderBean裡的欄位資料
//		List<OrderDetailBean[]> listCatee = service.getCateByDate(csDate1, csDate2, csSelOpt);
//		model.addAttribute("listCatee", listCatee);
//		// 類別報表下拉選單
//		List<MenuBean> listMenuCate = service.getMenuCate();
//		model.addAttribute("listMenuCate", listMenuCate);
//		return "report/categoryReport";
//	}

}
