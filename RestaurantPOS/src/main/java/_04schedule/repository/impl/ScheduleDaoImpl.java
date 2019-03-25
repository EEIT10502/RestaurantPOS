package _04schedule.repository.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.AttendenceBean;
import _00model.CalendarBean;
import _00model.EmployeeBean;
import _00model.ScheduleBean;
import _04schedule.repository.ScheduleDao;


@Repository
public class ScheduleDaoImpl implements ScheduleDao{
	//打卡查勤用
	java.util.Date uDate1 = null;
	java.util.Date uDate2 = null;
	java.sql.Date beginDate = null;
	java.sql.Date endDate = null;
	
	
	
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
												  setParameter("empNo", Integer.parseInt(empNo)).
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
/*------------------------------------------------------------------------------------------------------------*/
	// String to Date
		@Override
		public void stringToDate(String Date1, String Date2) {		
			String tDate1 = Date1 + " 00:00:00";
			String tDate2 = Date2 + " 23:59:59";
			SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// to util.Date
			try {
				uDate1 = fDate.parse(tDate1);
				uDate2 = fDate.parse(tDate2);
				beginDate = new java.sql.Date(uDate1.getTime());
				endDate = new java.sql.Date(uDate2.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AttendenceBean[]> getAttByDate(String Date1, String Date2) {
		// to sql.Date
		stringToDate(Date1, Date2);
		// hql
		String hql = "FROM AttendenceBean o WHERE o.orderTime>=:beginDate and o.orderTime<=:endDate GROUP BY cast(o.orderTime as date)";
		Session session = factory.getCurrentSession();
		List<AttendenceBean[]> listDailyAtt = session.createQuery(hql).setParameter("beginDate", beginDate)
				.setParameter("endDate", endDate).getResultList();

		return listDailyAtt;
	}
	
	/*------------------------------------------------------------------------------------------------------------*/
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ScheduleBean> findAllSchedule() {
		Session session = null;
		session = factory.getCurrentSession();
		List<ScheduleBean> list = new ArrayList<>();
		String hql = "FROM ScheduleBean";
		list = session.createQuery(hql).getResultList();
		return list;
	}

	//修改班表資料
	@Override
	public void updateSchedule(ScheduleBean schedule) {
		Session session = factory.getCurrentSession();
		session.update(schedule);
	}

	//新增班表資料
	@Override
	public void saveSchedule(ScheduleBean schedule) {
		Session session = factory.getCurrentSession();
		session.save(schedule);

	}
	//刪除班表資料
	@Override
	public void deleteScheduleByPrimaryKey(ScheduleBean schedule) {
		Session session = factory.getCurrentSession();
		session.delete(schedule);	
	}
	
	@Override
	public void deleteAllSchedule() {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM Schedule";
		session.createQuery(hql).executeUpdate();
	}
	
	@Override
	public ScheduleBean findByPrimaryKey(int scheduleId) {
		ScheduleBean sb = null;
		Session session = factory.getCurrentSession();
		sb = session.get(ScheduleBean.class, scheduleId);
		System.out.println(scheduleId);
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

	//查詢所有在職員工(完成)
	@Override
	@SuppressWarnings("unchecked")
	public List<CalendarBean> getAllEmpName() {
		String hql = "FROM CalendarBean c WHERE c.employee.status='在職' ";
		Session session = null;
		List<CalendarBean> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	//修改員工班別
	@Override
	public CalendarBean findByPrimary(int calendarId) {
		CalendarBean sb = null;
		Session session = factory.getCurrentSession();
		sb = session.get(CalendarBean.class, calendarId);
		System.out.println(calendarId);
		return sb;
	}
	//更新Calendar資料
	@Override
	public void updateCalendar(CalendarBean calendar) {
		Session session = factory.getCurrentSession();
		session.update(calendar);
		
	}
	//測試
	@Override
	public CalendarBean testGet(int id) {
		Session session = factory.getCurrentSession();
		return session.get(CalendarBean.class, id);
		
	}

}
