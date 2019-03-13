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

	@Transactional
	@Override
	public int getTotalPages() {
		return productdao.getTotalPages();
	}

	@Transactional
	@Override
	public List<MenuBean> getProductsListGetByPage() {
		return productdao.getProductsListGetByPage();
	}

	@Transactional
	@Override
	public int getItemsPerPage() {
		return productdao.getItemsPerPage();
	}

	@Transactional
	@Override
	public void setItemsPerPage(int itemsPerPage) {
		productdao.setItemsPerPage(itemsPerPage);
	}

	@Transactional
	@Override
	public int getCurrentPageNo() {
		return productdao.getCurrentPageNo();
	}

	@Transactional
	@Override
	public void setCurrentPageNo(int currentPageNo) {
		productdao.setCurrentPageNo(currentPageNo);
	}

	@Transactional
	@Override
	public void setSearchBarString(String searchBarString) {
		System.out.println("Service searchBarString:"+searchBarString);
		productdao.setSearchBarString(searchBarString);
	}

	@Transactional
	@Override
	public List<MenuBean> getProductsListGetBySearch() {
		return productdao.getProductsListGetBySearch();
	}

	@Transactional
	@Override
	public int getTotalPagesBySearch() {
		return productdao.getTotalPagesBySearch();
	}
	
//	public static void  main (String[] args) {
//		ProductService service = new ProductServiceImpl();
//		List<MenuBean> a = service.getProductsListGetBySearch();
//		System.out.println("a:"+a);
//	}
	
	@Transactional
	@Override
	public void setCateSelect(String cateSelect) {
		System.out.println("Service cateSelect:"+cateSelect);
		productdao.setCateSelect(cateSelect);
	}
	
	@Transactional
	@Override
	public List<MenuBean> getProductsListGetByCate() {
		return productdao.getProductsListGetByCate();
	}
	
	@Transactional
	@Override
	public int getTotalPagesByCate() {
		return productdao.getTotalPagesByCate();
	}
	
	@Transactional
	@Override
	public void setStatusSelect(String statusSelect) {
		System.out.println("Service statusSelect:"+statusSelect);
		productdao.setStatusSelect(statusSelect);
	}
	
	@Transactional
	@Override
	public List<MenuBean> getProductsListGetByProductStatus() {
		return productdao.getProductsListGetByProductStatus();
	}
	
	@Transactional
	@Override
	public int getTotalPagesByProductStatus() {
		return productdao.getTotalPagesByProductStatus();
	}
	
	@Transactional
	@Override
	public void updateMenu(MenuBean menuBean) {
		productdao.updateMenu(menuBean);
	}
}
