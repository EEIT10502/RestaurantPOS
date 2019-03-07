package _04schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.ScheduleBean;
import _00model.ScheduleBean;
import _04schedule.dao.ScheduleDao;

@Repository("scheduleService")
public class ScheduleServiceImpl implements ScheduleDao {
	@Autowired
	private ScheduleDao scheduleDao;

	@Override
	public List<ScheduleBean> findAll() {
		return scheduleDao.findAll();
	}
}