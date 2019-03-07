package _05financial.repository.impl;

import java.sql.Timestamp;
import java.util.List;

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

	@Override
	public List<OrderBean> getAllOrder() {
		
		return null;
	}
	

}
