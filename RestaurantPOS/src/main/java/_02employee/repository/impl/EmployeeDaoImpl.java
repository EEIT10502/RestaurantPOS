package _02employee.repository.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.EmployeeBean;
import _00model.ManagerBean;
import _02employee.repository.EmployeeDao;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
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
		
		
		//列出職員類別
		@SuppressWarnings("unchecked")
		@Override
		public List<String> getAllEmpCategories() {
			List<String> list  = null;
			String hql = "SELECT DISTINCT position FROM Employee";
			Session session = factory.getCurrentSession();
			list = session.createQuery(hql).list();
			return list;
		}

		//取得現有員工編號最大值
		@Override
		public Integer getCurrentEmpNo(int currentEmpNo) {
				String hql = "SELECT MAX(empNo) FROM Employee";
				Session session = factory.getCurrentSession();
				Integer currentEmpNo1 = (Integer) session.createQuery(hql).setParameter(0, currentEmpNo).uniqueResult();
				return currentEmpNo1;
			}


		@Override
		public List<EmployeeBean> getEmployeesByPosition(String position) {
			// TODO Auto-generated method stub
			return null;
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
