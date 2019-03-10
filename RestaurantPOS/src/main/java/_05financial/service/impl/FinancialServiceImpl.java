package _05financial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.OrderBean;
import _05financial.repository.FinancialDao;
import _05financial.service.FinancialService;

@Service
public class FinancialServiceImpl implements FinancialService {

	@Autowired
	FinancialDao dao;

	@Transactional
	@Override
	public List<OrderBean> getOrderByDate(String Date1, String Date2) {

		return dao.getOrderByDate(Date1, Date2);
	}

}
