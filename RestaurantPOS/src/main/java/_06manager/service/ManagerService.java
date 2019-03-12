package _06manager.service;

import java.util.List;

import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;

public interface ManagerService {
	
	public ManagerBean checkIDPassword(String mAccount,String mPwd);
	
	public List<OrderDetailBean> queryODetailById(int orderId);
	
	public int getMaxOrderId();
	
	
	public OrderBean getLastOrderBean();
}
