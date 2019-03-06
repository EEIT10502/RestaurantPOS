package _05financial.repository;

import _00model.OrderBean;

public interface FinancialDao {
	
	public OrderBean queryOrder(String date);
	
}
