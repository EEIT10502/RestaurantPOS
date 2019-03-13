package _04schedule.dao;

import java.util.List;

import _00model.ScheduleBean;

public interface ScheduleDao {

	ScheduleBean findByPrimaryKey(int scheduleId);

	ScheduleBean findBySchedule(String schedule);

	void saveSchedule(ScheduleBean schedule);

	void updateSchedule(ScheduleBean schedule);

	List<ScheduleBean> findAllSchedule();
}
