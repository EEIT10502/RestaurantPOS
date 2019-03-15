package _04schedule.repository;

import java.util.List;

import _00model.AttendenceBean;
import _00model.EmployeeBean;
import _00model.ScheduleBean;

public interface ScheduleDao {

	public EmployeeBean checkByEmpNo(String empNo);
	
	public void addAttendence(AttendenceBean attendenceBean);
	
	
	ScheduleBean findByPrimaryKey(int scheduleId);

	ScheduleBean findBySchedule(String schedule);

	void saveSchedule(ScheduleBean schedule);

	void updateSchedule(ScheduleBean schedule);

	void deleteScheduleByPrimaryKey(int scheduleId);
	
	List<ScheduleBean> findAllSchedule();
	
	
}
