package _02employee.repository.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.AttendenceBean;
import _00model.EmployeeBean;
import _00model.ManagerBean;
import _02employee.repository.EmployeeDao;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	java.util.Date uDate1 = null;
	java.util.Date uDate2 = null;
	java.sql.Date beginDate = null;
	java.sql.Date endDate = null;
	
	@Autowired
	SessionFactory factory;

	@Override
	//新增員工資料
	public void addEmployee(EmployeeBean employee) {
		Session session = factory.getCurrentSession();
		session.save(employee);
	}

	//列出所有員工資料
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getAllEmployees() {
		  String hql = "FROM EmployeeBean";
		    Session session = null;
		    List<EmployeeBean> list = new ArrayList<>();
		    session = factory.getCurrentSession();
		    list = session.createQuery(hql).getResultList();
		    return list;
	}
	
	
//	//透過員工NO取得單筆詳細資訊
//	@Override
//	public EmployeeBean getEmployeesByNo(String empNo) {
//		Session session = factory.getCurrentSession();
//		EmployeeBean employee = session.get(EmployeeBean.class, empNo);
//		return employee;
//	}

	//透過員工ID取得單筆詳細資訊
		@Override
		public EmployeeBean getEmployeesById(int empId) {
			Session session = factory.getCurrentSession();
			EmployeeBean employee = session.get(EmployeeBean.class, empId);
			return employee;
		}

		//更新員工資訊
		@Override
		public void updateEmployee(EmployeeBean employee) {
			Session session = factory.getCurrentSession();
			session.update(employee);
			
		}

		@Override
		public ManagerBean checkIDPassword(String mAccount, String mPwd) {
			// TODO Auto-generated method stub
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<AttendenceBean> getAllAttendence() {
			String hql = "FROM AttendenceBean";
		    Session session = null;
		    List<AttendenceBean> list = new ArrayList<>();
		    session = factory.getCurrentSession();
		    list = session.createQuery(hql).getResultList();
		    return list;
			
		}
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
		public List<AttendenceBean> getAttendenceListByDate(String Date1, String Date2) {
			stringToDate(Date1, Date2);
			String hql = "FROM AttendenceBean a WHERE a.date>=:beginDate and a.date<=:endDate";
			Session session = factory.getCurrentSession();
			List<AttendenceBean> EmpNameListByDate = session.createQuery(hql).setParameter("beginDate", beginDate)
					.setParameter("endDate", endDate).getResultList();
			return EmpNameListByDate;
		}

		

		
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<EmployeeBean> getEmployeesByPosition(String position) {
//		List<EmployeeBean> list  = null;
//		String hql = "FROM EmployeeBean e WHERE e.position = :position";
//		Session session = factory.getCurrentSession();
//		list = session.createQuery(hql).setParameter("position", position).list();
//		return list;
//	}

	

		
	


	

	
}
