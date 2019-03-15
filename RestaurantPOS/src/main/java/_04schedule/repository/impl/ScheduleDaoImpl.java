package _04schedule.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.AttendenceBean;
import _00model.EmployeeBean;
import _00model.ScheduleBean;
import _04schedule.repository.ScheduleDao;


@Repository
public class ScheduleDaoImpl implements ScheduleDao{

	@Autowired
	SessionFactory factory;
	
	public ScheduleDaoImpl() {
		
	}
	
	
	//使用該方法檢測拿到的empNo 是否真有其人
	@Override
	public EmployeeBean checkByEmpNo(String empNo) {
		
		EmployeeBean employeeBean = null;
		Session session = factory.getCurrentSession();
		
		try {
			String hql="FROM EmployeeBean WHERE empNo = :empNo";
			employeeBean = (EmployeeBean) session.createQuery(hql).
												  setParameter("empNo", empNo).
												  uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ScheduleDaoImpl checkByEmpNo error"+e.getMessage());
			employeeBean = null;
		}
		
		return employeeBean;
	}

	@Override
	public void addAttendence(AttendenceBean attendenceBean) {
		Session session = factory.getCurrentSession();
		session.save(attendenceBean);
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
//		String hql = "UPDATE ScheduleBean SET schedule=:schedule WHERE scheduleId=:scheduleId";
		Session session = factory.getCurrentSession();
		System.out.println(schedule.getScheduleId());	//ID
		System.out.println(schedule.getSchedule());		//班別
		session.update(schedule);
	}

	/*-----------------------------------新增班表資料-----------------------------------*/
	@Override
	public void saveSchedule(ScheduleBean schedule) {
		Session session = factory.getCurrentSession();
		session.save(schedule);

	}
	/*-----------------------------------刪除班表資料-----------------------------------*/
	@Override
	public void deleteScheduleByPrimaryKey(int scheduleId) {
		Session session = factory.getCurrentSession();
		ScheduleBean sb = new ScheduleBean();
		sb.setScheduleId(scheduleId);
		session.delete(sb);	
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
