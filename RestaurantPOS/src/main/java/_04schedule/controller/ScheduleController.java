package _04schedule.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import _00model.ScheduleBean;
import _04schedule.service.ScheduleService;

@Controller
public class ScheduleController {
	@Autowired
	ScheduleService service;
	
	@RequestMapping("/schedule")
	public String list(Model model) {
		List<ScheduleBean> list = service.findAll();
		model.addAttribute("schedule",list);
		return "/scheduleManage/schedule";
	}
	
	/*-----------------------------------新增班表資料-----------------------------------*/
	
	@RequestMapping(value = "/schedule/add", method = RequestMethod.GET)
	public String getAddNewScheduleForm(Model model) {
		ScheduleBean sb = new ScheduleBean();
		model.addAttribute("scheduleBean", sb);
		return "scheduleManage/addSchedule";
	}
	@RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
	public String processAddNewScheduleForm(@ModelAttribute("scheduleBean") ScheduleBean sb) {
		service.addSchedule(sb);
		return "redirect:/schedule";
	}
	@ModelAttribute("companyList")
	public Map<Integer, String> getCompanyList() {
		Map<Integer, String> companyMap = new HashMap<>();
		List<ScheduleBean> list = service.getScheduleList();
		for (ScheduleBean cb : list) {
			companyMap.put(cb.getScheduleId(), cb.getEmpName());
		}
		return companyMap;
	}
//	@ModelAttribute("categoryList")
//	public List<String> getCategoryList() {
//		return service.getAllCategories();
//	}
}

	
	
	
	
	


