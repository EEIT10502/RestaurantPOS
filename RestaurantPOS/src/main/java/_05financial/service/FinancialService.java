package _05financial.service;

import java.util.List;

import _00model.OrderBean;

public interface FinancialService {
	public List<OrderBean[]> getOrderByDate(String Date1, String Date2);
}
