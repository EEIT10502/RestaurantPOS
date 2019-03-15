package _02employee.controller;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import org.springframework.util.StringUtils;
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

	@Autowired
	ServletContext context;

	@Autowired
	EmployeeService employeeService;

//	Index.jsp開超連結，方便查看員工
	@RequestMapping("/empManage/empInsert")
	public String empInsert(Model model) {
		System.out.println("員工新增");

		return "empManage/empInsert";
	}


	// 新增員工資料1
	@RequestMapping(value = "/empManage/empInsert", method = RequestMethod.GET)
	public String getAddNewEmpForm(Model model) {
		EmployeeBean employeeBean = new EmployeeBean();
		model.addAttribute("employeeBean", employeeBean);
		return "/empManage/empInsert";
	}

	// 新增員工資料2
	@RequestMapping(value = "/empManage/empInsert", method = RequestMethod.POST)
	public String processAddNewEmpForm(@ModelAttribute("employeeBean") EmployeeBean employeeBean, Model model,BindingResult result,
			HttpServletRequest req) {
		
		
		//錯誤訊息
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

		
		//上傳照片
		MultipartFile empImg = employeeBean.getEmpImg();

//		 建立Blob物件，交由Hibernate 寫入資料庫
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
		
		//若有錯誤訊息則顯示在頁面上
		if (errors != null && !errors.isEmpty()) {
			return "empManage/empInsert";
		}
		

		employeeService.addEmployee(employeeBean);
		return "redirect:/empManage/empQuery";
	}
	

//	// 取得圖片、檔案
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
				}}
			
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		}
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
		}


	// 列出所有員工
	@RequestMapping("/empManage/empQuery")
	public String list(Model model) {
		List<EmployeeBean> list = employeeService.getAllEmployees();
		model.addAttribute("empQuery", list);
		return "empManage/empQuery";
	}

//		//點入單筆員工的資料 empNo版
//		@RequestMapping("/empManage/empQueryFor1")
//		public String getEmployeesByNo(@RequestParam("empNo") String empNo ,Model model) {
//			model.addAttribute("empQueryFor1", employeeService.getEmployeesByNo(empNo));
//			return "empManage/empQueryFor1";
//		}
	
//	//列出職員類別
	@ModelAttribute("empCateList")
	public List<String> getEmpCategoryList() {
		List<String> empCateList = new ArrayList<String>();
		empCateList.add(GlobalService.Employee_CATE_waiter);
		empCateList.add(GlobalService.Employee_CATE_EChef);
		empCateList.add(GlobalService.Employee_CATE_MChef);
		empCateList.add(GlobalService.Employee_CATE_manager);
		return empCateList;
	}
	
	
	//列出員工就職狀況
	@ModelAttribute("empStatusList")
	public List<String> getEmpStatusList() {
		List<String> empStatusList = new ArrayList<String>();
		empStatusList.add(GlobalService.Employee_Status_In_Service);
		empStatusList.add(GlobalService.Employee_Status_Resignate);
		empStatusList.add(GlobalService.Employee_Status_Leave_of_absence);
		return empStatusList;
	}

	// 點入單筆員工的資料 ID版
	@RequestMapping("/empManage/empQueryFor1")
	public String getEmployeesById(@RequestParam("empId") Integer empId, Model model) {
		model.addAttribute("empQueryFor1", employeeService.getEmployeesById(empId));
		return "empManage/empQueryFor1";
	}

	// 從單筆員工資料 ID版 點入編輯單筆員工資料頁面
	@RequestMapping(value = "/empManage/empEdit", method = RequestMethod.GET)
	public String getEmployeeEditPage(@RequestParam("empId") Integer empId, Model model) {
		EmployeeBean employeeBean = employeeService.getEmployeesById(empId);
		model.addAttribute("empEdit", employeeBean);
		System.out.println(employeeBean);
		return "empManage/empEdit";
	}
	
	// 從單筆員工資料 ID版 點入編輯單筆員工資料頁面後，送出更新資料
	@RequestMapping(value = "/empManage/empEdit", method = RequestMethod.POST)
	public String updateEmployee(@RequestParam("empId") Integer empId,
			@ModelAttribute("empEdit") EmployeeBean employeeBean, BindingResult result,
			HttpServletRequest req) {
		MultipartFile empImg = employeeBean.getEmpImg();
		// 建立Blob物件，交由Hibernate 寫入資料庫
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
		
		EmployeeBean employeeBean = new EmployeeBean();
		employeeService.updateEmployee(employeeBean);
		
		return "redirect:/empManage/empQueryFor1";
	}
	

	



}
