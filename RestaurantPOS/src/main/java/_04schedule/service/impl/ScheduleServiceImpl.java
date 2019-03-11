package _04schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.ScheduleBean;
import _04schedule.dao.ScheduleDao;
import _04schedule.service.ScheduleService;
@Service
@Repository("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleDao scheduleDao;

	@Transactional
	@Override
	public List<ScheduleBean> findAll() {
		return scheduleDao.findAll();
	}
	/*-----------------------------------新增班表資料-----------------------------------*/
	@Transactional
	@Override
	public void addSchedule(ScheduleBean schedule) {
		scheduleDao.addSchedule(schedule);
		
	}
	@Transactional
	@Override
	public ScheduleBean getScheduleById(Integer scheduleId) {
		return scheduleDao.getScheduleById(scheduleId);
	}
	@Transactional
	@Override
	public List<ScheduleBean> getScheduleList() {
		return scheduleDao.getScheduleList();
	}

	
}