package _02employee.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import _00model.EmployeeBean;
import _02employee.service.EmployeeService;

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
	
//	Index.jsp開超連結，方便查看登入
	@RequestMapping("/manage/managelogin")
	public String empLogin(Model model) {
		System.out.println("管理員登入");

		return "manage/managelogin";
	}
	
	
	
	

	
}
