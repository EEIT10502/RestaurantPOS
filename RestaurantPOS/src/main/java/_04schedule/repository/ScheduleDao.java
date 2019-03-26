package _04schedule.repository;

import java.util.List;

import _00model.AttendenceBean;
import _00model.CalendarBean;
import _00model.EmployeeBean;
import _00model.ScheduleBean;

public interface ScheduleDao {

	public EmployeeBean checkByEmpNo(String empNo);
	
	
	
	//查詢所有Attendance資料
	public void addAttendence(AttendenceBean attendenceBean);
	
	//daily
	public List<AttendenceBean> getAttByDate(String Date1, String Date2);
	
	
	
	
	
	//查詢所有Schedule資料
	ScheduleBean findByPrimaryKey(int scheduleId);

	ScheduleBean findBySchedule(String schedule);
	//新增、修改、刪除Schedule資料
	void saveSchedule(ScheduleBean schedule);

	void updateSchedule(ScheduleBean schedule);

	void deleteScheduleByPrimaryKey(int scheduleId);
	
	void deleteAllSchedule();
		
	List<ScheduleBean> findAllSchedule();
	
	//查詢所有員工與班別
	List<CalendarBean> getAllEmpName();
	
	//修改員工班別
	CalendarBean findByPrimary(int calendarId);
	void updateCalendar(CalendarBean calendar);
	//測試
	CalendarBean testGet(int id);
	
}
