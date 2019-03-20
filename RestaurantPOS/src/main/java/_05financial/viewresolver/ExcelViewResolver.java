package _05financial.viewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import _01.view.MultipleMembersExcelView;
import _01.view.SingleMemberExcelView;

public class ExcelViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		
		View view = null;
		if (viewName.startsWith("_01/cnvr/showMembers")) {
			view = new MultipleMembersExcelView();
		} else if (viewName.startsWith("_01/cnvr/displayMember"))  {
			view = new SingleMemberExcelView();
		} 
		System.out.println("ExcelViewResolver, viewName=" + viewName + ", return value=" + view);
		return view;
      }
	
}