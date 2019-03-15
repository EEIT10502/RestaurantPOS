package _02employee.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00.init.util.GlobalService;
import _00model.EmployeeBean;
import _00model.ManagerBean;
import _00model.MenuBean;
import _02employee.repository.EmployeeDao;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	SessionFactory factory;

	/**
	 * 以下為資料編輯，包含： 1.註冊：員工資料(empNo自動生成未完成) 1-1.取得現有職位(MAX)編號
	 * 1-2.設定就業狀態select(status) 1-3.設定職位select(position) 2.更新：員工資料(empImg未完成)
	 */

	// 1.註冊：員工資料
	@Override
	public void addEmployee(EmployeeBean employee) {
		Session session = factory.getCurrentSession();
		session.save(employee);
	}

	// 1-1.取得現有職位(MAX)編號
	@Override
	public Integer getCurrentPositionNumber(String positionInsert) {
		String hql = "SELECT MAX(empNo) FROM Employee Where position = ?0";
		Session session = factory.getCurrentSession();
		Integer CurrentCategoryNumber = (Integer) session.createQuery(hql).setParameter(0, positionInsert)
				.uniqueResult();
		return CurrentCategoryNumber;
	}

	// 2.更新：員工資訊
	@Override
	public void updateEmployee(EmployeeBean employee) {
		Session session = factory.getCurrentSession();
		session.update(employee);
	}

	/*
	 * 以下為搜尋，包含： 1.列出所有員工資料(完成) 2.empId-以員工ID搜尋(完成) 3.empNo-以員工編號搜尋 4.status-以就業狀態顯示
	 * 5.position-以員工職位搜尋 5-1.列出所有職位 5-1-1.列出服務生 5-1-2.列出廚師 5-1-3.列出主廚 5-1-4.列出經理
	 * 5-2.分別以職位顯示 6.searchBar-以搜尋列顯示員工表 6-1.設定搜尋列 6-2.列出搜尋列搜尋結果
	 * 
	 */

	// 1.搜尋：列出所有員工資料(完成)
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

	// 2.搜尋：以員工Id(PK)查詢員工(完成)
	@Override
	public EmployeeBean getEmployeesById(int empId) {
		Session session = factory.getCurrentSession();
		EmployeeBean employee = session.get(EmployeeBean.class, empId);
		return employee;
	}

//	//3.搜尋：以員工編號empNo
//	@Override
//	public EmployeeBean getEmployeesByNo(String empNo) {
//		Session session = factory.getCurrentSession();
//		EmployeeBean employee = session.get(EmployeeBean.class, empNo);
//		return employee;
//	}

	// 5-1.搜尋：列出所有職位
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllPositions() {
		List<String> positionList = null;
		String hql = "SELECT DISTINCT position FROM Employee";
		Session session = factory.getCurrentSession();
		positionList = session.createQuery(hql).list();
		return positionList;
	}

	// 5-1-1.列出服務生
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getAllPositionsTestWaiter() {
		String hql = "From Employee Where position = ?0 Order By empNo";
		Session session = factory.getCurrentSession();
		List<EmployeeBean> allPositionsTestWaiter = new ArrayList<>();
		allPositionsTestWaiter = session.createQuery(hql).setParameter(0, GlobalService.Employee_CATE_waiter)
				.getResultList();
		return allPositionsTestWaiter;
	}

	// 5-1-2.列出廚師
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getAllPositionsTestEChef() {
		String hql = "From Employee Where position = ?0 Order By empNo";
		Session session = factory.getCurrentSession();
		List<EmployeeBean> allPositionsTestEChef = new ArrayList<>();
		allPositionsTestEChef = session.createQuery(hql).setParameter(0, GlobalService.Employee_CATE_EChef)
				.getResultList();
		return allPositionsTestEChef;
	}

	// 5-1-3.列出主廚
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getAllPositionsTestMChef() {
		String hql = "From Employee Where position = ?0 Order By empNo";
		Session session = factory.getCurrentSession();
		List<EmployeeBean> allPositionsTestMChef = new ArrayList<>();
		allPositionsTestMChef = session.createQuery(hql).setParameter(0, GlobalService.Employee_CATE_MChef)
				.getResultList();
		return allPositionsTestMChef;
	}

	// 5-1-3.列出經理
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getAllPositionsTestManager() {
		String hql = "From Employee Where position = ?0 Order By empNo";
		Session session = factory.getCurrentSession();
		List<EmployeeBean> llPositionsTestManager = new ArrayList<>();
		llPositionsTestManager = session.createQuery(hql).setParameter(0, GlobalService.Employee_CATE_manager)
				.getResultList();
		return llPositionsTestManager;
	}
	
	

}
