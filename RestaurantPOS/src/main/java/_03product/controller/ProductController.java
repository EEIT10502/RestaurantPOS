package _03product.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping(value = "/goodsManage/goodsInsert", method = RequestMethod.GET)
	public String getGoodsInsertPage(Model model) {
		System.out.println("11");// 這行是測試用

		MenuBean menuBean = new MenuBean();
//		menuBean.setProductName("請輸入商品名稱");
		model.addAttribute("MenuBean", menuBean);

		return "goodsManage/goodsInsert";
	}

	@RequestMapping(value = "/goodsManage/goodsInsert", method = RequestMethod.POST)
	public String processAddNewGoodsForm(@ModelAttribute("MenuBean") MenuBean menuBean, BindingResult result,
			HttpServletRequest request) {
		System.out.println("12");// 這行是測試用
//		
//		String[] suppressedFields = result.getSuppressedFields();
//		if (suppressedFields.length > 0) {
//			throw new RuntimeException("嘗試傳入不允許的欄位: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
//		}
//		if (menuBean.getStock() == null) {
//			menuBean.setStock(0);
//		}
//		
//		MultipartFile productImage = menuBean.getProductImage();
//		String originalFilename = productImage.getOriginalFilename();
//		menuBean.setFileName(originalFilename);
//		
//		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
//		String rootDirectory = context.getRealPath("/");
//		//  建立Blob物件，交由 Hibernate 寫入資料庫
//		if (productImage != null && !productImage.isEmpty() ) {
//			try {
//				byte[] b = productImage.getBytes();
//				Blob blob = new SerialBlob(b);
//				menuBean.setCoverImage(blob);
//			} catch(Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//			}
//		}
		service.addProduct(menuBean);
//		try {
//			File imageFolder = new File(rootDirectory, "images");
//			if (!imageFolder.exists()) imageFolder.mkdirs();
//			File file = new File(imageFolder, menuBean.getBookId() + ext);
//			productImage.transferTo(file);
//		} catch(Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//		}
		return "redirect:/empManage/empInsert";
	}

	@ModelAttribute("cateList")
	public List<String> getProductCategoryList() {
		return service.getAllCategories();
	}

//	private List<String> ProductStatusList= new List<String>(); 
		

//	@ModelAttribute("productStatusList")
//	public List<String> getProductStatusList() {
//		List<String> ProductStatusList= new List<String>(); 
//		ProductStatusList.add("停售");
////		ProductStatusList.add(GlobalService.Product_Status_No_Longer_Be_Sold);
//		return ProductStatusList;
//	}

	}
