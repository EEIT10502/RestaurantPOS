package _03product.controller;

import java.util.ArrayList;
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
import _00model.MenuBean;
import _03product.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService service;

	int currentPageNo = 1;

	@RequestMapping(value = "/productManage/productInsert.action", method = RequestMethod.GET)
	public String getGoodsInsertPage(Model model) {
		System.out.println("進入productInsert.action GET方法");// 這行是測試用

		MenuBean menuBean = new MenuBean();
//		menuBean.setProductName("請輸入商品名稱");
		model.addAttribute("MenuBean", menuBean);

		return "productManage/productInsert";// 按JSP目錄層
	}

	int currentPageNoInit;

	// 新的
	@RequestMapping(value = "/productManage/allProductList.action")
	public String getAllProductListPage(
			@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo, Model model) {
		System.out.println("13");// 這行是測試用

//		MenuBean menuBean = new MenuBean();
//		model.addAttribute("MenuBean", menuBean);

		System.out.println("currentPageNo:" + currentPageNo);
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}
		System.out.println("currentPageNoInit:" + currentPageNoInit);
		service.setCurrentPageNo(currentPageNoInit);

		List<MenuBean> productsListGetByPage = new ArrayList<>();
		productsListGetByPage = service.getProductsListGetByPage();
		model.addAttribute("productsListGetByPage", productsListGetByPage);
		System.out.println("productsListGetByPage" + productsListGetByPage);

		if (productsListGetByPage.isEmpty()) {
			model.addAttribute("noItemString", "查無資料");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);
