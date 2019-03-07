package _04schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
	@RequestMapping(method=RequestMethod.GET)
	public String schedule() {
		return "schedule/schedule";
	}
}