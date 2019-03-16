package _01foodAndBeverages.service;

import java.util.List;

import org.springframework.stereotype.Service;

import _00model.MenuBean;
import _00model.OrderBean;



public interface FbService {
	List<MenuBean>  getAllProducts(); 
	List<MenuBean> getProductByCategory(String cate);
	MenuBean getProductById(Integer pId);
	MenuBean getProductListByName(String productName);
	void insertOrder(OrderBean ob);
}
