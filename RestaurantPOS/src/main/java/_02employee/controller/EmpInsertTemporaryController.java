package _02employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _00model.AttendenceBean;
import _00model.CumulativeTurnoverBean;
import _00model.OrderBean;
import _02employee.service.EmployeeService;

//此檔案係為了開發方便(在首頁直接出現連結，連到員工管理相關頁面)，故之後確定員工管理入口後再修改


//提供員工管理的@Controller
@Controller
public class EmpInsertTemporaryController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	//建議使用SpringMVC課本 P261的新增書籍資料流程來完成本功能 
	//參考凱頤的商品管理也不錯
	//重點:使用<from:from>表單
	@RequestMapping("/empManage/empInsert.action") 
	public String empInsert(Model model) {
		System.out.println("進入empInsert");//測試是否連結到此控制器

		return "empManage/empInsert";
	}
	
	@RequestMapping("/empManage/empQuery.action")
	public String empQuery(Model model) {
		System.out.println("進入empQuery");//測試是否連結到此控制器
		
		return "empManage/empQuery";
	}
	
	
	//給泰豪的出勤查詢控制器
	@RequestMapping("/empManage/attendance")
	public String listAttendancePage(Model model) {
		List<AttendenceBean> list = employeeService.getAllAttendence();
		model.addAttribute("Attendence", list);
		System.out.println("list="+list);
		return "empManage/attendance";
	}
	//以日期查詢員工
	@RequestMapping("/empManage/attendance")
	public String listByDate(Model model, @RequestParam("dDate1") String dDate1,
			@RequestParam("dDate2") String dDate2) {

		model.addAttribute("dDate1", dDate1);
		model.addAttribute("dDate2", dDate2);

//		List<AttendenceBean[]> listDailyOrder = employeeService.getAttendenceListByDate(Date1, Date2);
//		model.addAttribute("listDailyOrder", listDailyOrder);

//		List<AttendenceBean> listDailyCumu = employeeService.getAttendenceListByDate(Date1, Date2);
//		model.addAttribute("listDailyCumu", listDailyCumu);
//		System.out.println(listDailyCumu);

		return "empManage/attendance";
	}

}
