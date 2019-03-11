package _04schedule.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.ScheduleBean;
import _04schedule.dao.ScheduleDao;

@Repository("scheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {
	@Autowired
	SessionFactory factory;

	@Override
	@SuppressWarnings("unchecked")
	public List<ScheduleBean> findAll() {
		String hql = "FROM ScheduleBean";
		Session session = null;
		List<ScheduleBean> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	/*-----------------------------------新增班表資料-----------------------------------*/
	@Override
	public void addSchedule(ScheduleBean schedule) {
		Session session = factory.getCurrentSession();
		ScheduleBean sb = getScheduleById(schedule.getScheduleId());
		schedule.setScheduleBean(sb);
		session.save(schedule);
	}

	@Override
	public ScheduleBean getScheduleById(Integer scheduleId) {
		ScheduleBean sb = null;
		Session session = factory.getCurrentSession();
		sb = session.get(ScheduleBean.class, scheduleId);
		return sb;
	}

	@Override
	public List<ScheduleBean> getScheduleList() {
		String hql = "From ScheduleBean";
		Session session = factory.getCurrentSession();
		List<ScheduleBean> list = session.createQuery(hql).getResultList();
		return list;
	}

}