//			System.out.println("25");
			return "productManage/allProductListNew";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPages());

		System.out.println();

		return "productManage/allProductListNew";
	}

	@RequestMapping(value = "/productManage/productInsert.action", method = RequestMethod.POST)
	public String processAddNewGoodsForm(@ModelAttribute("MenuBean") MenuBean menuBean,
			BindingResult productInsertresult, Model model) {
		System.out.println("進入productInsert.action POST方法");// 這行是測試用

		Map<String, String> errors = new HashMap<>();
		model.addAttribute("modelErrors", errors);

		String productNameInsert = menuBean.getProductName();
		if (productNameInsert == null || productNameInsert.length() == 0) {
			errors.put("errorOfProductName", "請輸入商品名稱");
		}

		Integer priceInsert = menuBean.getPrice();
		if (priceInsert == null) {
			System.out.println("880");
			if (productInsertresult != null
					&& (productInsertresult.getFieldValue("price") instanceof java.lang.String)) {
				System.out.println("881");
				errors.put("typeErrorOfPrice", "請輸入數字");
			} else {
				System.out.println("882");
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
			System.out.println("14");
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
//		productNoInsert = service.getCurrentCategoryNumber(menuBean.getCate())+1;
//		menuBean.setProductNo(productNoInsert);
		service.addProduct(menuBean);

		return "redirect:/productManage/allProductList.action";
	}

	@ModelAttribute("cateList")
	public List<String> getProductCategoryList() {
//		return service.getAllCategories();
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

	@RequestMapping(value = "/productManage/productListBySearch.action", method = RequestMethod.GET)
	public String getProductListBySearch(
			@RequestParam(value = "currentPageNoBtnSearch", required = false) String currentPageNo,
			@RequestParam(value = "searchBar", required = false) String searchBarString, Model model) {
		System.out.println("20");// 這行是測試用
		System.out.println("searchBarString:" + searchBarString);

		model.addAttribute("searchBarString", searchBarString);
		if (searchBarString.isEmpty()) {
			model.addAttribute("noSearchBarString", "請輸入查詢條件");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);
			return "productManage/productListBySearch";
		}

		System.out.println("currentPageNo:" + currentPageNo);
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}
		System.out.println("currentPageNoInit:" + currentPageNoInit);
		service.setCurrentPageNo(currentPageNoInit);

		service.setSearchBarString(searchBarString);

		List<MenuBean> productListBySearch = new ArrayList<>();
		productListBySearch = service.getProductsListGetBySearch();
		model.addAttribute("productListBySearch", productListBySearch);
		System.out.println("productListBySearch" + productListBySearch);

		if (productListBySearch.isEmpty()) {
			model.addAttribute("noItemString", "查無資料");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);
//			System.out.println("25");
			return "productManage/productListBySearch";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPagesBySearch());

////		System.out.println();

		return "productManage/productListBySearch";
	}



	

	// 修改單筆Menu資料1
		@RequestMapping(value = "/productManage/allProductListEdit.action/{key}", method = RequestMethod.POST)
		public String updateMenu(@PathVariable Integer key,
				@RequestParam(value = "pIdEdit", required = false) String pIdEdit,
				@RequestParam(value = "productNoEdit", required = false) String productNoEdit,
				@RequestParam(value = "productNameEdit", required = false) String productNameEdit, 
				@RequestParam(value = "priceEdit", required = false) String priceEdit,
				@RequestParam(value = "cateEdit", required = false) String cateEdit,
				@RequestParam(value = "productStatusEdit", required = false) String productStatusEdit,
				@RequestParam(value = "currentPageNoBtn", required = false) String currentPageNo) {
			System.out.println(66);
			System.out.println("pIdEdit:"+pIdEdit);
			System.out.println("productNoEdit:"+productNoEdit);
			System.out.println("productNameEdit:"+productNameEdit);
			System.out.println("priceEdit:"+priceEdit);
			System.out.println("cateEdit:"+cateEdit);
			System.out.println("productStatusEdit:"+productStatusEdit);
			System.out.println("currentPageNo:"+currentPageNo);
			
			int pIdEditParse = Integer.parseInt(pIdEdit.trim());
			int productNoEditParse = Integer.parseInt(productNoEdit.trim());
			int priceEditParse = Integer.parseInt(priceEdit.trim());
			
			MenuBean menuBean = new MenuBean(pIdEditParse,productNoEditParse,productNameEdit,priceEditParse,cateEdit,productStatusEdit);
			service.updateMenu(menuBean);
			
			System.out.println(67);
			
			System.out.println("currentPageNo:" + currentPageNo);
			if (currentPageNo == null) {
				currentPageNoInit = 1;
			} else {
				try {
					currentPageNoInit = Integer.parseInt(currentPageNo.trim());
				} catch (NumberFormatException e) {
					currentPageNoInit = 1;
				}
			}
			System.out.println("currentPageNoInit:" + currentPageNoInit);
			service.setCurrentPageNo(currentPageNoInit);

			
			return "redirect:/productManage/allProductList.action?currentPageNoBtn="+currentPageNoInit;
		}
	
	// 修改單筆Menu資料2
	@RequestMapping(value = "/productManage/ProductListByCateEdit.action/{key}", method = RequestMethod.POST)
	public String updateMenuByCate(@PathVariable Integer key,
			@RequestParam(value = "pIdEdit", required = false) String pIdEdit,
			@RequestParam(value = "productNoEdit", required = false) String productNoEdit,
			@RequestParam(value = "productNameEdit", required = false) String productNameEdit, 
			@RequestParam(value = "priceEdit", required = false) String priceEdit,
			@RequestParam(value = "cateEdit", required = false) String cateEdit,
			@RequestParam(value = "productStatusEdit", required = false) String productStatusEdit,
			@RequestParam(value = "currentPageNoBtnCate", required = false) String currentPageNo,
			@RequestParam(value = "whichCate", required = false) String cateSelect,
			Model model) {
//		System.out.println(66);
//		System.out.println("pIdEdit:"+pIdEdit);
//		System.out.println("productNoEdit:"+productNoEdit);
//		System.out.println("productNameEdit:"+productNameEdit);
//		System.out.println("priceEdit:"+priceEdit);
//		System.out.println("cateEdit:"+cateEdit);
//		System.out.println("productStatusEdit:"+productStatusEdit);
//		System.out.println("currentPageNo:"+currentPageNo);
//		System.out.println("cateSelect:"+cateSelect);
		
		int pIdEditParse = Integer.parseInt(pIdEdit.trim());
		int productNoEditParse = Integer.parseInt(productNoEdit.trim());
		int priceEditParse = Integer.parseInt(priceEdit.trim());
		
		MenuBean menuBean = new MenuBean(pIdEditParse,productNoEditParse,productNameEdit,priceEditParse,cateEdit,productStatusEdit);
		service.updateMenu(menuBean);
		
		System.out.println(67);
		
		System.out.println("currentPageNo:" + currentPageNo);
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}
//		System.out.println("currentPageNoInit:" + currentPageNoInit);
		service.setCurrentPageNo(currentPageNoInit);
		
		model.addAttribute("whichCate", cateSelect);
		
		return "redirect:/productManage/ProductListByCate.action?currentPageNoBtnCate="+currentPageNoInit;
	}
	
	@RequestMapping(value = "/productManage/ProductListByCate.action")
	public String getProductListByCate(
			@RequestParam(value = "currentPageNoBtnCate", required = false) String currentPageNo,
			@RequestParam(value = "whichCate", required = false) String cateSelect, Model model) {
		System.out.println("68");// 這行是測試用

		System.out.println("currentPageNo:" + currentPageNo);
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}
//		System.out.println("currentPageNoInit:" + currentPageNoInit);
		service.setCurrentPageNo(currentPageNoInit);
		
		model.addAttribute("whichCate", cateSelect);
		service.setCateSelect(cateSelect);
		
		List<MenuBean> productsListGetByCate = new ArrayList<>();
		productsListGetByCate = service.getProductsListGetByCate();
		model.addAttribute("productsListGetByCate", productsListGetByCate);
		System.out.println("productsListGetByCate" + productsListGetByCate);

		if (productsListGetByCate.isEmpty()) {
			model.addAttribute("noItemString", "查無資料");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);
