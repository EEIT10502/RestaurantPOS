package _02employee.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import _00model.EmployeeBean;
import _02employee.repository.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	SessionFactory factory;

	@Override
	//新增員工資料
	public void addEmployee(EmployeeBean employee) {
		Session session = factory.getCurrentSession();
		session.save(employee);
	}

	@Override
	public List<EmployeeBean> getAllEmployees() {
		  String hql = "FROM Employee";
		    Session session = factory.getCurrentSession();
		    List<EmployeeBean> list = new ArrayList<>();
		    session = factory.getCurrentSession();
		    list = session.createQuery(hql).getResultList();
		    return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getEmployeesByPosition(String position) {
		List<EmployeeBean> list  = null;
		String hql = "FROM EmployeeBean e WHERE e.position = :position";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("position", position).list();
		return list;
	}

	@Override
	public EmployeeBean getEmployeesByNo(String empNo) {
		EmployeeBean employee  = null;
		Session session = factory.getCurrentSession();
		employee = session.get(EmployeeBean.class, empNo);
//		if(employee==null)throw new ProductNotFoundException("Employee Not Found: ");
		return employee;
	}



	

	
}
