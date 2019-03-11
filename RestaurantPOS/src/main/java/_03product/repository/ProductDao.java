package _03product.repository;

import java.util.List;

import _00model.MenuBean;

public interface ProductDao {
	List<String> getAllCategories();

	List<MenuBean> getAllProducts();

	List<MenuBean> getAllProductsListTestRice();

	List<MenuBean> getAllProductsListTestSoup();

	List<MenuBean> getAllProductsListTestDessert();

	Integer getCurrentCategoryNumber(String cateInsert);

	void addProduct(MenuBean menuItem);

	long getTotalItemCounts();

	int getTotalPages();

	List<MenuBean> getProductsListGetByPage();

	int getItemsPerPage();

	void setItemsPerPage(int itemsPerPage);

	int getCurrentPageNo();

	void setCurrentPageNo(int currentPageNo);

	List<MenuBean> getProductsListGetBySearch();

	void setSearchBarString(String searchBarString);

	long getTotalItemCountsBySearch();

	int getTotalPagesBySearch();

	
}
