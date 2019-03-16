package _06manager.service;

import java.util.List;
import java.util.Map;

import _00model.CumulativeTurnoverBean;
import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _00model.TargetTurnoverBean;

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
	
	/**
	 * 根據傳入的orderNo傳回一個OrderBean
	 * @param orderNo 訂單號碼 不要傳orderId 要傳orderNo
	 * @return 回傳一個OrderBean
	 */
	public OrderBean getOrderBeanByOrderNo(String orderNo);
	/**
	 * 取得最新的一筆CumulativeTurnoverBean
	 * @return 回傳一個CumulativeTurnoverBean
	 */
	public CumulativeTurnoverBean getLastCumulativeTurnoverBean();
	/**key:value
	 * key:來客數
	 * key:翻桌率
	 * key:客平均消費
	 * @param date 傳入一個格式是yyyy-MM-dd的日期字串
	 * @return 回傳一個Map物件  
	 */
	public Map<String, Object> getDayCheckAnalysisDate(String date);
	/**
	 * 取得含有當月份目標營業額的TargetTurnoverBean
	 * @return 一個TargetTurnoverBean
	 */
	public TargetTurnoverBean getMonthTarget();
	/**
	 * 透過傳入的時間字串取得一筆CumulativeTurnoverBean (字串格式:yyyy-MM-dd)
	 * @param date 時間字串
	 * @return 一個CumulativeTurnoverBean
	 */
	public CumulativeTurnoverBean getCumulativeTurnoverByDate(String date);
	
}
