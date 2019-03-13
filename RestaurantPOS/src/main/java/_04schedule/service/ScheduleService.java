package _04schedule.service;


import java.util.List;
import _00model.AttendenceBean;
import _00model.EmployeeBean;
import org.springframework.stereotype.Component;
import _00model.ScheduleBean;

@Component("ScheduleServiceDemo")
public interface ScheduleService {
	List<ScheduleBean> findAllSchedule();

	void saveSchedule(ScheduleBean schedule);

	ScheduleBean findByPrimaryKey(int scheduleId);
	
	ScheduleBean findBySchedule(String schedule);
	
	void updateSchedule(ScheduleBean scheduleId);

	public EmployeeBean checkByEmpNo(String empNo);
	
	public void addAttendence(AttendenceBean attendenceBean);

}
