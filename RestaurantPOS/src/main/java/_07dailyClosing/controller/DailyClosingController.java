package _07dailyClosing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _00model.CumulativeTurnoverBean;
import _00model.MenuBean;
import _07dailyClosing.repository.DailyClosingDao;
import _07dailyClosing.service.DailyClosingService;

@Controller
public class DailyClosingController {
	@Autowired
	DailyClosingService service;

	@RequestMapping(value = "/close/dailyClosing.action", method = RequestMethod.GET)
	public String getDailyClosingPage(Model model) {	
		Date today = new Date();
		long todayJavaUtil = today.getTime(); 
		java.sql.Date todayJavaSql = new java.sql.Date(todayJavaUtil);
		Integer resultOfcompleteCheckToday = service.completeCheckToday(todayJavaSql);

		SimpleDateFormat todayFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayString = todayFormat.format(today);
		model.addAttribute("todayString", todayString);
		
		if(resultOfcompleteCheckToday == null) {
			CumulativeTurnoverBean cumulativeTurnoverBean = new CumulativeTurnoverBean();
			model.addAttribute("CumulativeTurnoverBean", cumulativeTurnoverBean);

			cumulativeTurnoverBean.setDate(todayJavaSql);

			Integer totalSalesAmountToday = service.getTodayTurnover(todayString);
			if(totalSalesAmountToday != null) {
				model.addAttribute("totalSalesAmountToday", totalSalesAmountToday);
				cumulativeTurnoverBean.setTurnover(totalSalesAmountToday);
			}else {
				model.addAttribute("totalSalesAmountToday", 0);
				int zero = 0;
				cumulativeTurnoverBean.setTurnover(zero);
			}

		}else {
			CumulativeTurnoverBean cumulativeTurnoverBean = service.getAllById(resultOfcompleteCheckToday);
			Integer ShortoverAmount = cumulativeTurnoverBean.getShortoverAmount();
			model.addAttribute("CumulativeTurnoverBean", cumulativeTurnoverBean);
			model.addAttribute("totalSalesAmountToday", cumulativeTurnoverBean.getTurnover());
			model.addAttribute("shortoverAmountToday", ShortoverAmount);
			model.addAttribute("closingCompletedToday", "本日日結已完成");
			if( ShortoverAmount!= null) {
				if(ShortoverAmount.equals(0)) {
					model.addAttribute("shortoverAmountTodayString", "當日實收金額 與 當日應收金額 '無'差異");
				}else if(ShortoverAmount > 0) {
					model.addAttribute("shortoverAmountTodayString", "本日有溢收金額");
				}else if(ShortoverAmount < 0) {
					model.addAttribute("shortoverAmountTodayString", "本日有短收金額");
				}else {
					model.addAttribute("shortoverAmountTodayString", "");
				}
			}else {
				System.out.println("ShortoverAmount是null ?!");
			}
		}	
		return "close/dailyClosing";
	}

	@RequestMapping(value = "/close/checkDiffAmount.action", method = RequestMethod.POST)
	@ResponseBody
	public String checkDiffAmount(
			@RequestParam(value = "moneyReceivedInsert", required = false) String moneyReceivedInsert,
			@RequestParam(value = "totalSalesAmountToday", required = false) String totalSalesAmountToday,
			Model model) {

		int totalSalesAmountTodayCount = Integer.parseInt(totalSalesAmountToday.trim());
		int moneyReceivedInsertCount = Integer.parseInt(moneyReceivedInsert.trim());
		String Diff = String.valueOf((totalSalesAmountTodayCount - moneyReceivedInsertCount));

		return Diff;
	}

	@RequestMapping(value = "/close/dailyClosing.action", method = RequestMethod.POST)
	public String dailyClosingInsert(
			@ModelAttribute("CumulativeTurnoverBean") CumulativeTurnoverBean cumulativeTurnoverBean, Model model) {

		java.sql.Date today = cumulativeTurnoverBean.getDate();
		Integer cumulativeTurnoverPrevious = service.getCTPrevious();	
		Integer cumulativeTurnoverToday = cumulativeTurnoverBean.getTurnover() + cumulativeTurnoverPrevious;
		cumulativeTurnoverBean.setCumulativeTurnover(cumulativeTurnoverToday);
		service.addDailyClosing(cumulativeTurnoverBean);

		return "redirect:/manage/printDayCheck";
	}
}
