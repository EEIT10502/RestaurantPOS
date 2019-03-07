package _03product.repository;

import java.util.List;

import _00model.MenuBean;

public interface ProductDao {
	List<String> getAllCategories();

	String getCurrentCategoryNumber();

	void addProduct(MenuBean menuItem);
}
