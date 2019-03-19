package _02employee.controller;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import _00.init.util.GlobalService;
import _00model.EmployeeBean;
import _02employee.service.EmployeeService;

//這是一個提供Manager登入的@Controller
@Controller
public class EmployeeController {

//	@Autowired
//	ServletContext context;

	@Autowired
	EmployeeService employeeService;

	int currentPageNo = 1;
	int currentPageNoInit = 1;

//	Index.jsp開超連結，方便查看員工
	@RequestMapping("/empManage/empInsert")
	public String empInsert(Model model) {
		System.out.println("員工新增");

		return "empManage/empInsert";
	}

//	Index.jsp開超連結，方便查看員工
//	@RequestMapping("/empManage/showAllEmployeeList")
//	public String employeeList(Model model) {
//		System.out.println("員工列表");
//
//		return "empManage/allEmployeeList";
//	}

	/*
	 * 以下設定select選項 1.職位 2.就職狀況 3.性別
	 */

	// 設定Select：職位
	@ModelAttribute("empPositionList")
	public List<String> getEmpCategoryList() {
		List<String> empPositionList = new ArrayList<String>();
		empPositionList.add(GlobalService.Employee_CATE_waiter);
		empPositionList.add(GlobalService.Employee_CATE_EChef);
		empPositionList.add(GlobalService.Employee_CATE_MChef);
		empPositionList.add(GlobalService.Employee_CATE_manager);
		return empPositionList;
	}

	// 設定Select：就職狀況
	@ModelAttribute("empStatusList")
	public List<String> getEmpStatusList() {
		List<String> empStatusList = new ArrayList<String>();
		empStatusList.add(GlobalService.Employee_Status_In_Service);
		empStatusList.add(GlobalService.Employee_Status_Resignate);
		empStatusList.add(GlobalService.Employee_Status_Leave_of_absence);
		return empStatusList;
	}

	// 設定Select：性別
	@ModelAttribute("empGender")
	public List<String> empGenderList() {
		List<String> empGenderList = new ArrayList<String>();
		empGenderList.add(GlobalService.Employee_Gender_Male);
		empGenderList.add(GlobalService.Employee_Gender_Female);
		return empGenderList;
	}

	/*
	 * 設定select選項結束
	 */

	/*
	 * 設定搜尋頁面：1.列出全部員工 2.搜尋Bar 3.依職位列出 4.依就業狀態列出
	 */

	// 列出全部員工頁面
	@RequestMapping(value = "/empManage/allEmployeeList.action")
	public String getAllEmployeeListPage(
			@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo, Model model) {
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		employeeService.setCurrentPageNo(currentPageNoInit);

		List<EmployeeBean> employeeListGetByPage = new ArrayList<>();
		employeeListGetByPage = employeeService.getEmployeesListGetByPage();
		model.addAttribute("employeeListGetByPage", employeeListGetByPage);

		if (employeeListGetByPage.isEmpty()) {
			model.addAttribute("noEmployeeString", "查無員工");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);

			return "empManage/allEmployeeList";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.Employees_PER_PAGE);
		model.addAttribute("totalPages", employeeService.getTotalPages());

