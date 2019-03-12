package _04schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.AttendenceBean;
import _00model.EmployeeBean;
import _04schedule.repository.ScheduleDao;
import _04schedule.service.ScheduleService;


@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	ScheduleDao dao;

	
	
	public ScheduleServiceImpl() {
		
	}
	
	@Transactional
	@Override
	public EmployeeBean checkByEmpNo(String empNo) {
		
		return dao.checkByEmpNo(empNo);
	}
	
	@Transactional
	@Override
	public void addAttendence(AttendenceBean attendenceBean) {
		
		dao.addAttendence(attendenceBean);
	}
	
	
	

}
