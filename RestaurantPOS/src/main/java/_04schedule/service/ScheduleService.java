package _04schedule.service;

import _00model.AttendenceBean;
import _00model.EmployeeBean;

public interface ScheduleService {

	
	public EmployeeBean checkByEmpNo(String empNo);
	
	public void addAttendence(AttendenceBean attendenceBean);
}
