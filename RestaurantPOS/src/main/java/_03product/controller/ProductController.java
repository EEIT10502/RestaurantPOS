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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import _00.init.util.GlobalService;
import _00model.MenuBean;
import _03product.service.ProductService;

//此係為了開發方便(在首頁直接出現連結，連到商品管理相關頁面)，故之後確定商品管理入口後再修改
@Controller
public class ProductController {
	@Autowired
	ProductService service;

	@RequestMapping(value = "/productInsert.action", method = RequestMethod.GET)
	public String getGoodsInsertPage(Model model) {
		System.out.println("11");// 這行是測試用

		MenuBean menuBean = new MenuBean();
//		menuBean.setProductName("請輸入商品名稱");
		model.addAttribute("MenuBean", menuBean);

		return "productManage/productInsert";// 按JSP目錄層
	}

	@RequestMapping(value = "/allProductList.action")
	public String getAllProductListPage(Model model) {
		System.out.println("13");// 這行是測試用
		List<MenuBean> allProductsList = new ArrayList<>();
		allProductsList = service.getAllProducts();
		model.addAttribute("allProductsList", allProductsList);
		System.out.println("allProductsList"+allProductsList);
		
		//Test開始
		List<MenuBean> allProductsListTestRice = new ArrayList<>();
		allProductsListTestRice = service.getAllProductsListTestRice();
		model.addAttribute("allProductsListTestRice", allProductsListTestRice);
		System.out.println("allProductsListTestRice"+allProductsListTestRice);
		
		List<MenuBean> allProductsListTestSoup = new ArrayList<>();
		allProductsListTestSoup = service.getAllProductsListTestSoup();
		model.addAttribute("allProductsListTestSoup", allProductsListTestSoup);
		System.out.println("allProductsListTestSoup"+allProductsListTestSoup);
		
		List<MenuBean> allProductsListTestDessert = new ArrayList<>();
		allProductsListTestDessert = service.getAllProductsListTestDessert();
		model.addAttribute("allProductsListTestDessert", allProductsListTestDessert);
		System.out.println("allProductsListTestDessert"+allProductsListTestDessert);
		//Test結束
		
		return "productManage/allProductList";
	}

	
	@RequestMapping(value = "/productInsert.action", method = RequestMethod.POST)
	public String processAddNewGoodsForm(@ModelAttribute("MenuBean") MenuBean menuBean, BindingResult productInsertresult,
			Model model) {
		System.out.println("12");// 這行是測試用

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
				case GlobalService.Product_Cate_Dessert:
					menuBean.setProductNo(301);
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
		
		return "redirect:/productQuery.action";
	}

	@ModelAttribute("cateList")
	public List<String> getProductCategoryList() {
//		return service.getAllCategories();
		List<String> ProductCateList = new ArrayList<String>();
		ProductCateList.add(GlobalService.Product_Cate_Rice);
		ProductCateList.add(GlobalService.Product_Cate_Soup);
		ProductCateList.add(GlobalService.Product_Cate_Dessert);
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

}
