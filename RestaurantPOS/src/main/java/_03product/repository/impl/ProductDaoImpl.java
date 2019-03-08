package _03product.repository.impl;

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
	public Integer getCurrentCategoryNumber() {
		String hql = "SELECT MAX(productNo) FROM MenuBean";
		Session session = factory.getCurrentSession();
		Integer CurrentCategoryNumber = (Integer) session.createQuery(hql).uniqueResult();
		return CurrentCategoryNumber;
	}


}
