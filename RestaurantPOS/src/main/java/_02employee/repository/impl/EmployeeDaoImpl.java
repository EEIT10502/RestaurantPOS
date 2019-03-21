package _02employee.repository.impl;


import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00.init.util.GlobalService;
import _00model.AttendenceBean;
import _00model.EmployeeBean;
import _02employee.repository.EmployeeDao;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	java.util.Date uDate1 = null;
	java.util.Date uDate2 = null;
	java.sql.Date beginDate = null;
	java.sql.Date endDate = null;
	
	@Autowired
	SessionFactory factory;

	/**
	 * 以下為資料編輯，包含： 
	 * 1.註冊：員工資料(empNo自動生成未完成) 
		 * 1-1.取得現有職位(MAX)編號
		 * 1-2.設定就業狀態select(status) 
		 * 1-3.設定職位select(position) 
	 * 2.更新：員工資料(empImg未完成)
	 */

	// 1.註冊：員工資料
	@Override
	public void addEmployee(EmployeeBean employee) {
		Session session = factory.getCurrentSession();
		session.save(employee);
	}

	// 1-1.取得現有職位(MAX)編號
//	@Override
//	public Integer getCurrentPositionNumber(String positionInsert) {
//		String hql = "SELECT MAX(empNo) FROM EmployeeBean Where position = ?0";
//		Session session = factory.getCurrentSession();
//		Integer CurrentCategoryNumber = (Integer) session.createQuery(hql).setParameter(0, positionInsert)
//				.uniqueResult();
//		return CurrentCategoryNumber;
//	}
	
	@Override
	public Integer getCurrentPositionNumber() {
		String hql = "SELECT MAX(empNo) FROM EmployeeBean";
		Session session = factory.getCurrentSession();
		Integer CurrentCategoryNumber = (Integer) session.createQuery(hql)
				.uniqueResult();
		return CurrentCategoryNumber;
	}
	


	// 1-2.設定就業狀態select(status)
	private String statusSelect = null;

	public String getStatusSelect() {
		return statusSelect;
	}

//	
	public void setStatusSelect(String statusSelect) {
		this.statusSelect = statusSelect;

	}
	
//	1-3.設定職位select(position)??不用@override嗎
	private String positionSelect = null;

	public String getPositionSelect() {
		return positionSelect;
	}

	public void setPositionSelect(String positionSelect) {
		this.positionSelect = positionSelect;
	}

	// 2.更新：員工資訊
	@Override
	public void updateEmployee(EmployeeBean employee) {
		Session session = factory.getCurrentSession();
		session.update(employee);
	}
	
	
//	//透過員工NO取得單筆詳細資訊
//	@Override
//	public EmployeeBean getEmployeesByNo(String empNo) {
//		Session session = factory.getCurrentSession();
//		EmployeeBean employee = session.get(EmployeeBean.class, empNo);
//		return employee;
//	}

