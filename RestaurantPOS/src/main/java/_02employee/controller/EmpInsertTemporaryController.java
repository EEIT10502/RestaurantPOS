package _02employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//此檔案係為了開發方便(在首頁直接出現連結，連到員工管理相關頁面)，故之後確定員工管理入口後再修改

@Controller
public class EmpInsertTemporaryController {
	@RequestMapping("/empManage/empInsert")
	public String empInsert(Model model) {
		System.out.println("員工新增");

		return "empManage/empInsert";
	}
	
	

}
