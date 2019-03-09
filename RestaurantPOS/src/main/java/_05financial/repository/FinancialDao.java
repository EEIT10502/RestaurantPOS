package _05financial.repository;

import java.util.List;

import _00model.OrderBean;

public interface FinancialDao {

	public List<OrderBean> getOrderByDate(String Date1, String Date2);
}
