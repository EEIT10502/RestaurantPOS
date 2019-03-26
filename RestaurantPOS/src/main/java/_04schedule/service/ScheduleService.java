package _04schedule.service;

import java.util.List;

import org.springframework.stereotype.Component;

import _00model.AttendenceBean;
import _00model.CalendarBean;
import _00model.EmployeeBean;
import _00model.ScheduleBean;

@Component("ScheduleServiceDemo")
public interface ScheduleService {
	public EmployeeBean checkByEmpNo(String empNo);

	public void addAttendence(AttendenceBean attendenceBean);
	//完成
	public List<AttendenceBean> getAttByDate(String Date1, String Date2);

	ScheduleBean findByPrimaryKey(int scheduleId);

	ScheduleBean findBySchedule(String schedule);

	void saveSchedule(ScheduleBean schedule);

	void updateSchedule(ScheduleBean schedule);

	void deleteScheduleByPrimaryKey(int scheduleId);

	List<ScheduleBean> findAllSchedule();
	
	void deleteAllSchedule();

	// 查詢所有員工
	List<CalendarBean> getAllEmpName();

	// 修改員工班別
	CalendarBean findByPrimary(int calendarId);

	void updateCalendar(CalendarBean calendar);

	CalendarBean testGet(int id);
}