		return "empManage/allEmployeeList";
	}

	

	// 依職位列出員工頁面
	@RequestMapping(value = "/empManage/EmployeeListByPosition.action")
	public String getEmployeeListByPosition(
			@RequestParam(value = "currentPageNoBtnPosition", required = false) String currentPageNo,
			@RequestParam(value = "whichPosition", required = false) String positionSelect, Model model) {

		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		employeeService.setCurrentPageNo(currentPageNoInit);

		model.addAttribute("whichPosition", positionSelect);
		employeeService.setPositionSelect(positionSelect);

		List<EmployeeBean> employeeListGetByPosition = new ArrayList<>();
		employeeListGetByPosition = employeeService.getEmployeesListGetByPosition();
		model.addAttribute("employeeListGetByPosition", employeeListGetByPosition);

		if (employeeListGetByPosition.isEmpty()) {
			if (currentPageNoInit > 1) {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
				currentPageNoInit = currentPageNoInit - 1;
				employeeService.setCurrentPageNo(currentPageNoInit);

				employeeListGetByPosition = new ArrayList<>();
				employeeListGetByPosition = employeeService.getEmployeesListGetByPosition();
				model.addAttribute("employeeListGetByPosition", employeeListGetByPosition);

				model.addAttribute("currentPageNo", currentPageNoInit);
				model.addAttribute("currentBeginOfEmployeeNo",
						(currentPageNoInit - 1) * GlobalService.Employees_PER_PAGE);
				model.addAttribute("totalPages", employeeService.getTotalPagesByPosition());

				return "empManage/employeeListByPosition";
			}

			model.addAttribute("noEmployeeString", "查無員工");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);

			return "empManage/employeeListByPosition";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfEmployeeNo", (currentPageNoInit - 1) * GlobalService.Employees_PER_PAGE);
		model.addAttribute("totalPages", employeeService.getTotalPagesByPosition());

		return "empManage/employeeListByPosition";

	}

	// 依就職狀態列出員工頁面
	@RequestMapping(value = "/empManage/EmployeeListByEmployeeStatus.action")
	public String getEmployeeListByEmployeeStatus(

			@RequestParam(value = "currentPageNoBtnEmployeeStatus", required = false) String currentPageNo,
			@RequestParam(value = "whichStatus", required = false) String StatusSelect, Model model) {
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		employeeService.setCurrentPageNo(currentPageNoInit);

		model.addAttribute("whichStatus", StatusSelect);
		employeeService.setStatusSelect(StatusSelect);

		List<EmployeeBean> employeeListGetByEmployeeStatus = new ArrayList<>();
		employeeListGetByEmployeeStatus = employeeService.getEmployeesListGetByEmployeesStatus();
		model.addAttribute("employeeListGetByEmployeeStatus", employeeListGetByEmployeeStatus);

		if (employeeListGetByEmployeeStatus.isEmpty()) {
			if (currentPageNoInit > 1) {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
				currentPageNoInit = currentPageNoInit - 1;
				employeeService.setCurrentPageNo(currentPageNoInit);

				employeeListGetByEmployeeStatus = new ArrayList<>();
				employeeListGetByEmployeeStatus = employeeService.getEmployeesListGetByEmployeesStatus();
				model.addAttribute("employeeListGetByEmployeeStatus", employeeListGetByEmployeeStatus);
				model.addAttribute("currentPageNo", currentPageNoInit);
				model.addAttribute("currentBeginOfEmployeeNo",
						(currentPageNoInit - 1) * GlobalService.Employees_PER_PAGE);
				model.addAttribute("totalPages", employeeService.getTotalPagesByEmployeesStatus());

				return "empManage/employeeListByEmployeeStatus";
			}
			model.addAttribute("noEmployeeString", "查無員工");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);

			return "empManage/employeeListByEmployeeStatus";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfEmployeeNo", (currentPageNoInit - 1) * GlobalService.Employees_PER_PAGE);
		model.addAttribute("totalPages", employeeService.getTotalPagesByEmployeesStatus());

		return "empManage/employeeListByEmployeeStatus";
	}
	
	/*
	 * 搜尋Bar搜尋
	 */

	@RequestMapping(value = "/empManage/EmployeeListBySearch.action", method = RequestMethod.GET)
	public String getEmployeeListBySearch(
			@RequestParam(value = "currentPageNoBtnSearch", required = false) String currentPageNo,
			@RequestParam(value = "searchBar", required = false) String searchBarString, Model model) {
		model.addAttribute("searchBarString", searchBarString);

		if (searchBarString.isEmpty()) {
			model.addAttribute("noSearchBarString", "請輸入查詢條件");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);
			return "empManage/employeeListBySearch";
		}

		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		employeeService.setCurrentPageNo(currentPageNoInit);
		employeeService.setSearchBarString(searchBarString);
		List<EmployeeBean> employeeListBySearch = new ArrayList<>();
		employeeListBySearch = employeeService.getEmployeesListGetBySearch();
		model.addAttribute("employeeListBySearch", employeeListBySearch);

		if (employeeListBySearch.isEmpty()) {
			if (currentPageNoInit > 1) {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
				currentPageNoInit = currentPageNoInit - 1;
				employeeService.setCurrentPageNo(currentPageNoInit);

				employeeListBySearch = new ArrayList<>();
				employeeListBySearch = employeeService.getEmployeesListGetBySearch();
				model.addAttribute("employeeListBySearch", employeeListBySearch);
				model.addAttribute("currentPageNo", currentPageNoInit);
				model.addAttribute("currentBeginOfEmployeeNo",
						(currentPageNoInit - 1) * GlobalService.Employees_PER_PAGE);
				model.addAttribute("totalPages", employeeService.getTotalPagesBySearch());

				return "empManage/employeeListBySearch";
			}
			model.addAttribute("noEmployeeString", "查無員工");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);

			return "empManage/employeeListBySearch";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfEmployeeNo", (currentPageNoInit - 1) * GlobalService.Employees_PER_PAGE);
		model.addAttribute("totalPages", employeeService.getTotalPagesBySearch());

		return "empManage/employeeListBySearch";
	}

	/*
	 * 設定搜尋頁面結束
	 */

	/*
	 * 員工資料編修：1.註冊 2.更新
	 */
	// 註冊員工資料1
	@RequestMapping(value = "/empManage/empInsert", method = RequestMethod.GET)
	public String getAddNewEmpForm(Model model) {
		EmployeeBean employeeBean = new EmployeeBean();
		model.addAttribute("employeeBean", employeeBean);
		return "/empManage/empInsert";
	}

	// 註冊員工資料2
	@RequestMapping(value = "/empManage/empInsert", method = RequestMethod.POST)
	public String processAddNewEmpForm(@ModelAttribute("employeeBean") EmployeeBean employeeBean, Model model,
			BindingResult result, HttpServletRequest req) {

		// 錯誤訊息
		Map<String, String> errors = new HashMap<>();
		model.addAttribute("modelErrors", errors);

		// 錯誤訊息：員工姓名
		String employeeNameInsert = employeeBean.getEmpName();
		if (employeeNameInsert == null || employeeNameInsert.length() == 0) {
			errors.put("errorOfEmployeeName", "請輸入員工姓名");
		}

		// 錯誤訊息：員工電話
		String employeeTelInsert = employeeBean.getTel();
		if (employeeTelInsert == null || employeeTelInsert.length() == 0) {
			errors.put("errorOfEmployeeTel", "請輸入員工電話");
		}

		// 錯誤訊息：員工地址
		String employeeAddrInsert = employeeBean.getAddr();
		if (employeeAddrInsert == null || employeeAddrInsert.length() == 0) {
			errors.put("errorOfEmployeeAddr", "請輸入員工地址");
		}

		// 錯誤訊息：員工職位(選項)
		String positionInsert = employeeBean.getPosition();
		if (positionInsert == null || positionInsert.equals("-1")) {
			errors.put("errorOfPosition", "請選擇職位");
		}

		// 錯誤訊息：員工性別(選項)
		String genderInsert = employeeBean.getGender();
		if (genderInsert == null || genderInsert.equals("-1")) {
			errors.put("errorOfGender", "請選擇性別");
		}

		// 錯誤訊息：員工狀態(選項)
		String empStatusInsert = employeeBean.getStatus();
		if (empStatusInsert == null || empStatusInsert.equals("-1")) {
			errors.put("errorOfEmpStatus", "請選擇就職狀況");
		}

		// 錯誤訊息：員工照片(上傳)
//		Blob empPictureInsert = employeeBean.getImg();
//		if (empPictureInsert == null) {
//			errors.put("errorOfEmpPicture", "請上傳照片");
//		}

		// 若有錯誤訊息則顯示在頁面上
		if (errors != null && !errors.isEmpty()) {
			return "empManage/empInsert";
		}

		String position = employeeBean.getPosition();
		Integer employeeNoInsert = employeeService.getCurrentPositionNumber(position);
		if (employeeNoInsert == null) {
			switch (position) {
			case GlobalService.Employee_CATE_waiter:
				employeeBean.setEmpNo(101);
				break;
			case GlobalService.Employee_CATE_EChef:
				employeeBean.setEmpNo(201);
				break;
			case GlobalService.Employee_CATE_MChef:
				employeeBean.setEmpNo(301);
				break;
			case GlobalService.Employee_CATE_manager:
				employeeBean.setEmpNo(401);
				break;
			}
		} else {
			employeeBean.setEmpNo(employeeNoInsert + 1);
		}

		// 上傳照片
		MultipartFile empImg = employeeBean.getEmpImg();

//			 建立Blob物件，交由Hibernate 寫入資料庫
		if (empImg != null && !empImg.isEmpty()) {
			try {
				byte[] b = empImg.getBytes();
				Blob blob = new SerialBlob(b);
				employeeBean.setImg(blob);
				;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
			}
		}

		employeeService.addEmployee(employeeBean);
		return "redirect:/empManage/allEmployeeList.action";
	}

	// 取得圖片、檔案
	@RequestMapping(value = "/getPicture/{empId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse res, @PathVariable Integer empId) {
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		int len = 0;
		EmployeeBean employeeBean = employeeService.getEmployeesById(empId);
		if (employeeBean != null) {
			Blob blob = employeeBean.getImg();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);

				} catch (SQLException e) {
					throw new RuntimeException("EmployeeController的getPicture()發生SQLException:" + e.getMessage());
				}
			}

			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		}
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	/*
	 * 註冊員工資料結束
	 */

	/*
	 * 更新員工資料
	 */

	
	// 更新員工資料
	@RequestMapping(value = "/empManage/allEmployeeListEdit.action/{key}", method = RequestMethod.POST)
	public String updateEmployee(@PathVariable Integer key,
			@RequestParam(value = "empIdEdit", required = false) String empIdEdit,
			@RequestParam(value = "empNoEdit", required = false) String empNoEdit,
			@RequestParam(value = "empNameEdit", required = false) String empNameEdit,
			@RequestParam(value = "positionEdit", required = false) String positionEdit,
			@RequestParam(value = "genderEdit", required = false) String genderEdit,
			@RequestParam(value = "telEdit", required = false) String telEdit,
			@RequestParam(value = "addrEdit", required = false) String addrEdit,
			@RequestParam(value = "statusEdit", required = false) String statusEdit,
			@RequestParam(value = "remarkEdit", required = false) String remarkEdit,
			@RequestParam(value = "imgEdit", required = false) Blob imgEdit,
			@RequestParam(value = "empImgEdit", required = false) MultipartFile empImgEdit,
			@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo,EmployeeBean employeeBean) {
		int empIdEditParse = Integer.parseInt(empIdEdit.trim());
		int empNoEditParse = Integer.parseInt(empNoEdit.trim());
	
		// 如果imgEdit是空值，就直接將empimgEdit的值塞入imgEdit中，要轉型。
//		if(empImgEdit == null) {
//			empImgEdit =;
//		}
		
		// 上傳照片
				MultipartFile empImg = empImgEdit;
				if(empImg == null) {
					
//					 建立Blob物件，交由Hibernate 寫入資料庫
				}else if (empImg != null && !empImg.isEmpty()) {
					try {
						byte[] b = empImg.getBytes();
						Blob blob = new SerialBlob(b);
						imgEdit = blob;
						;
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
					}
				}
				
		
		EmployeeBean employeeBean1 = new EmployeeBean(empIdEditParse,empNoEditParse,empNameEdit,positionEdit,genderEdit,telEdit,addrEdit,statusEdit,remarkEdit,imgEdit,empImgEdit);
		employeeService.updateEmployee(employeeBean1);
		
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		employeeService.setCurrentPageNo(currentPageNoInit);
		
		return "redirect:/empManager/allEmployeeList.action?currentPageNoBtn=" + currentPageNoInit;
	}

	//更新員工by就業狀態
	@RequestMapping(value = "/empManage/employeeListByEmployeeStatusEdit.action/{key}", method = RequestMethod.POST)
	public String updateMenuByProductStatus(@PathVariable Integer key,
			@RequestParam(value = "empIdEdit", required = false) String empIdEdit,
			@RequestParam(value = "empNoEdit", required = false) String empNoEdit,
			@RequestParam(value = "empNameEdit", required = false) String empNameEdit,
			@RequestParam(value = "positionEdit", required = false) String positionEdit,
			@RequestParam(value = "genderEdit", required = false) String genderEdit,
			@RequestParam(value = "telEdit", required = false) String telEdit,
			@RequestParam(value = "addrEdit", required = false) String addrEdit,
			@RequestParam(value = "statusEdit", required = false) String statusEdit,
			@RequestParam(value = "remarkEdit", required = false) String remarkEdit,
			@RequestParam(value = "imgEdit", required = false) Blob imgEdit,
			@RequestParam(value = "empImgEdit", required = false) MultipartFile empImgEdit,
			@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo,
			@RequestParam(value = "whichStatus", required = false) String statusSelect,Model model) {
		int empIdEditParse = Integer.parseInt(empIdEdit.trim());
		int empNoEditParse = Integer.parseInt(empNoEdit.trim());
		
		
		
		EmployeeBean employeeBean = new EmployeeBean(empIdEditParse,empNoEditParse,empNameEdit,positionEdit,genderEdit,telEdit,addrEdit,statusEdit,remarkEdit,imgEdit,empImgEdit);
		employeeService.updateEmployee(employeeBean);
		
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}
		employeeService.setCurrentPageNo(currentPageNoInit);

		model.addAttribute("whichStatus", statusSelect);

		return "redirect:/empManage/EmployeeListByEmployeeStatus.action?currentPageNoBtnEmployeeStatus="
				+ currentPageNoInit;
	}

	// 更新員工by職位
		@RequestMapping(value = "/empManage/employeeListByEmployeePositionEdit.action/{key}", method = RequestMethod.POST)
		public String updateMenuByEmployeePosition(@PathVariable Integer key,
				@RequestParam(value = "empIdEdit", required = false) String empIdEdit,
				@RequestParam(value = "empNoEdit", required = false) String empNoEdit,
				@RequestParam(value = "empNameEdit", required = false) String empNameEdit,
				@RequestParam(value = "positionEdit", required = false) String positionEdit,
				@RequestParam(value = "genderEdit", required = false) String genderEdit,
				@RequestParam(value = "telEdit", required = false) String telEdit,
				@RequestParam(value = "addrEdit", required = false) String addrEdit,
				@RequestParam(value = "statusEdit", required = false) String statusEdit,
				@RequestParam(value = "remarkEdit", required = false) String remarkEdit,
				@RequestParam(value = "imgEdit", required = false) Blob imgEdit,
				@RequestParam(value = "empImgEdit", required = false) MultipartFile empImgEdit,
				@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo,
				@RequestParam(value = "whichPosition", required = false) String positionSelect,Model model) {
			int empIdEditParse = Integer.parseInt(empIdEdit.trim());
			int empNoEditParse = Integer.parseInt(empNoEdit.trim());
			
			EmployeeBean employeeBean = new EmployeeBean(empIdEditParse,empNoEditParse,empNameEdit,positionEdit,genderEdit,telEdit,addrEdit,statusEdit,remarkEdit,imgEdit,empImgEdit);
			employeeService.updateEmployee(employeeBean);
			
			if (currentPageNo == null) {
				currentPageNoInit = 1;
			} else {
				try {
					currentPageNoInit = Integer.parseInt(currentPageNo.trim());
				} catch (NumberFormatException e) {
					currentPageNoInit = 1;
				}
			}
			employeeService.setCurrentPageNo(currentPageNoInit);

			model.addAttribute("whichPosition", positionSelect);

			return "redirect:/empManage/EmployeeListByEmployeePosition.action?currentPageNoBtnEmployeePosition="
					+ currentPageNoInit;
		}
		

	// 更新員工by searchBar
				@RequestMapping(value = "/empManage/employeeListByEmployeeSearchEdit.action/{key}", method = RequestMethod.POST)
				public String updateMenuByEmployeeSearch(@PathVariable Integer key,
						@RequestParam(value = "empIdEdit", required = false) String empIdEdit,
						@RequestParam(value = "empNoEdit", required = false) String empNoEdit,
						@RequestParam(value = "empNameEdit", required = false) String empNameEdit,
						@RequestParam(value = "positionEdit", required = false) String positionEdit,
						@RequestParam(value = "genderEdit", required = false) String genderEdit,
						@RequestParam(value = "telEdit", required = false) String telEdit,
						@RequestParam(value = "addrEdit", required = false) String addrEdit,
						@RequestParam(value = "statusEdit", required = false) String statusEdit,
						@RequestParam(value = "remarkEdit", required = false) String remarkEdit,
						@RequestParam(value = "imgEdit", required = false) Blob imgEdit,
						@RequestParam(value = "empImgEdit", required = false) MultipartFile empImgEdit,
						@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo,
						@RequestParam(value = "searchBar", required = false) String searchBarString,Model model) {
					int empIdEditParse = Integer.parseInt(empIdEdit.trim());
					int empNoEditParse = Integer.parseInt(empNoEdit.trim());
					
					EmployeeBean employeeBean = new EmployeeBean(empIdEditParse,empNoEditParse,empNameEdit,positionEdit,genderEdit,telEdit,addrEdit,statusEdit,remarkEdit,imgEdit,empImgEdit);
					employeeService.updateEmployee(employeeBean);
					
					if (currentPageNo == null) {
						currentPageNoInit = 1;
					} else {
						try {
							currentPageNoInit = Integer.parseInt(currentPageNo.trim());
						} catch (NumberFormatException e) {
							currentPageNoInit = 1;
						}
					}
					employeeService.setCurrentPageNo(currentPageNoInit);

					model.addAttribute("searchBar", searchBarString);

					return "redirect:/empManage/EmployeeListBySearch.action?currentPageNoBtnSearch="+ currentPageNoInit;
				}
}

	// 列出所有員工