//	//透過員工ID取得單筆詳細資訊
//		@Override
//		public EmployeeBean getEmployeesById(int empId) {
//			Session session = factory.getCurrentSession();
//			EmployeeBean employee = session.get(EmployeeBean.class, empId);
//			return employee;
//		}
//
//		//更新員工資訊
//		@Override
//		public void updateEmployee(EmployeeBean employee) {
//			Session session = factory.getCurrentSession();
//			session.update(employee);
//			
//		}
//
//		@Override
//		public ManagerBean checkIDPassword(String mAccount, String mPwd) {
//			// TODO Auto-generated method stub
//			return null;
//		}

		@SuppressWarnings("unchecked")
		@Override
		public List<AttendenceBean> getAllAttendence() {
			String hql = "FROM AttendenceBean";
		    Session session = null;
		    List<AttendenceBean> list = new ArrayList<>();
		    session = factory.getCurrentSession();
		    list = session.createQuery(hql).getResultList();
		    return list;
			
		}
		@Override
		public void stringToDate(String Date1, String Date2) {
			String tDate1 = Date1 + " 00:00:00";
			String tDate2 = Date2 + " 23:59:59";
			SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// to util.Date
			try {
				uDate1 = fDate.parse(tDate1);
				uDate2 = fDate.parse(tDate2);
				beginDate = new java.sql.Date(uDate1.getTime());
				endDate = new java.sql.Date(uDate2.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<AttendenceBean> getAttendenceListByDate(String Date1, String Date2) {
			stringToDate(Date1, Date2);
			String hql = "FROM AttendenceBean a WHERE a.date>=:beginDate and a.date<=:endDate";
			Session session = factory.getCurrentSession();
			List<AttendenceBean> EmpNameListByDate = session.createQuery(hql).setParameter("beginDate", beginDate)
					.setParameter("endDate", endDate).getResultList();
			return EmpNameListByDate;
		}

		@Override
		public EmployeeBean findByPrimaryKey(int key) {
			Session session = factory.getCurrentSession();
			EmployeeBean employeeBean = session.get(EmployeeBean.class, key);
			return employeeBean;
		}

		

		
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<EmployeeBean> getEmployeesByPosition(String position) {
//		List<EmployeeBean> list = null;
//		String hql = "FROM EmployeeBean e WHERE e.position = :position";
//		Session session = factory.getCurrentSession();
//		list = session.createQuery(hql).setParameter("position", position).list();
//		return list;
//	}


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

	//3.搜尋：以員工編號empNo
	@Override
	public EmployeeBean getEmployeesByNo(String empNo) {
		Session session = factory.getCurrentSession();
		EmployeeBean employee = session.get(EmployeeBean.class, empNo);
		return employee;
	}
	
	//4.status-以就業狀態顯示
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getEmployeesListGetByEmployeesStatus() {
		System.out.println("DAO statusSelect:"+statusSelect);
		String hql = "From EmployeeBean where status = ?0 Order By empNo";
//		String hql = "From MenuBean where productName like '%"+searchBarString+"%' Order By productNo";
		int startEmployeeNo = (currentPageNo - 1) * employeesPerPage;
		Session session = factory.getCurrentSession();
		List<EmployeeBean> EmployeeListGetByEmployeeStatus = new ArrayList<>();
		EmployeeListGetByEmployeeStatus = session.createQuery(hql)
				.setParameter(0, statusSelect)
				.setFirstResult(startEmployeeNo)
				.setMaxResults(employeesPerPage)
				.getResultList();
		return EmployeeListGetByEmployeeStatus;
	}

	// 5-1.搜尋：列出所有職位
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllPositions() {
		List<String> positionList = null;
		String hql = "SELECT DISTINCT position FROM EmployeeBean";
		Session session = factory.getCurrentSession();
		positionList = session.createQuery(hql).list();
		return positionList;
	}

	// 5-1-1.列出服務生
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getAllPositionsTestWaiter() {
		String hql = "From EmployeeBean Where position = ?0 Order By empNo";
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
		String hql = "From EmployeeBean Where position = ?0 Order By empNo";
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
		String hql = "From EmployeeBean Where position = ?0 Order By empNo";
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
		String hql = "From EmployeeBean Where position = ?0 Order By empNo";
		Session session = factory.getCurrentSession();
		List<EmployeeBean> llPositionsTestManager = new ArrayList<>();
		llPositionsTestManager = session.createQuery(hql).setParameter(0, GlobalService.Employee_CATE_manager)
				.getResultList();
		return llPositionsTestManager;
	}



	// 5-2.搜尋：分別以職位顯示
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getEmployeesListGetByPosition() {
		System.out.println("DAO positionSelect:" + positionSelect);
		String hql = "From EmployeeBean where position = ?0 Order By empNo";
//		String hql = "From MenuBean where productName like '%"+searchBarString+"%' Order By productNo";
		int startEmployeeNo = (currentPageNo - 1) * employeesPerPage;
		Session session = factory.getCurrentSession();
		List<EmployeeBean> EmployeeListGetByPosition = new ArrayList<>();
		EmployeeListGetByPosition = session.createQuery(hql)
				.setParameter(0, positionSelect)
				.setFirstResult(startEmployeeNo)
				.setMaxResults(employeesPerPage)
				.getResultList();
		return EmployeeListGetByPosition;
	}

	
	// 6.以搜尋列顯示員工列表
	private String searchBarString = null;

	// 6-1.設定搜尋列
	public String getSearchBarString() {
		return searchBarString;
	}

	@Override
	public void setSearchBarString(String searchBarString) {
		this.searchBarString = searchBarString;
	}

	// 6-2.列出搜尋列搜尋結果
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getEmployeesListGetBySearch() {
		System.out.println("DAO searchBarString:" + searchBarString);
		String hql = "From EmployeeBean where empName like :key Order By empId";
//		String hql = "From MenuBean where productName like '%"+searchBarString+"%' Order By productNo";
		int startEmployeeNo = (currentPageNo - 1) * employeesPerPage;
		Session session = factory.getCurrentSession();
		List<EmployeeBean> EmployeeListGetBySearch = new ArrayList<>();
		EmployeeListGetBySearch = session.createQuery(hql).setParameter("key", '%' + searchBarString + '%')
				.setFirstResult(startEmployeeNo).setMaxResults(employeesPerPage).getResultList();
		return EmployeeListGetBySearch;
	}

	/*
	 * 分頁顯示 1.取得員工總數 2.取得總頁數 3.取得員工列表頁數 4.取得每頁顯示的員工數量 5.設定每頁顯示的員工數量 6.取得現在頁數
	 * 7.設定現在頁數 8.取得搜尋後總員工數量 9.取得搜尋後總頁數 10.取得各職位總數 11.取得各職位總頁數 12.取得各員工狀態頁面
	 * 13.取得各員工狀態數量
	 */

	// 1.取得員工總數
	@SuppressWarnings("unchecked")
	@Override
	public long getTotalEmployeesCounts() { // 所有員工的個數
		long count = 0;
		String hql = "SELECT count(*) FROM EmployeeBean";
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql).list();
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	private int totalPages = -1;

	// 2.取得總頁數
	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getTotalEmployeesCounts() / (double) employeesPerPage));

		return totalPages;
	}

	private int employeesPerPage = GlobalService.Employees_PER_PAGE;
	
	//3.取得員工列表頁數
		@SuppressWarnings("unchecked")
		@Override
		public List<EmployeeBean> getEmployeesListGetByPage() {
			String hql = "From EmployeeBean Order By empId";
			int startEmployeeNo = (currentPageNo - 1) * employeesPerPage;
			Session session = factory.getCurrentSession();
			List<EmployeeBean> EmployeesListGetByPage = new ArrayList<>();
			EmployeesListGetByPage = session.createQuery(hql).setFirstResult(startEmployeeNo).setMaxResults(employeesPerPage)
					.getResultList();
			return EmployeesListGetByPage;
		}

	// 4.取得每頁顯示的員工數量
	@Override
	public int getEmployeesPerPage() {
		return employeesPerPage;
	}

	// 5.設定每頁顯示的員工數量
	@Override
	public void setEmployeesPerPage(int employeesPerPage) {
		this.employeesPerPage = employeesPerPage;
	}

	private int currentPageNo = 1;

	// 6.取得現在頁數
	@Override
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	// 7.設定現在頁數
	@Override
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	// 8.取得搜尋後總員工數量
	@SuppressWarnings("unchecked")
	@Override
	public long getTotalEmployeesCountsBySearch() {// 所有產品的個數
		long count = 0;
		String hql = "SELECT count(*) FROM EmployeeBean where empName like :key";
//			String hql = "SELECT count(*) FROM MenuBean where productName like '%"+searchBarString+"%'";
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql).setParameter("key", '%' + searchBarString + '%').list();
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	
	private int totalPagesBySearch = -1;

	//9.取得搜尋後總頁數
	@Override
	public int getTotalPagesBySearch() {
		// 注意下一列敘述的每一個型態轉換
		totalPagesBySearch = (int) (Math.ceil(getTotalEmployeesCountsBySearch() / (double) employeesPerPage));
		return totalPagesBySearch;
	}

	// 10.取得各職位總數
	@SuppressWarnings("unchecked")
	@Override
	public long getTotalEmployeesCountsByPosition() {
		long count = 0;
		String hql = "SELECT count(*) FROM EmployeeBean where position = ?0";
//		String hql = "SELECT count(*) FROM MenuBean where productName like '%"+searchBarString+"%'";
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql).setParameter(0, positionSelect).list();
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	private int totalPagesByPosition = -1;

	// 11.取得各職位總頁數
	@Override
	public int getTotalPagesByPosition() {
		// 注意下一列敘述的每一個型態轉換
		totalPagesByPosition = (int) (Math.ceil(getTotalEmployeesCountsByPosition() / (double) employeesPerPage));
		return totalPagesByPosition;
	}

	private int totalPagesByEmployeeStatus = -1;

	// 12.取得各員工狀態頁面
	@Override
	public int getTotalPagesByEmployeesStatus() {
		// 注意下一列敘述的每一個型態轉換
		totalPagesByEmployeeStatus = (int) (Math
				.ceil(getTotalEmployeeCountsByEmployeeStatus() / (double) employeesPerPage));
		return totalPagesByEmployeeStatus;
	}

	// 13.取得各員工狀態數量
	@SuppressWarnings("unchecked")
	@Override
	public long getTotalEmployeeCountsByEmployeeStatus() {
		long count = 0;
		String hql = "SELECT count(*) FROM EmployeeBean where status = ?0";
//		String hql = "SELECT count(*) FROM MenuBean where productName like '%"+searchBarString+"%'";
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql).setParameter(0, statusSelect).list();
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	
//==========================================================================test排班_開始
		@SuppressWarnings("unchecked")
		public List<String> getAllEmployeesName() {
			  String hql = "SELECT empName FROM EmployeeBean";
			    Session session = null;
			    List<String> list = new ArrayList<>();
			    session = factory.getCurrentSession();
			    list = session.createQuery(hql).getResultList();
			    return list;
		}
//==========================================================================test排班_結束

		// 14.取得員工圖片
		@Override
		public Blob getEmployeePicture(Blob img) {
			String hql = "SELECT img FROM EmployeeBean where empName like :key";
			Session session = factory.getCurrentSession();
			Blob testP = (Blob) session.createQuery(hql).setParameter("key", img);
			return testP;
		}
		
	
	
	}
	
	

