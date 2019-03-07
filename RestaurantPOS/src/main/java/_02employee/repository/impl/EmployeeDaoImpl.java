package _02employee.repository.impl;

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
	public EmployeeDao findByName(String Name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Employee WHERE empName=:empName";
		EmployeeBean employee = null;
		@SuppressWarnings("unchecked")
		List<EmployeeBean> list = session.createQuery(hql)
								.setParameter("name", employee)
								.getResultList();
		if (!list.isEmpty()) 
			employee = list.get(0);
		return null;
		
	}
	

	
}
