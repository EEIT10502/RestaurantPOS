package _03product.service;

import java.util.List;

import _00model.MenuBean;

public interface ProductService {
	List<String> getAllCategories();

	List<MenuBean> getAllProducts();

	List<MenuBean> getAllProductsListTestRice();

	List<MenuBean> getAllProductsListTestSoup();

	List<MenuBean> getAllProductsListTestDessert();

	Integer getCurrentCategoryNumber(String cateInsert);

	void addProduct(MenuBean menuItem);
}
