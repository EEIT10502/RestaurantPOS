package _03product.repository;

import java.util.List;

import _00model.MenuBean;

public interface ProductDao {
	List<String> getAllCategories();
	List<String> getProductStatusList();

	void addProduct(MenuBean menuItem);
}
