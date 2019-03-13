package _04schedule.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	// 送回新增Schedule資料的空白表單
	@RequestMapping(value = "/schedule/add", method = RequestMethod.GET)
	public String input(Model model) {
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

	// 顯示單筆Member資料，然後導向更新畫面
	@RequestMapping(value = "/schedule/update", method = RequestMethod.GET)
	public String updateSchedule(@RequestParam("id") Integer scheduleId, Model model) {
		ScheduleBean schedule = scheduleService.findByPrimaryKey(scheduleId);
		model.addAttribute(schedule);
		return "scheduleManage/updateSchedule";
	}
	// 修改單筆Member資料
	@RequestMapping(value = "/schedule/update", method = RequestMethod.PUT)
	public String processUpdateScheduleForm(@RequestParam("scheduleBean") ScheduleBean sb, 
			HttpServletRequest request) {
		scheduleService.updateSchedule(sb);
		System.out.println("sb= "+sb);
		return "redirect:/schedule";
	}
	
	@RequestMapping("/schedule/arrange")
	public String scheduling(Model model) {
		System.out.println("HAHAHAHA");
		return "scheduleManage/arrangeSchedule";
	}

}