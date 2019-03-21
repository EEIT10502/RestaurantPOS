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

	int getTotalPages();

	void setCurrentPageNo(int currentPageNo);

	int getCurrentPageNo();

	void setItemsPerPage(int itemsPerPage);

	int getItemsPerPage();

	List<MenuBean> getProductsListGetByPage();

	void setSearchBarString(String searchBarString);

	List<MenuBean> getProductsListGetBySearch();

	int getTotalPagesBySearch();

	void setCateSelect(String cateSelect);

	List<MenuBean> getProductsListGetByCate();

	int getTotalPagesByCate();

	List<MenuBean> getProductsListGetByProductStatus();

	int getTotalPagesByProductStatus();

	void setStatusSelect(String statusSelect);

	void updateMenu(MenuBean menuBean);

	List<MenuBean> getAllProductsListGetByCate();

	List<MenuBean> getAllProductsListGetByProductStatus();

	List<MenuBean> getAllProductsListGetBySearch();
}
