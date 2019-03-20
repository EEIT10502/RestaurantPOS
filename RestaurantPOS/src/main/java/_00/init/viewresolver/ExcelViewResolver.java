package _00.init.viewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class ExcelViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		
		View view = null;
		if (viewName.startsWith("productManage/excel")) {
			view = new _03product.view.MultipleMembersExcelView();
		} else if (viewName.startsWith("categoryReport/excel"))  {
			view = new _05financial.view.MultipleMembersExcelView();
		} 
		System.out.println("ExcelViewResolver, viewName=" + viewName + ", return value=" + view);
		return view;
      }
	
}