package _02employee.service.impl;

import java.sql.Blob;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import _00model.AttendenceBean;
import _00model.CumulativeTurnoverBean;
import _00model.EmployeeBean;
import _00model.MenuBean;
import _02employee.repository.EmployeeDao;
import _02employee.service.EmployeeService;
import _03product.service.ProductService;
import _03product.service.impl.ProductServiceImpl;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	/*
	 * 以下為資料編輯 包含： 1.註冊：員工資料(empNo自動生成未完成) 1-1.取得現有職位(MAX)編號
	 * 1-2.設定就業狀態select(status) 1-3.設定職位select(position) 2.更新：員工資料(empImg未完成)
	 * 
	 */

	// 1.註冊：員工資料
	@Transactional
	@Override
	public void addEmployee(EmployeeBean employee) {
		employeeDao.addEmployee(employee);
	}

//	// 1-1.取得現有職位(MAX)編號
//	@Transactional
//	@Override
//	public Integer getCurrentPositionNumber(String positionInsert) {
//		return employeeDao.getCurrentPositionNumber(positionInsert);
//	}
	
	// 1-1.取得現有職位(MAX)編號
		@Transactional
		@Override
		public Integer getCurrentPositionNumber() {
			return employeeDao.getCurrentPositionNumber();
		}
	


	// 1-2.設定就業狀態select(status)
	@Transactional
	@Override
	public void setStatusSelect(String statusSelect) {
		System.out.println("Service cateSelect:"+statusSelect);
		employeeDao.setStatusSelect(statusSelect);;
	}

	// 1-3.設定職位select(position)??不用@override嗎
	@Override
	public void setPositionSelect(String positionSelect) {
		System.out.println("Service cateSelect:"+positionSelect);
		employeeDao.setPositionSelect(positionSelect);
		
	}

	// 2.更新：員工資料
	@Transactional
	@Override
	public void updateEmployee(EmployeeBean employee) {
		employeeDao.updateEmployee(employee);
	}

	/*
	 * 以下為搜尋 包含： 1.列出所有員工資料(完成) 2.empId-以員工ID搜尋(完成) 3.empNo-以員工編號搜尋 4.status-以就業狀態顯示
	 * 5.position-以員工職位搜尋 5-1.列出所有職位 5-1-1.列出服務生 5-1-2.列出廚師 5-1-3.列出主廚 5-1-4.列出經理
	 * 5-2.分別以職位顯示 6.searchBar-以搜尋列顯示員工表 6-1.設定搜尋列 6-2.列出搜尋列搜尋結果
	 * 
	 */

	//1.搜尋：列出所有員工資料(完成)
	@Transactional
	@Override
	public List<EmployeeBean> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	// 2.搜尋：以員工Id(PK)查詢員工(完成)-none
	@Transactional
	@Override
	public EmployeeBean getEmployeesById(int empId) {
		return employeeDao.getEmployeesById(empId);
	}

////	 3.搜尋：以員工編號empNo-none
//	@Transactional
//	@Override
//	public EmployeeBean getEmployeesByNo(String empNo) {
//		return employeeDao.getEmployeesByNo(empNo);
//	}

	// 4.status-以就業狀態顯示
	@Transactional
	@Override
	public List<EmployeeBean> getEmployeesListGetByEmployeesStatus() {
		return employeeDao.getEmployeesListGetByEmployeesStatus();
	}

	// 5.以員工職位搜尋
	// 5-1.搜尋：列出所有職位
	@Transactional
	@Override
	public List<String> getAllPositions() {
		return employeeDao.getAllPositions();
	}

	// 5-1-1.搜尋：列出服務生(測試?)
	@Transactional
	@Override
	public List<EmployeeBean> getAllPositionsTestWaiter() {
		return employeeDao.getAllPositionsTestWaiter();
	}

	// 5-1-2.搜尋：列出廚師(測試?)
	@Transactional
	@Override
	public List<EmployeeBean> getAllPositionsTestEChef() {
		return employeeDao.getAllPositionsTestEChef();
	}

	// 5-1-3.搜尋：列出主廚(測試?)
	@Transactional
	@Override
	public List<EmployeeBean> getAllPositionsTestMChef() {
		return employeeDao.getAllPositionsTestMChef();
	}

	// 5-1-4.搜尋：列出經理(測試?)
	@Transactional
	@Override
	public List<EmployeeBean> getAllPositionsTestManager() {
		return employeeDao.getAllPositionsTestManager();
	}

	// 5-2.搜尋：分別以職位顯示
	@Transactional
	@Override
	public List<EmployeeBean> getEmployeesListGetByPosition() {
		return employeeDao.getEmployeesListGetByPosition();
	}

	// 6.以搜尋列顯示員工表
	// 6-1.設定搜尋列
	@Transactional
	@Override
	public void setSearchBarString(String searchBarString) {
		System.out.println("Service searchBarString:"+searchBarString);
		employeeDao.setSearchBarString(searchBarString);
	}

	// 6-2.列出搜尋列搜尋結果
	@Transactional
	@Override
	public List<EmployeeBean> getEmployeesListGetBySearch() {
		return employeeDao.getEmployeesListGetBySearch();
	}

	/*
	 * 分頁顯示 1.取得員工總數 2.取得總頁數 3.取得員工列表頁數 4.取得每頁顯示的員工數量 5.設定每頁顯示的員工數量 6.取得現在頁數
	 * 7.設定現在頁數 8.取得搜尋後總員工數量 9.取得搜尋後總頁數 10.取得各職位總數 11.取得各職位總頁數 12.取得各員工狀態頁面
	 * 13.取得各員工狀態數量
	 */

	// 1.取得員工總數-none
