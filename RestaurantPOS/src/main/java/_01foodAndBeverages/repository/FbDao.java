package _01foodAndBeverages.repository;

import java.util.List;

import _00model.MenuBean;
import _00model.OrderBean;



public interface FbDao {

	public List<MenuBean> getAllProducts() ;
	
	public List<MenuBean> getProductByCategory(String cate);
	
	MenuBean getProductById(Integer pId);
	
	MenuBean getProductListByName(String productName);
	
	void insertOrder(OrderBean ob);
	
}
