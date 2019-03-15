package _04schedule.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.AttendenceBean;
import _00model.EmployeeBean;
import _00model.ScheduleBean;
import _04schedule.repository.ScheduleDao;
import _04schedule.service.ScheduleService;


@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	

	@Autowired
	ScheduleDao scheduleDao;

	public ScheduleServiceImpl() {

	}

	@Transactional
	@Override
	public List<ScheduleBean> findAllSchedule() {
		return scheduleDao.findAllSchedule();
	}

	@Transactional
	@Override
	public void saveSchedule(ScheduleBean schedule) {
		scheduleDao.saveSchedule(schedule);
	}

	@Transactional
	@Override
	public ScheduleBean findByPrimaryKey(int scheduleId) {
		return scheduleDao.findByPrimaryKey(scheduleId);
	}
	@Transactional
	@Override
	public ScheduleBean findBySchedule(String schedule) {
		return scheduleDao.findBySchedule(schedule);
	}
	@Transactional
	@Override
	public void updateSchedule(ScheduleBean schedule) {
		scheduleDao.updateSchedule(schedule);
	}
	@Transactional
	@Override
	public 	void deleteScheduleByPrimaryKey(int scheduleId) {
		scheduleDao.deleteScheduleByPrimaryKey(scheduleId);
	}
	
	@Transactional
	@Override
	public EmployeeBean checkByEmpNo(String empNo) {
		
		return scheduleDao.checkByEmpNo(empNo);
	}
	
	@Transactional
	@Override
	public void addAttendence(AttendenceBean attendenceBean) {
		
		scheduleDao.addAttendence(attendenceBean);
	}



	
}