//	@Override
//	public long getTotalEmployeesCounts() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	// 2.取得總頁數
	@Transactional
	@Override
	public int getTotalPages() {
		return employeeDao.getTotalPages();
	}

	// 3.取得員工列表頁數
	@Transactional
	@Override
	public List<EmployeeBean> getEmployeesListGetByPage() {
		return employeeDao.getEmployeesListGetByPage();
	}

	// 4.取得每頁顯示的員工數量
	@Transactional
	@Override
	public int getEmployeesPerPage() {
		return employeeDao.getEmployeesPerPage();
	}

	// 5.設定每頁顯示的員工數量
	@Transactional
	@Override
	public void setEmployeesPerPage(int employeesPerPage) {
		employeeDao.setEmployeesPerPage(employeesPerPage);
	}

	// 6.取得現在頁數
	@Transactional
	@Override
	public int getCurrentPageNo() {
		return employeeDao.getCurrentPageNo();
	}

	// 7.設定現在頁數
	@Transactional
	@Override
	public void setCurrentPageNo(int currentPageNo) {
		employeeDao.setCurrentPageNo(currentPageNo);
	}

//	// 8.取得搜尋後總員工數量-none
//	@Override
//	public long getTotalEmployeesCountsBySearch() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	// 9.取得搜尋後總頁數
	@Transactional
	@Override
	public int getTotalPagesBySearch() {
		return employeeDao.getTotalPagesBySearch();
	}
	
//	public static void  main (String[] args) {
//		EmployeeService service = new EmployeeServiceImpl();
//		List<EmployeeBean> a = service.getEmployeesListGetBySearch();
//		System.out.println("a:"+a);
//	}

//	// 10.取得各職位總數-none
//	@Override
//	public long getTotalEmployeesCountsByPosition() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	// 11.取得各職位總頁數
	@Transactional
	@Override
	public int getTotalPagesByPosition() {
		return employeeDao.getTotalPagesByPosition();
	}

	// 12.取得各員工狀態頁面
	@Transactional
	@Override
	public int getTotalPagesByEmployeesStatus() {
		return employeeDao.getTotalPagesByEmployeesStatus();
	}

	
//	// 13.取得各員工狀態數量-none
//	@Override
//	public long getTotalEmployeeCountsByEmployeeStatus() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
		//泰豪
		@Transactional
		@Override
		public List<AttendenceBean> getAllAttendence() {
			return employeeDao.getAllAttendence();
		}

		@Override
		public List<AttendenceBean> getAttendenceListByDate(String Date1, String Date2) {
			return employeeDao.getAttendenceListByDate(Date1, Date2);
		}
		@Transactional
		@Override
		public List<AttendenceBean> findAllAttendence() {
			return employeeDao.findAllAttendence();
		}

		
		@Override
		public EmployeeBean findByPrimaryKey(int key) {
			return employeeDao.findByPrimaryKey(key);
		}

		//dfd
		// 14.取得員工圖片
		@Transactional
		@Override
		public Blob getEmployeePicture(Blob img) {
			return employeeDao.getEmployeePicture(img);
		}



	


}
