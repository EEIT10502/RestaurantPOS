
package _00.init;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc

@ComponentScan(basePackages = { "_01foodAndBeverages", "_02employee", "_03product", "_04schedule", "_05financial",
		"_00.init", "_06manager", "_07dailyClosing" })
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	ServletContext context;

	@Override
	// 本方法會自動產生一個ContentNegotiationManager
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// 如果Spring無法由延伸檔名、請求標頭判斷出使用者要求的沒有型態，
		// 就使用MediaType.APPLICATION_JSON為預設型態。
		// configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

		resolvers.add(jspViewResolver());
		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	@Bean
	public ViewResolver jspViewResolver() {
		System.out.println("jspViewResolver");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/JSP/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/views/fonts/");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/views/images/");
		registry.addResourceHandler("/assets/plugins/bootstrap/css/**").addResourceLocations("/WEB-INF/views/assets/plugins/bootstrap/css/");
		registry.addResourceHandler("/assets/plugins/bootstrap/js/**").addResourceLocations("/WEB-INF/views/assets/plugins/bootstrap/js/");
		registry.addResourceHandler("/assets/plugins/jquery/**").addResourceLocations("/WEB-INF/views/assets/plugins/jquery/");
		/// http://localhost:8080/RestaurantPOS/assets/plugins/jquery/jquery.min.js net::ERR_ABORTED 404
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;

	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// 定義檔案上傳所需要的Bean
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(81920000);
		return resolver;

	}

}
