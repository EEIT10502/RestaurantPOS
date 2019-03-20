package _05financial.viewresolver;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import _01.view.MultipleMembersPdfView;
import _01.view.SingleMembersPdfView;
 
public class PdfViewResolver implements ViewResolver{

	ServletContext context;
	public PdfViewResolver(ServletContext context) {
		this.context = context;
	}

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		View view = null;
		if (viewName.startsWith("_01/cnvr/showMembers")) {
			// 由於要展示如何顯示位於檔案內的資料，所以傳入ServletContext物件以便開啟文字檔。
			view = new MultipleMembersPdfView(context);
		} else if (viewName.startsWith("_01/cnvr/displayMember"))  {
			view = new SingleMembersPdfView();
		} 
		System.out.println("ExcelViewResolver, viewName=" + viewName + ", return value=" + view);
		return view;
      }
	
}