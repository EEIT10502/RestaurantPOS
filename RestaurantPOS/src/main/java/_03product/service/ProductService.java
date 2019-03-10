package _03product.service;

import java.util.List;

import _00model.MenuBean;

public interface ProductService {
	List<String> getAllCategories();

	Integer getCurrentCategoryNumber();

	void addProduct(MenuBean menuItem);
}