package _03product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.MenuBean;
import _03product.repository.ProductDao;
import _03product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productdao;

	@Transactional
	@Override
	public List<String> getAllCategories() {

		return productdao.getAllCategories();
	}

	
	@Transactional
	@Override
	public void addProduct(MenuBean menuItem) {
		productdao.addProduct(menuItem);
	}

	@Transactional
	@Override
	public Integer getCurrentCategoryNumber(String cateInsert) {
		return productdao.getCurrentCategoryNumber(cateInsert);
	}

	@Transactional
	@Override
	public List<MenuBean> getAllProducts() {
		return productdao.getAllProducts();
	}

	@Transactional
	@Override
	public List<MenuBean> getAllProductsListTestRice() {
		return productdao.getAllProductsListTestRice();
	}

	@Transactional
	@Override
	public List<MenuBean> getAllProductsListTestSoup() {
		return productdao.getAllProductsListTestSoup();
	}

	@Transactional
	@Override
	public List<MenuBean> getAllProductsListTestDessert() {
		return productdao.getAllProductsListTestDessert();
	}

	
}
