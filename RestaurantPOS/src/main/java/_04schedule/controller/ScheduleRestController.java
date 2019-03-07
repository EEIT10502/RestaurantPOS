package _04schedule.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import _04schedule.service.ScheduleService;

@Controller
@RequestMapping("/schedule/schedule")
public class ScheduleRestController {
	
	@Autowired
	private ScheduleService scheduleService;

	@RequestMapping(value = "findall", method = RequestMethod.GET)
	@ResponseBody
	public String findall() {
		Gson gson = new Gson();
		return gson.toJson(scheduleService.findAll());
	}
}