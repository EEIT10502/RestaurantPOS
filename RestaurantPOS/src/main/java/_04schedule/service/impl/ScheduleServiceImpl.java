package _04schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.AttendenceBean;
import _00model.CalendarBean;
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
	public List<AttendenceBean> getAttByDate(String Date1, String Date2) {
		return scheduleDao.getAttByDate(Date1, Date2);
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
	public void deleteScheduleByPrimaryKey(ScheduleBean schedule) {
		scheduleDao.deleteScheduleByPrimaryKey(schedule);
	}
	
	@Transactional
	@Override
	public void deleteAllSchedule() {
		scheduleDao.deleteAllSchedule();		
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

	@Transactional
	@Override
	public List<CalendarBean> getAllEmpName() {
		return scheduleDao.getAllEmpName();
	}

	@Transactional
	@Override
	public CalendarBean findByPrimary(int calendarId) {
		return scheduleDao.findByPrimary(calendarId);
	}
	@Transactional
	@Override
	public void updateCalendar(CalendarBean calendar) {
		scheduleDao.updateCalendar(calendar);
	}

	@Transactional
	@Override
	public CalendarBean testGet(int id) {
		return scheduleDao.testGet(id);
	}

}