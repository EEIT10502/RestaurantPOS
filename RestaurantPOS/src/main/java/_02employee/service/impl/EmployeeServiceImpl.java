package _02employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.EmployeeBean;
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

	// 列出所有員工資料
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

	// 透過員工ID取得單筆詳細資訊
	@Transactional
	@Override
	public EmployeeBean getEmployeesById(int empId) {
		return employeeDao.getEmployeesById(empId);
	}

	// 更新員工資料
	@Transactional
	@Override
	public void updateEmployee(EmployeeBean employee) {
		employeeDao.updateEmployee(employee);

	}

	// 取得目前員工編號
	@Transactional
	@Override
	public Integer getCurrentEmpNo(int currentEmpNo) {
		employeeDao.getCurrentEmpNo(currentEmpNo);
		return null;
	}

	// 列出職員類別
	@Transactional
	@Override
	public List<String> getEmpAllCategories() {
		return employeeDao.getAllEmpCategories();
	}

	// 取得員工職位
	@Transactional
	@Override
	public List<EmployeeBean> getEmployeesByPosition(String position) {
		return employeeDao.getEmployeesByPosition(position);
	}

}
