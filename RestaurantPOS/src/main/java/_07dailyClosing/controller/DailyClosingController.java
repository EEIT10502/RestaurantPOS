package _07dailyClosing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _00model.CumulativeTurnoverBean;
import _07dailyClosing.service.DailyClosingService;

@Controller
public class DailyClosingController {
	@Autowired
	DailyClosingService service;

	@RequestMapping(value = "/close/dailyClosing.action", method = RequestMethod.GET)
	public String getDailyClosingPage(Model model) {
		System.out.println(11);
		CumulativeTurnoverBean cumulativeTurnoverBean = new CumulativeTurnoverBean();
		model.addAttribute("CumulativeTurnoverBean", cumulativeTurnoverBean);

		Date today = new Date();
		SimpleDateFormat todayFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayString = todayFormat.format(today);
		System.out.println("todayString:"+todayString);
		model.addAttribute("todayString", todayString);

		long todayJavaUtil = today.getTime(); 
		java.sql.Date todayJavaSql = new java.sql.Date(todayJavaUtil);
		cumulativeTurnoverBean.setDate(todayJavaSql);
		System.out.println(cumulativeTurnoverBean.getDate());
		
		Integer totalSalesAmountToday = service.getTodayTurnover(todayString);
		model.addAttribute("totalSalesAmountToday", totalSalesAmountToday);
		cumulativeTurnoverBean.setTurnover(totalSalesAmountToday);
		System.out.println("todayTurnover Controller:"+totalSalesAmountToday);
		
		return "close/dailyClosing";// 按JSP目錄層
	}
	
	@RequestMapping(value = "/close/checkDiffAmount.action", method = RequestMethod.POST)
	@ResponseBody
	public String checkDiffAmount(@RequestParam(value = "moneyReceivedInsert", required = false) String moneyReceivedInsert, @RequestParam(value = "totalSalesAmountToday", required = false) String totalSalesAmountToday, Model model) {
		System.out.println(12);
		System.out.println("moneyReceivedInsert:"+moneyReceivedInsert);
		System.out.println("totalSalesAmountToday:"+totalSalesAmountToday);
		
		int totalSalesAmountTodayCount = Integer.parseInt(totalSalesAmountToday.trim());
		int moneyReceivedInsertCount = Integer.parseInt(moneyReceivedInsert.trim());
		
		String Dif = String.valueOf((totalSalesAmountTodayCount-moneyReceivedInsertCount));
		System.out.println("Dif:"+Dif);
		
		return Dif;// 按JSP目錄層
	}
}
