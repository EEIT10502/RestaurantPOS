package _06manager.repository;

import java.util.List;

import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;

public interface ManagerDao {
	
	//給管理登入頁面使用的檢驗功能
	public ManagerBean checkIDPassword(String mAccount,String mPwd);
	
	//找出資料庫中現在最大的orderId
	public int getMaxOrderId();
	
	//按照orderId 取回一個List<OrderDetailBean> 給列印功能使用
	public List<OrderDetailBean> queryODetailById(int orderId);
	
	//找出資料庫中最後一筆Order資料
	public OrderBean getLastOrderBean();
}
