package _02employee.repository;


import java.util.List;

import _00model.AttendenceBean;
import _00model.EmployeeBean;
import _00model.ManagerBean;


public interface EmployeeDao {

	// 新增員工資料
	void addEmployee(EmployeeBean employee);

	// 列出所有員工資料
	List<EmployeeBean> getAllEmployees();
	
	//更新員工資料
	void updateEmployee(EmployeeBean employee);

//	// 以員工NO查詢員工
//	public EmployeeBean getEmployeesByNo(String empNo);
	
	// 以員工Id查詢員工
	public EmployeeBean getEmployeesById(int empId);
	
	// 以職位查詢員工
//	List<EmployeeBean> getEmployeesByPosition(String position);


	public ManagerBean checkIDPassword(String mAccount,String mPwd);
	
	//Tai
	List<AttendenceBean> getAllAttendence();
	
	
	
}
