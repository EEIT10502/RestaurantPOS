package _03product.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import _00.init.util.GlobalService;
import _00model.MenuBean;
import _03product.service.ProductService;

//此係為了開發方便(在首頁直接出現連結，連到商品管理相關頁面)，故之後確定商品管理入口後再修改
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


//	//舊的
//	@RequestMapping(value = "/allProductList.action")
//	public String getAllProductListPage(Model model) {
//		System.out.println("13");// 這行是測試用
//		List<MenuBean> allProductsList = new ArrayList<>();
//		allProductsList = service.getAllProducts();
//		model.addAttribute("allProductsList", allProductsList);
//		System.out.println("allProductsList"+allProductsList);
//		
//		//Test開始
//		List<MenuBean> allProductsListTestRice = new ArrayList<>();
//		allProductsListTestRice = service.getAllProductsListTestRice();
//		model.addAttribute("allProductsListTestRice", allProductsListTestRice);
//		System.out.println("allProductsListTestRice"+allProductsListTestRice);
//		
//		List<MenuBean> allProductsListTestSoup = new ArrayList<>();
//		allProductsListTestSoup = service.getAllProductsListTestSoup();
//		model.addAttribute("allProductsListTestSoup", allProductsListTestSoup);
//		System.out.println("allProductsListTestSoup"+allProductsListTestSoup);
//		
//		List<MenuBean> allProductsListTestDessert = new ArrayList<>();
//		allProductsListTestDessert = service.getAllProductsListTestDessert();
//		model.addAttribute("allProductsListTestDessert", allProductsListTestDessert);
//		System.out.println("allProductsListTestDessert"+allProductsListTestDessert);
//		//Test結束
//		
//		return "productManage/allProductList";
//	}
	
	int currentPageNoInit;
	//新的
	@RequestMapping(value = "/productManage/allProductList.action")
	public String getAllProductListPage(@RequestParam(value = "currentPageNoBtn", required=false)String currentPageNo, Model model) {
		System.out.println("13");// 這行是測試用
		
		System.out.println("currentPageNo:"+currentPageNo);
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}
		System.out.println("currentPageNoInit:"+currentPageNoInit);
		service.setCurrentPageNo(currentPageNoInit);
		
		List<MenuBean> productsListGetByPage = new ArrayList<>();
		productsListGetByPage = service.getProductsListGetByPage();
		model.addAttribute("productsListGetByPage", productsListGetByPage);
		System.out.println("productsListGetByPage"+productsListGetByPage);
		
		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit-1)*GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPages());
		
		System.out.println();
		
		return "productManage/allProductListNew";
	}

	
	@RequestMapping(value = "/productManage/productInsert.action", method = RequestMethod.POST)
	public String processAddNewGoodsForm(@ModelAttribute("MenuBean") MenuBean menuBean, BindingResult productInsertresult,
			Model model) {
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
			if (productInsertresult != null && (productInsertresult.getFieldValue("price") instanceof java.lang.String)) {
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
		if(productNoInsert == null || productNoInsert.equals(0) ) {
			switch(cateInsert) {
				case GlobalService.Product_Cate_Rice:
					menuBean.setProductNo(101);
					break;
				case GlobalService.Product_Cate_Soup:
					menuBean.setProductNo(201);
					break;
				case GlobalService.PRODUCT_CATE_NOODLE:
					menuBean.setProductNo(301);
					break;
				case GlobalService.PRODUCT_CATE_DUMPLINGS:
					menuBean.setProductNo(401);
					break;
				default: 
					menuBean.setProductNo(001);
					break;
			}
		}else{
			menuBean.setProductNo(productNoInsert+1);
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
		ProductCateList.add(GlobalService.PRODUCT_CATE_DUMPLINGS);
		return ProductCateList;
		
	}

	@ModelAttribute("productStatusList")
	public List<String> getProductStatusList() {
		List<String> ProductStatusList = new ArrayList<String>();
		ProductStatusList.add(GlobalService.Product_Status_Launched_Already);
		ProductStatusList.add(GlobalService.Product_Status_No_Longer_Be_Sold);
		return ProductStatusList;
	}
	
//	@ModelAttribute("productStatusList")
//	public List<String> getProductStatusList() {
//		
//		List<MenuBean> allProductsListTestRice = service.getAllProductsListTestRice();
//		model.addAttribute("allProductsListTestRice", allProductsListTestRice);
//		List<String> ProductStatusList = new ArrayList<String>();
//		ProductStatusList.add(GlobalService.Product_Status_Launched_Already);
//		ProductStatusList.add(GlobalService.Product_Status_No_Longer_Be_Sold);
//		return ProductStatusList;
//	}

//	@ModelAttribute("currentCategoryNumber")
//	public Integer getCurrentCategoryNumber() {
//		return service.getCurrentCategoryNumber() + 1;
//	}
	
	@RequestMapping(value = "/productManage/productListBySearch.action", method = RequestMethod.GET)
	public String getProductListBySearch(@RequestParam(value = "currentPageNoBtnSearch", required=false)String currentPageNo,@RequestParam(value = "searchBar", required=false)String searchBarString, Model model) {
		System.out.println("20");// 這行是測試用
		System.out.println("searchBarString:"+searchBarString);
		model.addAttribute("searchBarString", searchBarString);
		
		System.out.println("currentPageNo:"+currentPageNo);
		if (currentPageNo == null) {
			currentPageNoInit = 1;
		} else {
			try {
				currentPageNoInit = Integer.parseInt(currentPageNo.trim());
			} catch (NumberFormatException e) {
				currentPageNoInit = 1;
			}
		}
		System.out.println("currentPageNoInit:"+currentPageNoInit);
		service.setCurrentPageNo(currentPageNoInit);
		
		service.setSearchBarString(searchBarString);
		
		List<MenuBean> productListBySearch = new ArrayList<>();
		productListBySearch = service.getProductsListGetBySearch();
		model.addAttribute("productListBySearch", productListBySearch);
		System.out.println("productListBySearch"+productListBySearch);

		model.addAttribute("currentPageNo", currentPageNoInit);
		model.addAttribute("currentBeginOfItemNo", (currentPageNoInit-1)*GlobalService.ITEMS_PER_PAGE);
		model.addAttribute("totalPages", service.getTotalPagesBySearch());

////		System.out.println();
		
		return "productManage/productListBySearch";
	}
}
