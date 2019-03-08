package _02employee.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import _00model.EmployeeBean;

@Repository
public interface EmployeeDao {

	// 新增員工資料
	void addEmployee(EmployeeBean employee);

	// 列出所有員工
	List<EmployeeBean> getAllEmployees();

	// 以職位查詢員工
	List<EmployeeBean> getEmployeesByPosition(String position);

	// 以員工編號查詢員工
	public EmployeeBean getEmployeesByNo(String empNo);

}
