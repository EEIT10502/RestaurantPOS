package _06manager.controller;

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
import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _06manager.service.ManagerService;

//這是Manager的控制器
//功能:管理功能的登入、取得OrderBean、OrderDetailBean
//列印後回首頁
@Controller
public class ManagerController {

	@Autowired
	ManagerService managerservice;

	// 先定義一個OrderBean 用來裝找到的唯一OrderBean物件
	OrderBean orderBean = null;

	//再定義一個Set<OrderDetailBean> 用來裝找到的物件集合
	Set<OrderDetailBean> OrderDetailBeanSet = null;
	
	// 可根據傳入的OrderNo列印單據 用於丟單時補單
	@RequestMapping("/manage/queryOne")
	public String prepareMessageForPrinter_queryOne() {

		orderBean = managerservice.getOrderBeanByOrderNo("061138");
		OrderDetailBeanSet = orderBean.getOrderDetailBean();


		// 交給下一個控制器列印
		return "redirect:/printer";
	}

	// 列印出最新的一張出菜單
	@RequestMapping("/manage/getLastOne")
	public String prepareMessageForPrinter_LastOne(Model model) {

		orderBean = managerservice.getLastOrderBean();
		OrderDetailBeanSet = orderBean.getOrderDetailBean();
		
		
		// 交給下一個控制器列印
		return "redirect:/printer";
	}

	// 負責列印的控制器
	@RequestMapping("/printer")
	public String Printer() {
		
		// 呼叫靜態列印方法
		MainPrinter.printBill(orderBean, OrderDetailBeanSet);
		MainPrinter.printForBK(orderBean, OrderDetailBeanSet);
		
		
		return "redirect:/";
	}

	@RequestMapping("/manage/managelogin")
	public String empLogin(Model model) {
		System.out.println("管理員登入");
		
//		MainActivity pos = new MainActivity();
//		pos.onClick();
		
		return "manage/managelogin";
	}

	@RequestMapping(value = "/manage/managelogin.check", method = RequestMethod.POST)
	public String CheckLogin(Model model, @RequestParam(value = "mAccount") String mAccount, // 取得FORM表單的參數
			@RequestParam(value = "mPwd") String mPwd, // 取得FORM表單的參數
			HttpSession session) {

		System.out.println("mAccount:" + mAccount);
		System.out.println("mPwd:" + mPwd);
		System.out.println("進入檢查");

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
			return "manage/managelogin";
		}

		ManagerBean managerBean = null;

		try {
			// 調用寫好的方法進行帳密檢測，不為空代表有該主管資料，並放入Session備用
			managerBean = managerservice.checkIDPassword(mAccount, mPwd);

			if (managerBean != null) {
				System.out.println("登入成功");
				session.setAttribute("LoginOK", managerBean);
			} else {
				// 為空表示找不到，肯定輸入錯誤!回到登入頁面
				errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
				return "manage/managelogin";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			errorMsgMap.put("LoginError", e.getMessage());
		}
		// 回首頁要這樣寫!
		// return "redirect:/";

		// 預設登入後前往查詢員工頁面
		return "empManage/empQuery";
	}

}
