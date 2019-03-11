package _04schedule.dao;

import java.util.List;

import _00model.ScheduleBean;

public interface ScheduleDao {
	List<ScheduleBean> findAll();
	/*-----------------------------------新增班表資料-----------------------------------*/
	void addSchedule(ScheduleBean schedule);
	ScheduleBean getScheduleById(Integer scheduleId);
	List<ScheduleBean> getScheduleList();
}
