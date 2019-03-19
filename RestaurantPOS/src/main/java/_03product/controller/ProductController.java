package _03product.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import _00.init.util.GlobalService;
import _00model.EmployeeBean;
import _00model.MenuBean;
import _02employee.service.EmployeeService;
import _03product.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService service;

	int currentPageNo = 1;
	int currentPageNoInit;

	@RequestMapping(value = "/productManage/productInsert.action", method = RequestMethod.GET)
	public String getGoodsInsertPage(@ModelAttribute("insertComplete") String insertComplete, Model model) {
		MenuBean menuBean = new MenuBean();
		model.addAttribute("MenuBean", menuBean);
		
		if(insertComplete != null) {
			model.addAttribute("insertComplete", insertComplete);
		}
		

		return "productManage/productInsert";// 按JSP目錄層
	}

	@RequestMapping(value = "/productManage/productInsert.action", method = RequestMethod.POST)
	public String processAddNewGoodsForm(@ModelAttribute("MenuBean") MenuBean menuBean,
			BindingResult productInsertresult, Model model) {
		Map<String, String> errors = new HashMap<>();
		model.addAttribute("modelErrors", errors);

		String productNameInsert = menuBean.getProductName();
		if (productNameInsert == null || productNameInsert.length() == 0) {
			errors.put("errorOfProductName", "請輸入商品名稱");
		}

		Integer priceInsert = menuBean.getPrice();
		if (priceInsert == null) {
			if (productInsertresult != null
					&& (productInsertresult.getFieldValue("price") instanceof java.lang.String)) {
				errors.put("typeErrorOfPrice", "請輸入數字");
			} else {
				errors.put("errorOfPrice", "請輸入商品價格");
			}
		}

		String CateInsert = menuBean.getCate();
		if (CateInsert == null || CateInsert.equals("-1")) {
			errors.put("errorOfCate", "請選擇商品種類");
		}

		String ProductStatusInsert = menuBean.getProductStatus();
		if (ProductStatusInsert == null || ProductStatusInsert.equals("-1")) {
			errors.put("errorOfProductStatus", "請選擇商品狀態");
		}

		if (errors != null && !errors.isEmpty()) {
			return "productManage/productInsert";
		}

		String cateInsert = menuBean.getCate();
		Integer productNoInsert = service.getCurrentCategoryNumber(cateInsert);
		if (productNoInsert == null || productNoInsert.equals(0)) {
			switch (cateInsert) {
			case GlobalService.Product_Cate_Rice:
				menuBean.setProductNo(101);
				break;
			case GlobalService.Product_Cate_Soup:
				menuBean.setProductNo(201);
				break;
			case GlobalService.PRODUCT_CATE_NOODLE:
				menuBean.setProductNo(301);
				break;
			case GlobalService.PRODUCT_CATE_VEGETABLE:
				menuBean.setProductNo(401);
				break;
			case GlobalService.PRODUCT_CATE_SIDEDISH:
				menuBean.setProductNo(501);
				break;
			default:
				menuBean.setProductNo(001);
				break;
			}
		} else {
			menuBean.setProductNo(productNoInsert + 1);
		}

		service.addProduct(menuBean);
		model.addAttribute("insertComplete", "成功新增一筆資料");
		
		return "redirect:/productManage/productInsert.action";
	}
	
	@RequestMapping(value = "/productManage/allProductList.action")
	public String getAllProductListPage(
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

		service.setCurrentPageNo(currentPageNoInit);

		List<MenuBean> productsListGetByPage = new ArrayList<>();
		productsListGetByPage = service.getProductsListGetByPage();
		model.addAttribute("productsListGetByPage", productsListGetByPage);

		if (productsListGetByPage.isEmpty()) {
			model.addAttribute("noItemString", "查無資料");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);

			return "productManage/allProductListNew";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPages());

		return "productManage/allProductListNew";
	}

	@RequestMapping(value = "/productManage/allProductListEdit.action/{key}", method = RequestMethod.POST)
	public String updateMenu(@PathVariable Integer key,
			@RequestParam(value = "pIdEdit", required = false) String pIdEdit,
			@RequestParam(value = "productNoEdit", required = false) String productNoEdit,
			@RequestParam(value = "productNameEdit", required = false) String productNameEdit,
			@RequestParam(value = "priceEdit", required = false) String priceEdit,
			@RequestParam(value = "cateEdit", required = false) String cateEdit,
			@RequestParam(value = "productStatusEdit", required = false) String productStatusEdit,
			@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo) {
		int pIdEditParse = Integer.parseInt(pIdEdit.trim());
		int productNoEditParse = Integer.parseInt(productNoEdit.trim());
		int priceEditParse = Integer.parseInt(priceEdit.trim());

		MenuBean menuBean = new MenuBean(pIdEditParse, productNoEditParse, productNameEdit, priceEditParse, cateEdit,
				productStatusEdit);
		service.updateMenu(menuBean);

		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		service.setCurrentPageNo(currentPageNoInit);

		return "redirect:/productManage/allProductList.action?currentPageNoBtn=" + currentPageNoInit;
	}
	
	@RequestMapping(value = "/productManage/ProductListByCate.action")
	public String getProductListByCate(
			@RequestParam(value = "currentPageNoBtnCate", required = false) String currentPageNo,
			@RequestParam(value = "whichCate", required = false) String cateSelect, Model model) {
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		service.setCurrentPageNo(currentPageNoInit);

		model.addAttribute("whichCate", cateSelect);
		service.setCateSelect(cateSelect);

		List<MenuBean> productsListGetByCate = new ArrayList<>();
		productsListGetByCate = service.getProductsListGetByCate();
		model.addAttribute("productsListGetByCate", productsListGetByCate);

		if (productsListGetByCate.isEmpty()) {
			if (currentPageNoInit > 1) {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
				currentPageNoInit = currentPageNoInit - 1;
				service.setCurrentPageNo(currentPageNoInit);

				productsListGetByCate = new ArrayList<>();
				productsListGetByCate = service.getProductsListGetByCate();
				model.addAttribute("productsListGetByCate", productsListGetByCate);

				model.addAttribute("currentPageNo", currentPageNoInit);
				model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
				model.addAttribute("totalPages", service.getTotalPagesByCate());
				
				return "productManage/productListByCate";
			}
			model.addAttribute("noItemString", "查無資料");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);

			return "productManage/productListByCate";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPagesByCate());

		return "productManage/productListByCate";
	}

	@RequestMapping(value = "/productManage/ProductListByCateEdit.action/{key}", method = RequestMethod.POST)
	public String updateMenuByCate(@PathVariable Integer key,
			@RequestParam(value = "pIdEdit", required = false) String pIdEdit,
			@RequestParam(value = "productNoEdit", required = false) String productNoEdit,
			@RequestParam(value = "productNameEdit", required = false) String productNameEdit,
			@RequestParam(value = "priceEdit", required = false) String priceEdit,
			@RequestParam(value = "cateEdit", required = false) String cateEdit,
			@RequestParam(value = "productStatusEdit", required = false) String productStatusEdit,
			@RequestParam(value = "currentPageNoBtnCate", required = false) String currentPageNo,
			@RequestParam(value = "whichCate", required = false) String cateSelect, Model model) {
		int pIdEditParse = Integer.parseInt(pIdEdit.trim());
		int productNoEditParse = Integer.parseInt(productNoEdit.trim());
		int priceEditParse = Integer.parseInt(priceEdit.trim());

		MenuBean menuBean = new MenuBean(pIdEditParse, productNoEditParse, productNameEdit, priceEditParse, cateEdit,
				productStatusEdit);
		service.updateMenu(menuBean);

		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		service.setCurrentPageNo(currentPageNoInit);

		model.addAttribute("whichCate", cateSelect);

		return "redirect:/productManage/ProductListByCate.action?currentPageNoBtnCate=" + currentPageNoInit;
	}

	@RequestMapping(value = "/productManage/ProductListByProductStatus.action")
	public String getProductListByProductStatus(
			@RequestParam(value = "currentPageNoBtnProductStatus", required = false) String currentPageNo,
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

		service.setCurrentPageNo(currentPageNoInit);

		model.addAttribute("whichStatus", StatusSelect);
		service.setStatusSelect(StatusSelect);

		List<MenuBean> productsListGetByProductStatus = new ArrayList<>();
		productsListGetByProductStatus = service.getProductsListGetByProductStatus();
		model.addAttribute("productsListGetByProductStatus", productsListGetByProductStatus);

		if (productsListGetByProductStatus.isEmpty()) {
			if (currentPageNoInit > 1) {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
				currentPageNoInit = currentPageNoInit - 1;
				service.setCurrentPageNo(currentPageNoInit);

				productsListGetByProductStatus = new ArrayList<>();
				productsListGetByProductStatus = service.getProductsListGetByProductStatus();
				model.addAttribute("productsListGetByProductStatus", productsListGetByProductStatus);
				model.addAttribute("currentPageNo", currentPageNoInit);
				model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
				model.addAttribute("totalPages", service.getTotalPagesByProductStatus());
				
				return "productManage/productListByProductStatus";
			}
			model.addAttribute("noItemString", "查無資料");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);

			return "productManage/productListByProductStatus";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPagesByProductStatus());

		return "productManage/productListByProductStatus";
	}

	@RequestMapping(value = "/productManage/ProductListByProductStatusEdit.action/{key}", method = RequestMethod.POST)
	public String updateMenuByProductStatus(@PathVariable Integer key,
			@RequestParam(value = "pIdEdit", required = false) String pIdEdit,
			@RequestParam(value = "productNoEdit", required = false) String productNoEdit,
			@RequestParam(value = "productNameEdit", required = false) String productNameEdit,
			@RequestParam(value = "priceEdit", required = false) String priceEdit,
			@RequestParam(value = "cateEdit", required = false) String cateEdit,
			@RequestParam(value = "productStatusEdit", required = false) String productStatusEdit,
			@RequestParam(value = "currentPageNoBtnProductStatus", required = false) String currentPageNo,
			@RequestParam(value = "whichStatus", required = false) String StatusSelect, Model model) {
		int pIdEditParse = Integer.parseInt(pIdEdit.trim());
		int productNoEditParse = Integer.parseInt(productNoEdit.trim());
		int priceEditParse = Integer.parseInt(priceEdit.trim());

		MenuBean menuBean = new MenuBean(pIdEditParse, productNoEditParse, productNameEdit, priceEditParse, cateEdit,
				productStatusEdit);
		service.updateMenu(menuBean);

		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		service.setCurrentPageNo(currentPageNoInit);

		model.addAttribute("whichStatus", StatusSelect);

		return "redirect:/productManage/ProductListByProductStatus.action?currentPageNoBtnProductStatus="
				+ currentPageNoInit;
	}

	@RequestMapping(value = "/productManage/productListBySearch.action", method = RequestMethod.GET)
	public String getProductListBySearch(
			@RequestParam(value = "currentPageNoBtnSearch", required = false) String currentPageNo,
			@RequestParam(value = "searchBar", required = false) String searchBarString, Model model) {
		model.addAttribute("searchBarString", searchBarString);

		if (searchBarString.isEmpty()) {
			model.addAttribute("noSearchBarString", "請輸入查詢條件");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);
			return "productManage/productListBySearch";
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
		
		service.setCurrentPageNo(currentPageNoInit);
		service.setSearchBarString(searchBarString);
		List<MenuBean> productListBySearch = new ArrayList<>();
		productListBySearch = service.getProductsListGetBySearch();
		model.addAttribute("productListBySearch", productListBySearch);

		if (productListBySearch.isEmpty()) {
			if (currentPageNoInit > 1) {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
				currentPageNoInit = currentPageNoInit - 1;
				service.setCurrentPageNo(currentPageNoInit);

				productListBySearch = new ArrayList<>();
				productListBySearch = service.getProductsListGetBySearch();
				model.addAttribute("productListBySearch", productListBySearch);
				model.addAttribute("currentPageNo", currentPageNoInit);
				model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
				model.addAttribute("totalPages", service.getTotalPagesBySearch());
				
				return "productManage/productListBySearch";
			}
			model.addAttribute("noItemString", "查無資料");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);

			return "productManage/productListBySearch";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPagesBySearch());

		return "productManage/productListBySearch";
	}

	@RequestMapping(value = "/productManage/ProductListBySearchEdit.action/{key}", method = RequestMethod.POST)
	public String updateMenuBySearch(@PathVariable Integer key,
			@RequestParam(value = "pIdEdit", required = false) String pIdEdit,
			@RequestParam(value = "productNoEdit", required = false) String productNoEdit,
			@RequestParam(value = "productNameEdit", required = false) String productNameEdit,
			@RequestParam(value = "priceEdit", required = false) String priceEdit,
			@RequestParam(value = "cateEdit", required = false) String cateEdit,
			@RequestParam(value = "productStatusEdit", required = false) String productStatusEdit,
			@RequestParam(value = "currentPageNoBtnSearch", required = false) String currentPageNo,
			@RequestParam(value = "searchBar", required = false) String searchBarString, Model model) {
		int pIdEditParse = Integer.parseInt(pIdEdit.trim());
		int productNoEditParse = Integer.parseInt(productNoEdit.trim());
		int priceEditParse = Integer.parseInt(priceEdit.trim());

		MenuBean menuBean = new MenuBean(pIdEditParse, productNoEditParse, productNameEdit, priceEditParse, cateEdit,
				productStatusEdit);
		service.updateMenu(menuBean);

		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}

		service.setCurrentPageNo(currentPageNoInit);

		model.addAttribute("searchBar", searchBarString);

		return "redirect:/productManage/productListBySearch.action?currentPageNoBtnSearch=" + currentPageNoInit;
	}

	@ModelAttribute("cateList")
	public List<String> getProductCategoryList() {
		List<String> ProductCateList = new ArrayList<String>();
		ProductCateList.add(GlobalService.Product_Cate_Rice);
		ProductCateList.add(GlobalService.Product_Cate_Soup);
		ProductCateList.add(GlobalService.PRODUCT_CATE_NOODLE);
		ProductCateList.add(GlobalService.PRODUCT_CATE_VEGETABLE);
		ProductCateList.add(GlobalService.PRODUCT_CATE_SIDEDISH);

		return ProductCateList;
	}

	@ModelAttribute("productStatusList")
	public List<String> getProductStatusList() {
		List<String> ProductStatusList = new ArrayList<String>();
		ProductStatusList.add(GlobalService.Product_Status_Launched_Already);
		ProductStatusList.add(GlobalService.Product_Status_No_Longer_Be_Sold);
		return ProductStatusList;
	}
	
