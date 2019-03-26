package _06manager.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import _00.init.printerUtils.MainPrinter;
import org.springframework.web.bind.annotation.RequestParam;
import _00.init.printerUtils.MainPrinter;
import _00.init.util.SystemUtils2018;
import _00.init.printerUtils.MainPrinter;
import _00model.CumulativeTurnoverBean;
import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _00model.TargetTurnoverBean;
import _06manager.service.ManagerService;

//這是Manager的控制器
//功能:管理功能的登入、準備需要的資訊後列印

@Controller
public class ManagerController {

	@Autowired
	ManagerService managerservice;

	// 先定義一個OrderBean 用來裝找到的唯一OrderBean物件
	OrderBean orderBean = null;

	//再定義一個Set<OrderDetailBean> 用來裝找到的物件集合
	Set<OrderDetailBean> OrderDetailBeanSet = null;
	
	//按下日結清機後列印清機單的控制器
	@RequestMapping("/manage/printDayCheck")
	public String printDayCheckOut() {
		
		
		//取得當日的yyyy-MM-dd格式字串 
		String date=SystemUtils2018.getDate();  
		
		//準備一個有當日數據分析的MAP
		Map<String, Object> mapData = managerservice.getDayCheckAnalysisDate(date);
		
		//準備當日營業數據，放入MAP
		CumulativeTurnoverBean CTBean = managerservice.getCumulativeTurnoverByDate(date);
		mapData.put("CumulativeTurnover", CTBean);
		
		//準備一個當月份的目TargetTurnoverBean，放入MAP
		TargetTurnoverBean TTBean = managerservice.getMonthTarget();
		mapData.put("TargetTurnover",TTBean);
		


		
		//呼叫列印方法，列印日結清機單
		MainPrinter.printDayCheck(mapData);
		
		return "redirect:/close/dailyClosing.action";
	}
	
	// 可根據傳入的OrderNo列印單據 用於丟單時補單
	@RequestMapping("/manage/queryOne")
	public String prepareMessageForPrinter_queryOne() {

		orderBean = managerservice.getOrderBeanByOrderNo("061138");
		OrderDetailBeanSet = orderBean.getOrderDetailBean();


		// 交給下一個控制器列印
		return "redirect:/printer";
	}

	// 列印出最新的一張出菜單，用於點餐畫面按下結帳後將資料輸入進資料庫後呼叫列印
	//流程:員工於點餐畫面按下"結帳"->資料INSERT進入資料庫，控制器交棒給本控制器撈資料
	@RequestMapping("/manage/getLastOne")
	public String prepareMessageForPrinter_LastOne(Model model) {
		
		orderBean = managerservice.getLastOrderBean();
		OrderDetailBeanSet = orderBean.getOrderDetailBean();
		
		
		// 交給下一個控制器列印
		return "redirect:/printer";
	}
	//將準備好的資料交給列印方法，再redirect:/回到點餐畫面
	// 負責列印的控制器
	@RequestMapping("/printer")
	public String Printer() {
		
		// 呼叫靜態列印方法
		MainPrinter.printBill(orderBean, OrderDetailBeanSet);
		MainPrinter.printForBK(orderBean, OrderDetailBeanSet);
		
		
		return "redirect:/outfield/order";
	}
	
	//進入管理登入頁面
	@RequestMapping("/manage/managelogin")
	public String empLogin(Model model) {
		
		return "manage/login";
	}

	@RequestMapping(value = "/manage/managelogin.check", method = RequestMethod.POST)
	public String CheckLogin(Model model,
			@RequestParam(value = "mAccount") String mAccount, // 取得FORM表單的參數
			@RequestParam(value = "mPwd") String mPwd, // 取得FORM表單的參數
			HttpSession session) {



		// 建構一個errorMsgMap的Map 將錯誤訊息放入
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		model.addAttribute("ErrorMsgKey", errorMsgMap);

		// 如果 mAccount 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (mAccount == null || mAccount.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		// 如果 mPwd 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (mPwd == null || mPwd.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}

		// errorMsgMap不為空，表示裡面有錯誤訊息，跳轉到登入頁面重新輸入
		if (!errorMsgMap.isEmpty()) {
			return "manage/login";
		}

		ManagerBean managerBean = null;

		try {
			// 調用寫好的方法進行帳密檢測，不為空代表有該主管資料
			managerBean = managerservice.checkIDPassword(mAccount, mPwd);

			if (managerBean != null) {
				System.out.println("登入成功");
				session.setAttribute("LoginOK", managerBean);
			} else {
				// 為空表示找不到，肯定輸入錯誤!回到登入頁面
				errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
				return "manage/login";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			errorMsgMap.put("LoginError", e.getMessage());
		}
		
		
		return "redirect:/checkTTBisExist";
				
		
	}
	//認證管理登入的帳號密碼後，如果當天為每月1日且還沒有輸入目標營業額，則跳到輸入營業額的頁面
	@RequestMapping("/checkTTBisExist")
	public String checkTTBisExist() {
		
		TargetTurnoverBean TTB = managerservice.getMonthTarget();
		if(TTB == null) {
			//代表這個月的目標營業額還沒有輸入，送他去輸入			
			return "manage/InsertTB";
		}
		//代表本月已經有目標營業額，直接轉後台首頁		
		return "redirect:/toDashBoard";		
	}
	@RequestMapping(value="/manage/insertTTB.check",method=RequestMethod.POST)
	public String insertTTB(Model model,@RequestParam(value="targetTurnover")Integer targetTurnover,
			TargetTurnoverBean TTB) {

		TTB.setDate(SystemUtils2018.getMonth());
		TTB.setTargetTurnover(targetTurnover);		
		managerservice.saveTTB(TTB);
		
		return "redirect:/toDashBoard";
	}
	
	//前往後台首頁
	@RequestMapping("/toDashBoard")
	public String toDashBoard(Model model) {
		//      給圖表用
		TargetTurnoverBean TTBean = managerservice.getMonthTarget();		
		model.addAttribute("TTBean",TTBean);
		
		
		List<CumulativeTurnoverBean> CTBList = managerservice.getOneMonthCumulativeTurnoverBean();		
		model.addAttribute("ctbList", CTBList);
		
		return "/DashBoard";
	}
	
	//跳轉到日結清機
	@RequestMapping("/close/close")
	public String toClose() {
		
		
		return "close/closeBoardTest";
	}
	

	
	
}
