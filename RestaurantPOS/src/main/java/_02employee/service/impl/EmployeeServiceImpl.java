package _02employee.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.EmployeeBean;
import _00model.ManagerBean;
import _02employee.repository.EmployeeDao;
import _02employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;

	@Transactional
	@Override
	public void addEmployee(EmployeeBean employee) {
		employeeDao.addEmployee(employee);
		
	}

	//列出所有員工資料
	@Transactional
	@Override
	public List<EmployeeBean> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}


//	//透過員工NO取得單筆詳細資訊
//	@Transactional
//	@Override
//	public EmployeeBean getEmployeesByNo(String empNo) {
//		return employeeDao.getEmployeesByNo(empNo);
//	}
	
	//透過員工ID取得單筆詳細資訊
		@Transactional
		@Override
		public EmployeeBean getEmployeesById(int empId) {
			return employeeDao.getEmployeesById(empId);
		}

		//更新員工資料
		@Override
		public void updateEmployee(EmployeeBean employee) {
			employeeDao.updateEmployee(employee);
		}

//	@Transactional
//	@Override
//	public List<EmployeeBean> getEmployeesByPosition(String position) {
//		return employeeDao.getEmployeesByPosition(position);
//	}
		
		@Autowired
		EmployeeDao dao;

		@Transactional
		@Override
		public ManagerBean checkIDPassword(String mAccount, String mPwd) {
			
			ManagerBean managerBean =null;
			
			managerBean = dao.checkIDPassword(mAccount, mPwd);
			
			return managerBean;
		}

	
	

	


}
