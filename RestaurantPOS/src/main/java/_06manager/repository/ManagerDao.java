package _06manager.repository;

import java.util.List;
import java.util.Map;

import _00model.CumulativeTurnoverBean;
import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _00model.TargetTurnoverBean;

public interface ManagerDao {
	
	//給管理登入頁面使用的檢驗功能
	public ManagerBean checkIDPassword(String mAccount,String mPwd);
	
	//找出資料庫中現在最大的orderId
	public int getMaxOrderId();
	
	//按照orderId 取回一個List<OrderDetailBean> 給列印功能使用
	public List<OrderDetailBean> queryODetailById(int orderId);
	
	//找出資料庫中最新一筆Order資料
	public OrderBean getLastOrderBean();
	
	//按照orderNo 取回一個OrderBean給列印功能使用
	public OrderBean getOrderBeanByOrderNo(String orderNo);
	
	//從CumulativeTurnover中得到最新一筆資料
	public CumulativeTurnoverBean getLastCumulativeTurnoverBean();
	
	//依照傳入的日期字串從OrderBean 取得數據分析(今日來客數、客平均消費、翻桌率) 日期格式 yyyy-MM-dd
	public Map<String,Object> getDayCheckAnalysisDate(String date);

	//取得當月份目標營業額
	public TargetTurnoverBean getMonthTarget();
	
	//依照傳入的日期字串從CumulativeTurnover中得到一筆資料
	public CumulativeTurnoverBean getCumulativeTurnoverByDate(String date);
	
	//取得從今日往前推一個月內的所有CumulativeTurnoverBean
	public List<CumulativeTurnoverBean> getOneMonthCumulativeTurnoverBean();
	
	//SAVE 一個TargetTurnoverBean
	public void saveTTB(TargetTurnoverBean TTB);
}