//	@RequestMapping(value = "/productManage/allProductListExcel.action")
//	public String getAllProductListPageExcel(
//			@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo, Model model) {
//		createExcel(getStudent());
//		
//		
//		
//		return "productManage/allProductListNewtest";
//	}
//	
//	private static List<Student> getStudent() {
//		List<Student> list = new ArrayList<Student>();
//		Student student1 = new Student("小明", 8, "二年級");
//		Student student2 = new Student("小光", 9, "三年級");
//		Student student3 = new Student("小花", 10, "四年級");
//		list.add(student1);
//		list.add(student2);
//		list.add(student3);
//		return list;
//		}
//	
//	private static void createExcel(List<Student> list) {
//		// 建立一個Excel檔案
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		// 建立一個工作表
//		HSSFSheet sheet = workbook.createSheet("學生表一");
//		// 新增表頭行
//		HSSFRow hssfRow = sheet.createRow(0);
//		// 設定單元格格式居中
//		HSSFCellStyle cellStyle = workbook.createCellStyle();
//		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		// 新增表頭內容
//		HSSFCell headCell = hssfRow.createCell(0);
//		headCell.setCellValue("姓名");
//		headCell.setCellStyle(cellStyle);
//		headCell = hssfRow.createCell(1);
//		headCell.setCellValue("年齡");
//		headCell.setCellStyle(cellStyle);
//		headCell = hssfRow.createCell(2);
//		headCell.setCellValue("年級");
//		headCell.setCellStyle(cellStyle);
//		// 新增資料內容
//		for (int i = 0; i < list.size(); i++  ) {
//		hssfRow = sheet.createRow((int) i + 1);
//		Student student = list.get(i);
//		// 建立單元格，並設定值
//		HSSFCell cell = hssfRow.createCell(0);
//		cell.setCellValue(student.getName());
//		cell.setCellStyle(cellStyle);
//		cell = hssfRow.createCell(1);
//		cell.setCellValue(student.getAge());
//		cell.setCellStyle(cellStyle);
//		cell = hssfRow.createCell(2);
//		cell.setCellValue(student.getGrade());
//		cell.setCellStyle(cellStyle);
//		}
//		// 儲存Excel檔案
//		try {
//		OutputStream outputStream = new FileOutputStream("D:/students.xls");
//		workbook.write(outputStream);
//		outputStream.close();
//		} catch (Exception e) {
//		e.printStackTrace();
//		}
//		}
	
	//==========================================================================test排班_開始
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "/productManage/test/scheduleTest.action", method = RequestMethod.GET)
	public String scheduleTest(
//			@RequestParam(value = "pIdEdit", required = false) String pIdEdit,
//			@RequestParam(value = "productNoEdit", required = false) String productNoEdit,
//			@RequestParam(value = "productNameEdit", required = false) String productNameEdit,
//			@RequestParam(value = "priceEdit", required = false) String priceEdit,
//			@RequestParam(value = "cateEdit", required = false) String cateEdit,
//			@RequestParam(value = "productStatusEdit", required = false) String productStatusEdit,
//			@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo
			) {
//		int pIdEditParse = Integer.parseInt(pIdEdit.trim());
//		int productNoEditParse = Integer.parseInt(productNoEdit.trim());
//		int priceEditParse = Integer.parseInt(priceEdit.trim());
//
//		MenuBean menuBean = new MenuBean(pIdEditParse, productNoEditParse, productNameEdit, priceEditParse, cateEdit,
//				productStatusEdit);
//		service.updateMenu(menuBean);
//
//		if (currentPageNo == null) {
//			currentPageNoInit = 1;
//		} else {
//			try {
//				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
//			} catch (NumberFormatException e) {
//				currentPageNoInit = 1;
//			}
//		}
//
//		service.setCurrentPageNo(currentPageNoInit);

		return "productManage/scheduleTest";
	}
	
	@ModelAttribute("scheduleDateList")
	public List<String> getScheduleDateList(Model model) {
		
		Date beginDate = new Date();
		long beginDateJavaUtil = beginDate.getTime(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String beginDateString = dateFormat.format(beginDate);
		
		long nextDate = beginDateJavaUtil+24 * 60 * 60 * 1000;
		String nextDateString = dateFormat.format(nextDate);
		
		List<String> scheduleDateList = new ArrayList<String>();
		scheduleDateList.add(beginDateString);
		scheduleDateList.add(nextDateString);
		

		return scheduleDateList;
	}
//	@ModelAttribute("scheduleEmpNameList")
//	public List<String> getScheduleEmpNameList(Model model) {
//	
//		List<String> ScheduleEmpList = new ArrayList<String>();
//		ScheduleEmpList = employeeService.getAllEmployeesName();
//		
//		return ScheduleEmpList;
//	}
	@ModelAttribute("scheduleEmpList")
	public List<EmployeeBean> getScheduleEmpList(Model model) {
	
		List<EmployeeBean> ScheduleEmpList = new ArrayList<EmployeeBean>();
		ScheduleEmpList = employeeService.getAllEmployees();
		
		return ScheduleEmpList;
	}
	@ModelAttribute("workTypeList")
	public List<String> getworkTypeList(Model model) {
	
		List<String> WorkTypeList = new ArrayList<String>();
		WorkTypeList.add("早班");
		WorkTypeList.add("午班");
		WorkTypeList.add("晚班");
		
		return WorkTypeList;
	}
	
	//==========================================================================test排班_結束
}
