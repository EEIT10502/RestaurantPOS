package _05financial.service;

import java.util.List;

import _00model.CumulativeTurnoverBean;
import _00model.MenuBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _00model.TargetTurnoverBean;

public interface FinancialService {
	// daily
	public List<OrderBean[]> getOrderByDate(String Date1, String Date2);

	public List<CumulativeTurnoverBean> getCumulativeTurnoverByDate(String Date1, String Date2);
	// category
	public List<MenuBean> getMenuCate();

	public List<OrderDetailBean[]> getCateByDate(String Date1, String Date2, String Cate);
	// product
	public List<MenuBean> getMenuProductByCate(String Cate);

	public List<OrderBean[]> getProductByDate(String Date1, String Date2, String Product);
	//goal
	public List<CumulativeTurnoverBean> getCumulativeTurnoverByDate2(String Date1);

	public List<TargetTurnoverBean> getTargetTurnoverBeanByDate(String Date1);

}
