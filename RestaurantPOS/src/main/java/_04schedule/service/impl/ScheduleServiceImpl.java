package _04schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.ScheduleBean;
import _04schedule.dao.ScheduleDao;
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

	@Override
	public ScheduleBean findBySchedule(String schedule) {
		return scheduleDao.findBySchedule(schedule);
	}

	@Override
	public void updateSchedule(ScheduleBean schedule) {
		scheduleDao.updateSchedule(schedule);
	}

	
}