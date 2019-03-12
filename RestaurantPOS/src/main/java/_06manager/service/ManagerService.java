package _06manager.service;

import java.util.List;

import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;

public interface ManagerService {
	
	/**
	 * 
	 * @param mAccount 管理登入頁面輸入的帳號
	 * @param mPwd 管理登入頁面輸入的密碼
	 * @return 驗證成功則回傳一個ManagerBean物件
	 */
	public ManagerBean checkIDPassword(String mAccount,String mPwd);
	/**
	 * 
	 * @param orderId 透過傳入ID查找出相對應的OrderDetail
	 * @return 回傳一個List<OrderDetailBean>
	 */
	public List<OrderDetailBean> queryODetailById(int orderId);
	/**
	 * 取得最新的OrderId
	 * @return
	 */
	public int getMaxOrderId();
	
	/**
	 * 取得一個最新的、最後的OrderBean
	 * @return 一個OrderBean
	 */
	public OrderBean getLastOrderBean();
}
