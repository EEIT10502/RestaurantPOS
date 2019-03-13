package _04schedule.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.ScheduleBean;
import _04schedule.dao.ScheduleDao;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
	@Autowired
	SessionFactory factory;

	public ScheduleDaoImpl() {

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ScheduleBean> findAllSchedule() {
		String hql = "FROM ScheduleBean";
		Session session = null;
		List<ScheduleBean> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	/*-----------------------------------修改班表資料-----------------------------------*/
	
	@Override
	public void updateSchedule(ScheduleBean schedule) {
		System.out.println("1");
		Session session = factory.getCurrentSession();
		System.out.println("2");
		session.update(schedule);
		System.out.println("3");
	}

	/*-----------------------------------新增班表資料-----------------------------------*/
	@Override
	public void saveSchedule(ScheduleBean schedule) {
		Session session = factory.getCurrentSession();
		session.save(schedule);

	}
	
	@Override
	public ScheduleBean findByPrimaryKey(int scheduleId) {
		ScheduleBean sb = null;
		Session session = factory.getCurrentSession();
		sb = session.get(ScheduleBean.class, scheduleId);
		return sb;
	}

	@Override
	public ScheduleBean findBySchedule(String schedule) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Schedule WHERE schedule=:schedule";
		ScheduleBean sb = null;
		@SuppressWarnings("unchecked")
		List<ScheduleBean> list = session.createQuery(hql)
								.setParameter("schedule", schedule)
								.getResultList();
		if (!list.isEmpty()) 
			sb = list.get(0);
		return sb;
	}
}