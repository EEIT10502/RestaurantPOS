package _04schedule.repository.impl;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.AttendenceBean;
import _00model.EmployeeBean;
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
			int IempNo = Integer.parseInt(empNo);
			employeeBean = session.get(EmployeeBean.class, IempNo);
			
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

	
	


	
	

}
