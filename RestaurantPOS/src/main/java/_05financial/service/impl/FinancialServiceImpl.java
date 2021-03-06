package _05financial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.CumulativeTurnoverBean;
import _00model.MenuBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _00model.TargetTurnoverBean;
import _05financial.repository.FinancialDao;
import _05financial.service.FinancialService;

@Service
public class FinancialServiceImpl implements FinancialService {

	@Autowired
	FinancialDao dao;

	@Transactional
	@Override
	public List<OrderBean[]> getOrderByDate(String Date1, String Date2) {
		return dao.getOrderByDate(Date1, Date2);
	}

	@Transactional
	@Override
	public List<CumulativeTurnoverBean> getCumulativeTurnoverByDate(String Date1, String Date2) {
		return dao.getCumulativeTurnoverByDate(Date1, Date2);
	}

	@Transactional
	@Override
	public List<MenuBean> getMenuCate() {
		return dao.getMenuCate();
	}

	@Transactional
	@Override
	public List<OrderDetailBean[]> getCateByDate(String Date1, String Date2, String Cate) {
		return dao.getCateByDate(Date1, Date2, Cate);
	}

	@Transactional
	@Override
	public List<MenuBean> getMenuProductByCate(String Cate) {
		return dao.getMenuProductByCate(Cate);
	}

	@Transactional
	@Override
	public List<OrderBean[]> getProductByDate(String Date1, String Date2, String Product) {
		return dao.getProductByDate(Date1, Date2, Product);
	}

	@Transactional
	@Override
	public List<CumulativeTurnoverBean> getCumulativeTurnoverByDate2(String Date1) {
		return dao.getCumulativeTurnoverByDate2(Date1);
	}

	@Transactional
	@Override
	public List<TargetTurnoverBean> getTargetTurnoverBeanByDate(String Date1) {
		return dao.getTargetTurnoverBeanByDate(Date1);
	}
}
