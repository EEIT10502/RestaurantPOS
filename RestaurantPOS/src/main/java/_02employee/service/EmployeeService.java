package _02employee.service;

import java.sql.Blob;
import java.util.List;

import _00model.AttendenceBean;
import _00model.EmployeeBean;

public interface EmployeeService {

	/*
	 * 以下為資料編輯 包含： 1.註冊：員工資料(empNo自動生成未完成) 1-1.取得現有職位(MAX)編號
	 * 1-2.設定就業狀態select(status) 1-3.設定職位select(position) 2.更新：員工資料(empImg未完成)
	 * 
	 */

	// 1.註冊：員工資料
	void addEmployee(EmployeeBean employee);

//	//1-1.取得現有職位(MAX)編號
//	Integer getCurrentPositionNumber(String positionInsert);
	
	//TEST INSERT EMPNO
	Integer getCurrentPositionNumber();

	// 1-2.設定就業狀態select(status)
	void setStatusSelect(String statusSelect);

	// 1-3.設定職位select(position)??不用@override嗎
	void setPositionSelect(String positionSelect);

	// 2.更新：員工資料
	void updateEmployee(EmployeeBean employee);

	/*
	 * 以下為搜尋 包含： 1.列出所有員工資料(完成) 2.empId-以員工ID搜尋(完成) 3.empNo-以員工編號搜尋 4.status-以就業狀態顯示
	 * 5.position-以員工職位搜尋 5-1.列出所有職位 5-1-1.列出服務生 5-1-2.列出廚師 5-1-3.列出主廚 5-1-4.列出經理
	 * 5-2.分別以職位顯示 6.searchBar-以搜尋列顯示員工表 6-1.設定搜尋列 6-2.列出搜尋列搜尋結果
	 * 
	 */

	// 1.搜尋：列出所有員工資料(完成)
	List<EmployeeBean> getAllEmployees();

	// 2.搜尋：以員工Id(PK)查詢員工(完成)-none
	public EmployeeBean getEmployeesById(int empId);

////	 3.搜尋：以員工編號empNo-none
//	public EmployeeBean getEmployeesByNo(String empNo);

	// 4.status-以就業狀態顯示
	List<EmployeeBean> getEmployeesListGetByEmployeesStatus();

	// 5.以員工職位搜尋
	// 5-1.搜尋：列出所有職位
	List<String> getAllPositions();

	// 5-1-1.搜尋：列出服務生(測試?)
	List<EmployeeBean> getAllPositionsTestWaiter();

	// 5-1-2.搜尋：列出廚師(測試?)
	List<EmployeeBean> getAllPositionsTestEChef();

	// 5-1-3.搜尋：列出主廚(測試?)
	List<EmployeeBean> getAllPositionsTestMChef();

	// 5-1-4.搜尋：列出經理(測試?)
	List<EmployeeBean> getAllPositionsTestManager();

	// 5-2.搜尋：分別以職位顯示
	List<EmployeeBean> getEmployeesListGetByPosition();

	// 6.以搜尋列顯示員工表
	// 6-1.設定搜尋列
	void setSearchBarString(String searchBarString);

	// 6-2.列出搜尋列搜尋結果
	List<EmployeeBean> getEmployeesListGetBySearch();

	/*
	 * 分頁顯示 1.取得員工總數 2.取得總頁數 3.取得員工列表頁數 4.取得每頁顯示的員工數量 5.設定每頁顯示的員工數量 6.取得現在頁數
	 * 7.設定現在頁數 8.取得搜尋後總員工數量 9.取得搜尋後總頁數 10.取得各職位總數 11.取得各職位總頁數 12.取得各員工狀態頁面
	 * 13.取得各員工狀態數量
	 */

//	// 1.取得員工總數-none
//	long getTotalEmployeesCounts();

	// 2.取得總頁數
	int getTotalPages();

	// 3.取得員工列表頁數
	List<EmployeeBean> getEmployeesListGetByPage();

	// 4.取得每頁顯示的員工數量
	int getEmployeesPerPage();

	// 5.設定每頁顯示的員工數量
	void setEmployeesPerPage(int employeesPerPage);

	// 6.取得現在頁數
	int getCurrentPageNo();

	// 7.設定現在頁數
	void setCurrentPageNo(int currentPageNo);

//	// 8.取得搜尋後總員工數量-none
//	long getTotalEmployeesCountsBySearch();

	// 9.取得搜尋後總頁數
	int getTotalPagesBySearch();

//	// 10.取得各職位總數-none
//	long getTotalEmployeesCountsByPosition();

	// 11.取得各職位總頁數
	int getTotalPagesByPosition();

	// 12.取得各員工狀態頁面
	int getTotalPagesByEmployeesStatus();

//	// 13.取得各員工狀態數量-none
//	long getTotalEmployeeCountsByEmployeeStatus();
	
	// 14.取得員工照片
	Blob getEmployeePicture(Blob pFromDB);
	
	
	//Tai 用於出勤表(完成)
	List<AttendenceBean> getAllAttendence();
	//Tai 用於出勤表(尚未完成)
	List<AttendenceBean> getAttendenceListByDate(String Date1, String Date2);
	
	EmployeeBean findByPrimaryKey(int key);
	

}