//			System.out.println("25");
			return "productManage/productListByCate";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPagesByCate());

		System.out.println();

		return "productManage/productListByCate";
	}
	
	// 修改單筆Menu資料
	@RequestMapping(value = "/productManage/ProductListByProductStatusEdit.action/{key}", method = RequestMethod.POST)
	public String updateMenuByProductStatus(@PathVariable Integer key,
			@RequestParam(value = "pIdEdit", required = false) String pIdEdit,
			@RequestParam(value = "productNoEdit", required = false) String productNoEdit,
			@RequestParam(value = "productNameEdit", required = false) String productNameEdit, 
			@RequestParam(value = "priceEdit", required = false) String priceEdit,
			@RequestParam(value = "cateEdit", required = false) String cateEdit,
			@RequestParam(value = "productStatusEdit", required = false) String productStatusEdit,
			@RequestParam(value = "currentPageNoBtnCate", required = false) String currentPageNo,
			@RequestParam(value = "whichCate", required = false) String cateSelect,
			Model model) {
//		System.out.println(66);
//		System.out.println("pIdEdit:"+pIdEdit);
//		System.out.println("productNoEdit:"+productNoEdit);
//		System.out.println("productNameEdit:"+productNameEdit);
//		System.out.println("priceEdit:"+priceEdit);
//		System.out.println("cateEdit:"+cateEdit);
//		System.out.println("productStatusEdit:"+productStatusEdit);
//		System.out.println("currentPageNo:"+currentPageNo);
//		System.out.println("cateSelect:"+cateSelect);
		
		int pIdEditParse = Integer.parseInt(pIdEdit.trim());
		int productNoEditParse = Integer.parseInt(productNoEdit.trim());
		int priceEditParse = Integer.parseInt(priceEdit.trim());
		
		MenuBean menuBean = new MenuBean(pIdEditParse,productNoEditParse,productNameEdit,priceEditParse,cateEdit,productStatusEdit);
		service.updateMenu(menuBean);
		
		System.out.println(67);
		
		System.out.println("currentPageNo:" + currentPageNo);
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}
//		System.out.println("currentPageNoInit:" + currentPageNoInit);
		service.setCurrentPageNo(currentPageNoInit);
		
		model.addAttribute("whichCate", cateSelect);
		
		return "redirect:/productManage/ProductListByCate.action?currentPageNoBtnCate="+currentPageNoInit;
	}
	
	@RequestMapping(value = "/productManage/ProductListByProductStatus.action")
	public String getProductListByProductStatus(
			@RequestParam(value = "currentPageNoBtnProductStatus", required = false) String currentPageNo,
			@RequestParam(value = "whichStatus", required = false) String StatusSelect, Model model) {
		System.out.println("33");// 這行是測試用

		System.out.println("currentPageNo:" + currentPageNo);
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}
		System.out.println("currentPageNoInit:" + currentPageNoInit);
		service.setCurrentPageNo(currentPageNoInit);

		System.out.println("StatusSelect:" + StatusSelect);
		model.addAttribute("whichStatus", StatusSelect);
		service.setStatusSelect(StatusSelect);

		List<MenuBean> productsListGetByProductStatus = new ArrayList<>();
		productsListGetByProductStatus = service.getProductsListGetByProductStatus();
		model.addAttribute("productsListGetByProductStatus", productsListGetByProductStatus);
		System.out.println("productsListGetByProductStatus" + productsListGetByProductStatus);

		if (productsListGetByProductStatus.isEmpty()) {
			model.addAttribute("noItemString", "查無資料");
			model.addAttribute("currentPageNo", 0);
			model.addAttribute("totalPages", 0);
//			System.out.println("25");
			return "productManage/productListByProductStatus";
		}

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit - 1) * GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPagesByProductStatus());

		System.out.println();

		return "productManage/productListByProductStatus";
	}
}