//	@RequestMapping("/empManage/empQuery")
//	public String list(Model model) {
//		List<EmployeeBean> list = employeeService.getAllEmployees();
//		model.addAttribute("empQuery", list);
//		return "empManage/allEmployeeList";
//	}}

//		//點入單筆員工的資料 empNo版
//		@RequestMapping("/empManage/empQueryFor1")
//		public String getEmployeesByNo(@RequestParam("empNo") String empNo ,Model model) {
//			model.addAttribute("empQueryFor1", employeeService.getEmployeesByNo(empNo));
//			return "empManage/empQueryFor1";
//		}

//	// 點入單筆員工的資料 ID版
//	@RequestMapping("/empManage/empQueryFor1")
//	public String getEmployeesById(@RequestParam("empId") Integer empId, Model model) {
//
//		model.addAttribute("empQueryFor1", employeeService.getEmployeesById(empId));
//		return "empManage/empQueryFor1";
//	}
//
//	// 從單筆員工資料 ID版 點入編輯單筆員工資料頁面
//	@RequestMapping(value = "/empManage/empEdit", method = RequestMethod.GET)
//	public String getEmployeeEditPage(@RequestParam("empId") Integer empId, Model model) {
//		EmployeeBean employeeBean = employeeService.getEmployeesById(empId);
//		model.addAttribute("empEdit", employeeBean);
//		System.out.println(employeeBean);
//		return "empManage/empEdit";
//	}
//
//	// 從單筆員工資料 ID版 點入編輯單筆員工資料頁面後，送出更新資料
//	@RequestMapping(value = "/empManage/empEdit", method = RequestMethod.POST)
//	public String updateEmployee(@RequestParam("empId") Integer empId,
//			@ModelAttribute("empEdit") EmployeeBean employeeBean, BindingResult result, HttpServletRequest req) {
//
//		MultipartFile empImg = employeeBean.getEmpImg();
//		// 建立Blob物件，交由Hibernate 寫入資料庫
//		if (empImg != null && !empImg.isEmpty()) {
//			try {
//				byte[] b = empImg.getBytes();
//				Blob blob = new SerialBlob(b);
//				employeeBean.setImg(blob);
//				;
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
//			}
//		}
//
//		EmployeeBean test = new EmployeeBean();
//		employeeService.updateEmployee(test);
//
//		return "redirect:/empManage/empQueryFor1";
//	}
