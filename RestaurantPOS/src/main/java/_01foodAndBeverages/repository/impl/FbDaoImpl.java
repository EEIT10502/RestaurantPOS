package _01foodAndBeverages.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import _00model.MenuBean;
import _01foodAndBeverages.repository.FbDao;

@Repository
public class FbDaoImpl implements FbDao {

	@Autowired
	SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getAllProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM MenuBean";
		List<MenuBean> list = session.createQuery(hql).getResultList();
		// List<BookBean> list2 = session.createQuery(hql).list();
		
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getProductByCategory(String cate) {
		String hql= "FROM MenuBean b WHERE b.cate = :cate";
		Session session = factory.getCurrentSession();
		List<MenuBean> list = session.createQuery(hql).setParameter("cate", cate).getResultList();
		return list;
	}
	
	
	

}
