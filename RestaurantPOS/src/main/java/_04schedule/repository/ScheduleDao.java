package _04schedule.repository;

import java.util.List;

import _00model.AttendenceBean;
import _00model.CalendarBean;
import _00model.EmployeeBean;
import _00model.ScheduleBean;

public interface ScheduleDao {

	public EmployeeBean checkByEmpNo(String empNo);
	
	public void addAttendence(AttendenceBean attendenceBean);
	//
	ScheduleBean findByPrimaryKey(int scheduleId);

	ScheduleBean findBySchedule(String schedule);

	void saveSchedule(ScheduleBean schedule);

	void updateSchedule(ScheduleBean schedule);

	void deleteScheduleByPrimaryKey(ScheduleBean schedule);
	
	void deleteAllSchedule();
		
	List<ScheduleBean> findAllSchedule();
	
	//查詢所有員工與班別
	List<CalendarBean> getAllEmpName();
	
	//修改員工班別
	CalendarBean findByPrimary(int calendarId);
	void updateCalendar(CalendarBean calendar);
	
	CalendarBean testGet(int id);
	
}
