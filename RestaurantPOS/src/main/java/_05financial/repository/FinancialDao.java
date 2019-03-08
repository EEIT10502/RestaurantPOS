package _05financial.repository;

import java.sql.Timestamp;
import java.util.List;

import _00model.OrderBean;

public interface FinancialDao {
	
	public List<OrderBean> getAllOrder();
	
	public List<OrderBean> getOrderByDate(Timestamp Date1, Timestamp Date2);
	
}
