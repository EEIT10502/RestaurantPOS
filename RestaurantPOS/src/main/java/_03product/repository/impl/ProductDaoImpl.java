package _03product.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00.init.util.GlobalService;
import _00model.MenuBean;
import _03product.repository.ProductDao;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllCategories() {
		List<String> list = null;
		String hql = "SELECT DISTINCT cate FROM MenuBean";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).list();
		return list;
	}

	@Override
	public void addProduct(MenuBean menuItem) {
		Session session = factory.getCurrentSession();
		session.save(menuItem);
	}

	@Override
	public Integer getCurrentCategoryNumber(String cateInsert) {
		String hql = "SELECT MAX(productNo) FROM MenuBean Where cate = ?0";
		Session session = factory.getCurrentSession();
		Integer CurrentCategoryNumber = (Integer) session.createQuery(hql).setParameter(0, cateInsert).uniqueResult();
		return CurrentCategoryNumber;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getAllProducts() {
		String hql = "From MenuBean Order By productNo";
		Session session = factory.getCurrentSession();
		List<MenuBean> allProductsList = new ArrayList<>();
		allProductsList = session.createQuery(hql).getResultList();
		return allProductsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getAllProductsListTestRice() {
		String hql = "From MenuBean Where cate = ?0 Order By productNo";
		Session session = factory.getCurrentSession();
		List<MenuBean> allProductsListTestRice = new ArrayList<>();
		allProductsListTestRice = session.createQuery(hql).setParameter(0, GlobalService.Product_Cate_Rice)
				.getResultList();
		return allProductsListTestRice;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getAllProductsListTestSoup() {
		String hql = "From MenuBean Where cate = ?0 Order By productNo";
		Session session = factory.getCurrentSession();
		List<MenuBean> allProductsListTestSoup = new ArrayList<>();
		allProductsListTestSoup = session.createQuery(hql).setParameter(0, GlobalService.Product_Cate_Soup)
				.getResultList();
		return allProductsListTestSoup;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getAllProductsListTestDessert() {
		String hql = "From MenuBean Where cate = ?0 Order By productNo";
		Session session = factory.getCurrentSession();
		List<MenuBean> allProductsListTestDessert = new ArrayList<>();
		allProductsListTestDessert = session.createQuery(hql).setParameter(0, GlobalService.PRODUCT_CATE_NOODLE)
				.getResultList();
		return allProductsListTestDessert;
	}

	private int itemsPerPage = GlobalService.ITEMS_PER_PAGE;

	@Override
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	@Override
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	private int currentPageNo = 1;

	@Override
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	@Override
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getProductsListGetByPage() {
		String hql = "From MenuBean Order By productNo";
		int startItemNo = (currentPageNo - 1) * itemsPerPage;
		Session session = factory.getCurrentSession();
		List<MenuBean> ProductsListGetByPage = new ArrayList<>();
		ProductsListGetByPage = session.createQuery(hql).setFirstResult(startItemNo).setMaxResults(itemsPerPage)
				.getResultList();
		return ProductsListGetByPage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getTotalItemCounts() { // 所有產品的個數
		long count = 0;
		String hql = "SELECT count(*) FROM MenuBean";
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql).list();
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	private int totalPages = -1;

	// 計算販售的商品總共有幾頁
	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getTotalItemCounts() / (double) itemsPerPage));

		return totalPages;
	}

	private String searchBarString = null;

	public String getSearchBarString() {
		return searchBarString;
	}

	public void setSearchBarString(String searchBarString) {
		this.searchBarString = searchBarString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getProductsListGetBySearch() {
		System.out.println("DAO searchBarString:"+searchBarString);
		String hql = "From MenuBean where productName like :key Order By productNo";
//		String hql = "From MenuBean where productName like '%"+searchBarString+"%' Order By productNo";
		int startItemNo = (currentPageNo - 1) * itemsPerPage;
		Session session = factory.getCurrentSession();
		List<MenuBean> ProductsListGetBySearch = new ArrayList<>();
		ProductsListGetBySearch = session.createQuery(hql)
				.setParameter("key", '%'+searchBarString+'%')
				.setFirstResult(startItemNo)
				.setMaxResults(itemsPerPage)
				.getResultList();
		return ProductsListGetBySearch;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public long getTotalItemCountsBySearch() { // 所有產品的個數
		long count = 0;
		String hql = "SELECT count(*) FROM MenuBean where productName like :key";
//		String hql = "SELECT count(*) FROM MenuBean where productName like '%"+searchBarString+"%'";
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql)
				.setParameter("key", '%'+searchBarString+'%')
				.list();
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	private int totalPagesBySearch = -1;

	// 計算販售的商品總共有幾頁
	@Override
	public int getTotalPagesBySearch() {
		// 注意下一列敘述的每一個型態轉換
		totalPagesBySearch = (int) (Math.ceil(getTotalItemCountsBySearch() / (double) itemsPerPage));

		return totalPagesBySearch;
	}
}
