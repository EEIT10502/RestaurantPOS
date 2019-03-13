package _04schedule.service;

import java.util.List;

import org.springframework.stereotype.Component;

import _00model.ScheduleBean;

@Component("ScheduleServiceDemo")
public interface ScheduleService {
	List<ScheduleBean> findAllSchedule();

	void saveSchedule(ScheduleBean schedule);

	ScheduleBean findByPrimaryKey(int scheduleId);
	
	ScheduleBean findBySchedule(String schedule);
	
	void updateSchedule(ScheduleBean scheduleId);
}
