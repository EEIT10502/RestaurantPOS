package _04schedule.controller;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _00.init.util.SystemUtils2018;
import _00model.AttendenceBean;
import _00model.EmployeeBean;
import _00model.ScheduleBean;
import _04schedule.service.ScheduleService;

@Controller
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	@RequestMapping("/schedule")
	public String listSchedule(Model model) {
		List<ScheduleBean> list = scheduleService.findAllSchedule();
		model.addAttribute("schedule", list);
		return "/scheduleManage/schedule";
	}
	
	@RequestMapping("/calendar")
	public String scheduling(Model model) {
		return "scheduleManage/calendar";
	}

	// 送回新增Schedule資料的空白表單
	@RequestMapping(value = "/schedule/add", method = RequestMethod.GET)
	public String inputSchedule(Model model) {
		model.addAttribute("scheduleBean", new ScheduleBean());
		return "scheduleManage/addSchedule";
	}

	// URL為 /schedule/add, 搭配 POST方法可以新增一筆紀錄
	// 儲存瀏覽器送來的ScheduleBean資料
	@RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
	public String saveSchedule(ScheduleBean schedule) {
		scheduleService.saveSchedule(schedule);
		return "redirect:/schedule";
	}

//	@ModelAttribute("scheduleList")
//	public Map<Integer, String> getScheduleList() {
//		Map<Integer, String> scheduleMap = new HashMap<>();
//		List<ScheduleBean> list = scheduleService.findAllSchedule();
//		for (ScheduleBean sb : list) {
//			scheduleMap.put(sb.getscheduleId(), sb.getSchedule());
//		}
//		return scheduleMap;
//	}

	// 顯示單筆Schedule資料，然後導向更新畫面
	@RequestMapping(value = "/schedule/update", method = RequestMethod.GET)
	public String showSchedule(@RequestParam("id") Integer scheduleId, Model model) {
		ScheduleBean schedule = scheduleService.findByPrimaryKey(scheduleId);
		model.addAttribute(schedule);
		System.out.println("--顯示單筆Schedule資料，然後導向更新畫面--");
		return "scheduleManage/updateSchedule";
	}

	// 修改單筆Schedule資料
	@RequestMapping(value = "/schedule/update", method = RequestMethod.POST)
	public String updateSchedule(@ModelAttribute("scheduleBean") ScheduleBean scheduleBean,
			HttpServletRequest request) {
		System.out.println("--準備修改單筆Schedule資料--");
		scheduleService.updateSchedule(scheduleBean);
		System.out.println("--返回原始畫面--");
		return "redirect:/schedule";
	}
	
	// 刪除單筆Schedule資料			//做不到此方法
		@RequestMapping(value = "/schedule/update", method = RequestMethod.DELETE)
		public String deleteSchedule(@ModelAttribute("scheduleBean") Integer scheduleId, Model model, 
				HttpServletRequest req) {
			System.out.println("進入delete方法, scheduleId="+scheduleId);
			System.out.println("--準備刪除單筆Schedule資料--");
			scheduleService.deleteScheduleByPrimaryKey(scheduleId);
			System.out.println("--刪除單筆Schedule資料--");
			return "redirect:/schedule";
		}



	@RequestMapping(value = "/schedule/time.check", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> GoToCheck(Model model, @RequestParam(value = "empNO") String empNO,
			@RequestParam(value = "choice") String choice, AttendenceBean attendenceBean) {// 需要在這裡創建AttendenceBean
																							// 不然attendenceBean會null

		System.out.println("進入GoToCheck");
		System.out.println("empNO:" + empNO);
		System.out.println("choice:" + choice);

		// 建構一個errorMsgMap的Map 將錯誤訊息放入
		Map<String, String> Msg = new HashMap<String, String>();
//			model.addAttribute("ErrorMsgKey", errorMsgMap);

		EmployeeBean emp = scheduleService.checkByEmpNo(empNO);

		if (emp != null) {

			System.out.println("取得用戶資訊");
			String SDate = SystemUtils2018.getDate(); // 取得現在的日期格式(yyyu-MM-dd)
			String STime = SystemUtils2018.getTime(); // 取得現在的時間格式(hh:mm:ss)
			Date ddate = SystemUtils2018.strToDate(SDate); // 將其轉換為相對應的型態
			Time dtime = SystemUtils2018.strToTime(STime);

			attendenceBean.setCheckStatus(choice); // 注入
			attendenceBean.setEmpNo(empNO);
			attendenceBean.setClockTime(dtime);
			attendenceBean.setDate((java.sql.Date) ddate);
			attendenceBean.setEmpName(emp.getEmpName());

			scheduleService.addAttendence(attendenceBean); // 新增
			Msg.put("msg", "打卡成功");
		} else {
			System.out.println("無法取得");
			Msg.put("msg", "打卡失敗");
		}

		// 需要在前端將該MAP物件取出
		return Msg;

	}

}
