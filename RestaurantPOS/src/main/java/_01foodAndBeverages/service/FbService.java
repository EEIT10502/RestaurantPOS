package _01foodAndBeverages.service;

import java.util.List;

import org.springframework.stereotype.Service;

import _00model.MenuBean;



public interface FbService {
	List<MenuBean>  getAllProducts(); 
	List<MenuBean> getProductByCategory(String cate);
	MenuBean getProductById(Integer pId);
	Integer getProductPriceByName(String productName);
}
