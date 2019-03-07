package _04schedule.dao.impl;

import java.util.*;

import org.hibernate.*;
import org.hibernate.transform.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.*;
import _04schedule.dao.ScheduleDao;

@Repository("scheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduleBean> findAll(){
		List<ScheduleBean> list= null;
		Session session = null;
		Transaction transaction =null;
		try {
			session = sessionFactory.openSession();
			 transaction = session.beginTransaction();
		 list=session.createQuery("select s.scheduleId as scheduleId,"
					 +"s.date as date,"
					 +"s.empNo as empNo,"
					 +"s.empName as empName,"
					 +"DARE_FORMAT(s.startTime,'%Y-%m-%d') as startTime,"
					 +"DARE_FORMAT(s.endTime,'%Y-%m-%d') as endTime,"
					 +"s.choose as choose,"
					 +"from ScheduleBean s")
					 .setResultTransformer(
						Transformers.aliasToBean(ScheduleBean.class))
					 .list();
			 transaction.commit();
		} catch (Exception e) {
			list= null;
			if(transaction!=null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return list;
	}
}