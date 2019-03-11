package _04schedule.service;

import java.util.List;

import org.springframework.stereotype.Component;

import _00model.ScheduleBean;

@Component("ScheduleServiceDemo")
public interface ScheduleService {
	List<ScheduleBean> findAll();
	/*-----------------------------------新增班表資料-----------------------------------*/
		void addSchedule(ScheduleBean schedule);
		ScheduleBean getScheduleById(Integer scheduleId);
		List<ScheduleBean> getScheduleList();
}
