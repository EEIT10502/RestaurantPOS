package _05financial.repository;

import java.util.List;

import _00model.CumulativeTurnoverBean;
import _00model.MenuBean;
import _00model.OrderBean;
import _00model.TargetTurnoverBean;

public interface FinancialDao {

	public void stringToDate(String Date1, String Date2);

	public List<OrderBean[]> getOrderByDate(String Date1, String Date2);
	
	public List<CumulativeTurnoverBean> getCumulativeTurnoverByDate(String Date1, String Date2);
	
	public List<MenuBean> getMenuCate();
	
	public List<OrderBean[]> getCateByDate(String Date1, String Date2, String Cate);
	
	public List<CumulativeTurnoverBean> getCumulativeTurnoverByDate2(String Date1);
	
	public List<TargetTurnoverBean> getTargetTurnoverBeanByDate(String Date1);

}
