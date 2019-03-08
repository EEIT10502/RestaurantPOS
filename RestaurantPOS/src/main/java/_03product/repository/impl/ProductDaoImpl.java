package _03product.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
		Integer CurrentCategoryNumber = (Integer) session.createQuery(hql).setParameter(0,cateInsert).uniqueResult();
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
		allProductsListTestRice = session.createQuery(hql).setParameter(0,GlobalService.Product_Cate_Rice).getResultList();
		return allProductsListTestRice;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getAllProductsListTestSoup() {
		String hql = "From MenuBean Where cate = ?0 Order By productNo";
		Session session = factory.getCurrentSession();
		List<MenuBean> allProductsListTestSoup = new ArrayList<>();
		allProductsListTestSoup = session.createQuery(hql).setParameter(0,GlobalService.Product_Cate_Soup).getResultList();
		return allProductsListTestSoup;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getAllProductsListTestDessert() {
		String hql = "From MenuBean Where cate = ?0 Order By productNo";
		Session session = factory.getCurrentSession();
		List<MenuBean> allProductsListTestDessert = new ArrayList<>();
		allProductsListTestDessert = session.createQuery(hql).setParameter(0,GlobalService.Product_Cate_Dessert).getResultList();
		return allProductsListTestDessert;
	}


}
