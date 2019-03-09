package _05financial.repository.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import _00model.OrderBean;
import _05financial.repository.FinancialDao;

public class FinancialDaoImpl implements FinancialDao{

	@Autowired
	SessionFactory factory;
	public SessionFactory getFactory() {
		return factory;
	}
	
	//getOrder
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getAllOrder() {
		Session session = factory.getCurrentSession();
		String hql="FROM Order";
		List<OrderBean> list = session.createQuery(hql).getResultList();
		System.out.println("getAllOrder is here!");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getOrderByDate(Timestamp Date1, Timestamp Date2) {
		String hql= "FROM OrderBean o WHERE o.Date1 = :Date1";
		Session session = factory.getCurrentSession();
		List<OrderBean> list = session.createQuery(hql).setParameter("Date1", Date1).getResultList();
		return list;
	}
	

}
