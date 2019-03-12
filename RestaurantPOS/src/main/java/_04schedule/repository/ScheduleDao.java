package _04schedule.repository;

import _00model.AttendenceBean;
import _00model.EmployeeBean;

public interface ScheduleDao {

	public EmployeeBean checkByEmpNo(String empNo);
	
	public void addAttendence(AttendenceBean attendenceBean);
}
