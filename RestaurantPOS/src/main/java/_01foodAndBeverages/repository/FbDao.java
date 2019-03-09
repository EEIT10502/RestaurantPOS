package _01foodAndBeverages.repository;

import java.util.List;

import _00model.MenuBean;



public interface FbDao {

	public List<MenuBean> getAllProducts() ;
	
	public List<MenuBean> getProductByCategory(String cate);
	
	MenuBean getProductById(Integer pId);
	
}
