package _04schedule.controller;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _00.init.util.SystemUtils2018;
import _00model.AttendenceBean;
import _00model.EmployeeBean;
import _04schedule.service.ScheduleService;

@Controller
public class ScheduleController {

	@Autowired
	ScheduleService service;
	
	
	
	@RequestMapping(value="/schedule/time.check",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> GoToCheck(Model model,
			@RequestParam(value="empNO")String empNO,
			@RequestParam(value="choice")String choice,
			AttendenceBean attendenceBean) {//需要在這裡創建AttendenceBean 不然attendenceBean會null
		
			System.out.println("進入GoToCheck");
			System.out.println("empNO:"+empNO);
			System.out.println("choice:"+choice);
		
		    //建構一個errorMsgMap的Map 將錯誤訊息放入
			Map<String, String> errorMsgMap = new HashMap<String, String>();
//			model.addAttribute("ErrorMsgKey", errorMsgMap);
			errorMsgMap.put("empNoError", "員工編號錯誤");		
				
			EmployeeBean emp = service.checkByEmpNo(empNO);
		
		if(emp!=null) {
			
			
			System.out.println("取得用戶資訊");
			String SDate = SystemUtils2018.getDate();     //取得現在的日期格式(yyyu-MM-dd)
			String STime = SystemUtils2018.getTime();	  //取得現在的時間格式(hh:mm:ss)
			Date ddate = SystemUtils2018.strToDate(SDate); //將其轉換為相對應的型態
			Time dtime = SystemUtils2018.strToTime(STime);
			
			attendenceBean.setCheckStatus(choice);        //注入
			attendenceBean.setEmpNo(empNO);
			attendenceBean.setClockTime(dtime);
			attendenceBean.setDate((java.sql.Date) ddate);
			
			
			service.addAttendence(attendenceBean);        //新增
		}else {
			System.out.println("無法取得");
		}
		
		
		
		return  errorMsgMap;

	}
	
	
	
}
