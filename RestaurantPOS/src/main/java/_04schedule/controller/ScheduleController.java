package _04schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ScheduleController {
	@RequestMapping("/schedule")
	public String schedule() {
		return "/scheduleManage/schedule";
	}
}