package _02employee.view;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import _04schedule.view.MultipleAttendencePdfView;


 
public class PdfViewResolver implements ViewResolver{
	ServletContext context;
	public PdfViewResolver(ServletContext context) {
		this.context = context;
	}

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		View view = null;
		if (viewName.startsWith("/cnvr/showpEmployee")) {
			System.out.println("fu");
			// 由於要展示如何顯示位於檔案內的資料，所以傳入ServletContext物件以便開啟文字檔。
			view = new MultipleMembersPdfView(context);
			
		} else if (viewName.startsWith("/cnvr/showpAttendence"))  {
			view = new MultipleAttendencePdfView(context);
		} 
		System.out.println("ExcelViewResolver, viewName=" + viewName + ", return value=" + view);
		return view;
      }
	
}