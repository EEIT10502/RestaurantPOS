package _03product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import _00model.MenuBean;

//此檔案係為了開發方便(在首頁直接出現連結，連到商品管理相關頁面)，故之後確定商品管理入口後再修改

@Controller
public class TemporaryController {
	@RequestMapping("/goodsManage/goodsInsert")
	public String welcome(Model model) {
		System.out.println("11");//這行是測試用，能刪

		MenuBean menuBean = new MenuBean();
//		menuBean.setProductName("請輸入商品名稱");
		model.addAttribute("MenuBean", menuBean);
		
		return "goodsManage/goodsInsert";
	}

}
