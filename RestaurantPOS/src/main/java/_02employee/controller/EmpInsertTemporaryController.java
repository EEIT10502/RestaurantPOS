package _02employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import _00model.AttendenceBean;
import _00model.CumulativeTurnoverBean;
import _00model.OrderBean;
import _02employee.service.EmployeeService;
import _04schedule.service.ScheduleService;

//此檔案係為了開發方便(在首頁直接出現連結，連到員工管理相關頁面)，故之後確定員工管理入口後再修改

//提供員工管理的@Controller
@Controller
public class EmpInsertTemporaryController {

	@Autowired
	EmployeeService employeeService;

	// 建議使用SpringMVC課本 P261的新增書籍資料流程來完成本功能
	// 參考凱頤的商品管理也不錯
	// 重點:使用<from:from>表單
	@RequestMapping("/empManage/empInsert.action")
	public String empInsert(Model model) {
		System.out.println("進入empInsert");// 測試是否連結到此控制器

		return "empManage/empInsert";
	}

	@RequestMapping("/empManage/empQuery.action")
	public String empQuery(Model model) {
		System.out.println("進入empQuery");// 測試是否連結到此控制器

		return "empManage/empQuery";
	}

	// 給泰豪的出勤查詢控制器
	@RequestMapping("/empManage/attendance")
	public String listAttendancePage(Model model) {
		List<AttendenceBean> list = employeeService.getAllAttendence();
		model.addAttribute("Attendence", list);
		System.out.println("list=" + list);
		return "empManage/attendance";
	}

//	@RequestMapping("/empManage/attendance")
//	public String dailyReportGet(Model model, @RequestParam("dDate1") String dDate1,
//			@RequestParam("dDate2") String dDate2) {
//		model.addAttribute("dDate1", dDate1);
//		model.addAttribute("dDate2", dDate2);
//		// 欄位資料:日期,單數,人數,金額
//		List<AttendenceBean[]> listDailyOrder = ScheduleService.getAttByDate(dDate1, dDate2);
//		model.addAttribute("listDailyOrder", listDailyOrder);
//
//		return "empManage/attendance";
//	}
	
	// 以日期查詢員工
//	@RequestMapping("/empManage/attendance")
//	public String listByDate(Model model, @RequestParam("dDate1") String dDate1,
//			@RequestParam("dDate2") String dDate2) {
//
//		model.addAttribute("dDate1", dDate1);
//		model.addAttribute("dDate2", dDate2);

//		List<AttendenceBean[]> listDailyOrder = employeeService.getAttendenceListByDate(Date1, Date2);
//		model.addAttribute("listDailyOrder", listDailyOrder);

//		List<AttendenceBean> listDailyCumu = employeeService.getAttendenceListByDate(Date1, Date2);
//		model.addAttribute("listDailyCumu", listDailyCumu);
//		System.out.println(listDailyCumu);

//		return "empManage/attendance";
//	}
	
	// URL為 /members, 搭配 GET方法可以傳回所有紀錄。
	// 加入produces屬性可以說明產生之資料的格式: produces = "application/vnd.ms-excel"
	// 查詢所有Member紀錄，本方法可以產生 Excel格式的回應
	@RequestMapping(value = "/empManage/attendance", method = RequestMethod.GET, 
			produces = "application/vnd.ms-excel")
	public String queryAllAttendenceExcel(Model model) {
		List<AttendenceBean> allAttendence = employeeService.findAllAttendence();
		model.addAttribute("allAttendence", allAttendence);
		return "empManage/attendance";
	}

}
