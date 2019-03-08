package _02employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import _00model.EmployeeBean;


public interface EmployeeService {
	
	// 新增員工資料
	void addEmployee(EmployeeBean employee);

	// 列出所有員工
	List<EmployeeBean> getAllEmployees();

	// 以職位查詢員工
	List<EmployeeBean> getEmployeesByPosition(String position);

	// 以員工編號查詢員工
	public EmployeeBean getEmployeesByNo(String empNo);

}
