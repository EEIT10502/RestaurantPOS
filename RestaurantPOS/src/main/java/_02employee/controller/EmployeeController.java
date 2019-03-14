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
		
		// 員工姓名
		String employeeNameInsert = employeeBean.getEmpName();
		if (employeeNameInsert == null || employeeNameInsert.length() == 0) {
			errors.put("errorOfEmployeeName", "請輸入員工姓名");
		}
		
		// 員工電話
		String employeeTelInsert = employeeBean.getTel();
		if (employeeTelInsert == null || employeeTelInsert.length() == 0) {
			errors.put("errorOfEmployeeTel", "請輸入員工電話");
		}
//
//		// 員工地址
		String employeeAddrInsert = employeeBean.getAddr();
		if (employeeAddrInsert == null || employeeAddrInsert.length() == 0) {
			errors.put("errorOfEmployeeAddr", "請輸入員工地址");
		}
//
//		// 員工職位(選項)
		String positionInsert = employeeBean.getPosition();
		if (positionInsert == null || positionInsert.equals("-1")) {
			errors.put("errorOfPosition", "請選擇職位");
		}
//
//		// 員工性別(選項)
		String genderInsert = employeeBean.getGender();
		if (genderInsert == null || genderInsert.equals("-1")) {
			errors.put("errorOfGender", "請選擇性別");
		}
//
//		// 員工狀態(選項)
		String empStatusInsert = employeeBean.getStatus();
		if (empStatusInsert == null || empStatusInsert.equals("-1")) {
			errors.put("errorOfEmpStatus", "請選擇就職狀況");
		}
//		
//		//員工照片
		Blob empImage = employeeBean.getImg();
		if (empImage == null || ((MultipartFile) empImage).isEmpty() ) {
			errors.put("errorOfEmpPictures", "請上傳照片");
		}
		
		//照片方法
		MultipartFile empImg = employeeBean.getEmpImg();
		
//		String originalFileName = empImg.getOriginalFilename();
//		employeeBean.setFileName(originalFileName);

//		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
//		String rootDirectory = context.getRealPath("/");
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
		employeeService.addEmployee(employeeBean);
		// 將上傳的圖片傳到指定資料夾
//		try {
//			File imageFolder = new File(rootDirectory, "images");
//			if (!imageFolder.exists())
//				imageFolder.mkdirs();
//			File file = new File(imageFolder, employeeBean.getEmpId() + ext);
//			empImg.transferTo(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//		}
		if (errors != null && !errors.isEmpty()) {
			System.out.println("14");
			return "productManage/productInsert";
		}
		
		Integer currentEmpNo = employeeBean.getEmpNo();
		Integer empNoInsert = employeeService.getCurrentEmpNo(currentEmpNo);
				if(empNoInsert == null || empNoInsert.equals(0))
				employeeBean.setEmpNo(empNoInsert+1);
		
		
		return "redirect:/empManage/empQuery";
	}

//	// 取得圖片、檔案
	@RequestMapping(value = "/getPicture/{empId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse res, @PathVariable Integer empId) {
//		String filePath = "/images";
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
//		String fileName = "";
		int len = 0;
		EmployeeBean employeeBean = employeeService.getEmployeesById(empId);
		if (employeeBean != null) {
			Blob blob = employeeBean.getImg();
//			fileName = employeeBean.getFileName();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);

				} catch (SQLException e) {
					throw new RuntimeException("EmployeeController的getPicture()發生SQLException:" + e.getMessage());
				}}
//			} else {
////				media = toByteArray(filePath);
////				fileName = filePath;
////			}
////		} else {
////			media = toByteArray(filePath);
////			fileName = filePath;
////		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
////		String mimeType = context.getMimeType(fileName);
////		MediaType mediaType = MediaType.valueOf(mimeType);
////		System.out.println("mediaType = " + mediaType);
////		headers.setContentType(mediaType);
		}
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
		}

//	private byte[] toByteArray(String filepath) {
//		String root = context.getRealPath("/");
//		root = root.substring(0, root.length() - 1);
//		String fileLocation = root + filepath;
//		byte[] b = null;
//		try {
//			File file = new File(fileLocation);
//			long size = file.length();
//			b = new byte[(int) size];
//			InputStream fis = context.getResourceAsStream(filepath);
//			fis.read(b);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return b;
//	}
	
	

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
		
		
		employeeService.updateEmployee(employeeBean);
		
		return "redirect:/empManage/empQueryFor1";
	}
	

	

//		@RequestMapping(value = "/employeeInsert.action", method = RequestMethod.POST)
//		public String processAddNewEmployeeForm(@ModelAttribute("employeeBean") EmployeeBean employeeBean,
//				BindingResult productInsertresult, Model model) {
//			System.out.println("新來的員工填資料吧!");// 這行是測試用
	//
////			EmployeeBean employeeBean = new EmployeeBean();
//			Map<String, String> errors = new HashMap<>();
//			model.addAttribute("modelErrors", errors);
//			
	//
//			// 員工編號
//			String empNoInsert = employeeBean.getEmpNo();
//			if (empNoInsert == null) {
//				System.out.println("880");
//				if (empNoInsert != null || empNoInsert.length() == 0) {
//					errors.put("errorOfEmployeeNo", "請輸入員工編號");
//				}
//			}
	//
//			
	//
//			
//			
	//
//			// 員工備註(可null)
//			String remarkInsert = employeeBean.getRemark();
//			if (remarkInsert == null || remarkInsert.length() == 0) {
//				errors.put("errorOfRemark", "無備註");
//			}
//			
//			if (errors != null && !errors.isEmpty()) {
//				System.out.println("14");
//				return "employeeInsert.action";
//			}
//			
//			
//			return "redirect:/employeeInsert.action";
	//
//			
	//
////			String cateInsert = menuBean.getCate();
////			Integer productNoInsert = service.getCurrentCategoryNumber(cateInsert);
////			if(productNoInsert == null || productNoInsert.equals(0) ) {
////				switch(cateInsert) {
////					case GlobalService.Product_Cate_Rice:
////						menuBean.setProductNo(101);
////						break;
////					case GlobalService.Product_Cate_Soup:
////						menuBean.setProductNo(201);
////						break;
////					case GlobalService.Product_Cate_Dessert:
////						menuBean.setProductNo(301);
////						break;
////					default: 
////						menuBean.setProductNo(001);
////						break;
////				}
////			}else{
////				menuBean.setProductNo(productNoInsert+1);
////			}
//////			productNoInsert = service.getCurrentCategoryNumber(menuBean.getCate())+1;
//////			menuBean.setProductNo(productNoInsert);
////			service.addProduct(menuBean);
////			
////			return "redirect:/productQuery.action";
//		}

}
