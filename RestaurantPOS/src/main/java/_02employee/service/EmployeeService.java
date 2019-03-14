package _02employee.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import _00model.EmployeeBean;
import _00model.ManagerBean;


public interface EmployeeService {
	
	// 新增員工資料
	void addEmployee(EmployeeBean employee);

	// 列出所有員工資料
	List<EmployeeBean> getAllEmployees();
	
	//更新資料
	void updateEmployee(EmployeeBean employee);
	
	//取得目前員工編號
	Integer getCurrentEmpNo(int currentEmpNo);
	
	//列出職員類別
	List<String>  getEmpAllCategories();
	
	// 以職位查詢員工
	List<EmployeeBean> getEmployeesByPosition(String position);

//	// 以員工NO查詢員工
//	public EmployeeBean getEmployeesByNo(String empNo);
	
	// 以員工ID查詢員工
	public EmployeeBean getEmployeesById(int empId);
	
	

//	//圖片轉格式
//	public Blob fileToBlob(String imageFileName) throws IOException, SQLException;
//
//	//讀取圖片
//	public Blob fileToBlob(InputStream is, long size) throws IOException, SQLException;
	
	

	
}
