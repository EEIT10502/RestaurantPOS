package _06manager.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.reflect.FastConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import _00.init.util.SystemUtils2018;
import _00model.CumulativeTurnoverBean;
import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _00model.TargetTurnoverBean;


@Repository
public class ManagerDaoImpl implements ManagerDao{

	@Autowired
	SessionFactory factory;
	
	
	public ManagerDaoImpl() {
		
	}


	//提供檢查用的
	@Override
	public ManagerBean checkIDPassword(String mAccount, String mPwd) {
		
		ManagerBean managerBean =null;

		Session session = factory.getCurrentSession();
		String hql="FROM ManagerBean m WHERE m.account = :account and m.password = :password";
		try {
			managerBean = (ManagerBean) session.createQuery(hql)
									   .setParameter("account", mAccount)
									   .setParameter("password", mPwd)
									   .uniqueResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			System.out.println("EmployeeDaoImpl checkIDPassword 錯誤!"+e.getMessage());
			managerBean = null;
		}

		return managerBean;
	}


	//按照傳入的orderId，找到其對應的List<OrderDetailBean>
	@Override
	public List<OrderDetailBean> queryODetailById(int orderId) {
		
		String hql="FROM OrderDetailBean o INNER JOIN FETCH o.orderBean m WHERE m.orderId = :orderId";
		
		Session session = factory.getCurrentSession();
		List<OrderDetailBean>orderDetailList = session.createQuery(hql).setParameter("orderId", orderId)
																	   .getResultList();

		return orderDetailList;
	}

	//找出資料庫Orders的orderId最大值
	@Override
	public int getMaxOrderId() {
		
		int maxOrderId = 0;
		
		String hql="SELECT MAX(orderId) FROM OrderBean";
		Session session = factory.getCurrentSession();
		maxOrderId =(Integer)session.createQuery(hql).uniqueResult();
		
		return maxOrderId;
	}

	//取得資料庫中最後一筆OrderBean的資料
	@Override
	public OrderBean getLastOrderBean() {
		
		OrderBean orderBean = null;
		
		String hql = "FROM OrderBean ORDER BY orderId DESC";
		
		Session session = factory.getCurrentSession();
		
		orderBean = (OrderBean) session.createQuery(hql).setMaxResults(1).uniqueResult();
		
		return orderBean;
	}

	//根據傳入的orderNo 取得對應的OrderBean
	@Override
	public OrderBean getOrderBeanByOrderNo(String orderNo) {
		OrderBean orderBean = null;
		
		String hql="FROM OrderBean WHERE orderNo = :orderNo";
		
		Session session = factory.getCurrentSession();
		
		orderBean = (OrderBean) session.createQuery(hql)
									   .setParameter("orderNo", orderNo)
									   .uniqueResult();
		
		return orderBean;
	}

	//取得最新的一筆CumulativeTurnoverBean資料
	@Override
	public CumulativeTurnoverBean getLastCumulativeTurnoverBean() {
		
		CumulativeTurnoverBean CTBean = null;
		
		String hql="FROM CumulativeTurnoverBean ORDER BY id DESC";
		Session session = factory.getCurrentSession();
		
		CTBean = (CumulativeTurnoverBean) session.createQuery(hql).setMaxResults(1).uniqueResult();
		 
		return CTBean;
	}

	//根據傳入的一個日期查出當天的來客數、翻桌率、客平均消費
	@Override
	public Map<String, Object> getDayCheckAnalysisDate(String date) {
		//串接日期字串
		String startTime = date+" 00:00:00";
		String endTime   = date+" 23:59:59";
		
		Map<String, Object>dataMap = null;
		
		String hql ="select new Map(SUM(cusFlow) as 來客數,CAST(COUNT(*) as float)/5 as 翻桌率,SUM(totalPrice)/SUM(cusFlow) as 客平均消費) from OrderBean where orderTime \r\n" + 
				"  between '"+startTime+"' and '"+endTime+"'";
		
		Session session = factory.getCurrentSession();
		dataMap = (Map<String, Object>) session.createQuery(hql).uniqueResult();

		return dataMap;
	}

	//取得當月份的TargetTurnoverBean
	@Override
	public TargetTurnoverBean getMonthTarget() {
		
		TargetTurnoverBean TTBean = null;
		//取得當月份的yyyy-MM格式字串
		String date = SystemUtils2018.getMonth();
		
		String hql ="FROM TargetTurnoverBean WHERE date = :date";
		
		Session session = factory.getCurrentSession();
		TTBean = (TargetTurnoverBean) session.createQuery(hql)
											 .setParameter("date", date)
											 .uniqueResult();
		
		return TTBean;
	}
	
	
	
	

}
