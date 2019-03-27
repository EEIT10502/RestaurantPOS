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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _00.init.util.SystemUtils2018;
import _00model.AttendenceBean;
import _00model.CalendarBean;
import _00model.EmployeeBean;
import _00model.ScheduleBean;
import _04schedule.service.ScheduleService;

@Controller
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;


	// 耀德 - 打卡
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

	/*--------------------------------------------------------------------------------------------------------------------------*/
	// 日期查詢報表
	@RequestMapping(value = "/empManage/attendance", method = RequestMethod.POST)
	public String dailyReport(Model model, @RequestParam(value = "aDate1") String aDate1,
			@RequestParam(value = "aDate2") String aDate2) {

		model.addAttribute("aDate1", aDate1);
		model.addAttribute("aDate2", aDate2);
		try {
			// 欄位資料:日期,單數,人數,金額
			List<AttendenceBean> listDailyAtt = scheduleService.getAttByDate(aDate1, aDate2);
			model.addAttribute("listDailyAtt", listDailyAtt);
		} catch (NullPointerException e) {
			return "empManage/attendance";
		}
		return "empManage/attendance";
	}

//		// URL為 /attendenceBeans, 搭配 GET方法可以傳回所有紀錄。
//		// 加入produces屬性可以說明產生之資料的格式: produces = "application/vnd.ms-excel"
//		// 查詢所有Member紀錄，本方法可以產生 Excel格式的回應
//		@RequestMapping(value = "/attendenceBeans", method = RequestMethod.GET, 
//				produces = "application/vnd.ms-excel")
//		public String queryAllMembersExcel(Model model) {
//			List<ScheduleBean> attendenceBeans = scheduleService.findBySchedule(schedule)();
//			model.addAttribute("allAttendance", attendenceBeans);
//			return "empManage/attendance";
//		}

	/*--------------------------------------------------------------------------------------------------------------------------*/

	@RequestMapping("/schedule")
	public String listSchedule(Model model) {
		List<ScheduleBean> list = scheduleService.findAllSchedule();
		model.addAttribute("schedule", list);
		return "scheduleManage/schedule";
	}

	// 送回新增Schedule資料的空白表單
	@RequestMapping(value = "/schedule/add", method = RequestMethod.GET)
	public String inputSchedule(Model model) {
		model.addAttribute("scheduleBean", new ScheduleBean());
		System.out.println("--準備新增Schedule資料--");
		return "scheduleManage/addSchedule";
	}

	// URL為 /schedule/add, 搭配 POST方法可以新增一筆紀錄
	// 儲存瀏覽器送來的ScheduleBean資料
	@RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
	public String saveSchedule(ScheduleBean schedule) {
		scheduleService.saveSchedule(schedule);
		System.out.println("--新增Schedule資料成功--");
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
	@RequestMapping(value = "/schedule/{key}", method = RequestMethod.GET)
	public String showSchedule(@PathVariable Integer key, Model model) {
		ScheduleBean schedule = scheduleService.findByPrimaryKey(key);
		model.addAttribute("scheduleBean",schedule);
		System.out.println("--顯示單筆Schedule資料，然後導向更新畫面--");
		return "scheduleManage/updateSchedule";
	}

	// 刪除單筆Schedule資料 
	@RequestMapping(value = "/schedule/{key}", method = RequestMethod.DELETE)
	public String deleteSchedule(@ModelAttribute("scheduleBean")ScheduleBean scheduleBean,@PathVariable Integer key,
			HttpServletRequest request) {
		System.out.println("--準備刪除單筆Schedule資料--");
		scheduleService.deleteScheduleByPrimaryKey(key);
		System.out.println("--刪除單筆Schedule資料--");
		return "redirect:/schedule";
	}

	// 修改單筆Schedule資料
	@RequestMapping(value = "/schedule/{key}", method = RequestMethod.POST)
	public String updateSchedule(@ModelAttribute("scheduleBean")ScheduleBean scheduleBean,@PathVariable Integer key,
			HttpServletRequest request) {
		System.out.println("--準備修改單筆Schedule資料--");	
		scheduleService.updateSchedule(scheduleBean);
		System.out.println("--修改單筆Schedule資料成功--");
		return "redirect:/schedule";
	}

	@RequestMapping("/test")
	public String test(Model model) {
		CalendarBean c = scheduleService.testGet(1);
		System.out.println(c.getEmployee().getEmpName());
		System.out.println(c.getSchedule().getSchedule());
		return "/scheduleManage/calendar";
	}

	// 查詢所有員工
	@RequestMapping("/calendar")
	public String list(Model model) {
		List<CalendarBean> list = scheduleService.getAllEmpName();
		model.addAttribute("calendar", list);

		for (CalendarBean c : list) {
			System.out.println("name" + c.getEmployee().getEmpName());
			System.out.println("status" + c.getEmployee().getStatus());
		}

		return "/scheduleManage/calendar";
	}

	// 顯示單筆Calendar資料，然後導向更新畫面
	@RequestMapping(value = "/calendar/update", method = RequestMethod.GET)
	public String showCalendar(@RequestParam("id") Integer calendarId, Model model) {
		CalendarBean calendarBean = scheduleService.findByPrimary(calendarId);

		List<ScheduleBean> ss = scheduleService.findAllSchedule();
		for (ScheduleBean listRow : ss) {
			System.out.println("班別:" + listRow.getSchedule());
		}
		model.addAttribute("ss", ss);

		model.addAttribute(calendarBean);
		System.out.println(calendarBean.getDay1());
		System.out.println("--顯示單筆Schedule資料，然後導向更新畫面--");
		return "scheduleManage/updateCalendar";
	}

	// 修改單筆Calendar資料
	@RequestMapping(value = "/calendar/update", method = RequestMethod.POST)
	public String updateSchedule(@ModelAttribute("calendarBean") CalendarBean calendarBean,
			HttpServletRequest request) {
		System.out.println("--準備修改單筆Schedule資料--");
		scheduleService.updateCalendar(calendarBean);
		System.out.println("--返回原始畫面--");
		return "redirect:/calendar";
	}

	//======================以下為轉檔PDF=====================完成//
	 @RequestMapping(value = "/attendences", method = RequestMethod.GET, produces = "application/pdf")
	 public String queryAllAttendencePDF(Model model) {
	  List<AttendenceBean> allAttendence = scheduleService.getAllAttendance();
	  model.addAttribute("allAttendence", allAttendence);
	  System.out.println("--Attendence準備轉成PDF--");
	  return "/cnvr/showpAttendence";
	 }
	 //======================以上為轉檔PDF=====================//
	
}
