package _06manager.repository;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.reflect.FastConstructor;
import org.springframework.stereotype.Repository;

import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;


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
		if(orderDetailList!=null) {
			System.out.println("取得資料");
		}else {
			System.out.println("無法取得資料");
		}
		
		
		return orderDetailList;
	}

	//找出資料庫Orders的orderId最大值
	@Override
	public int getMaxOrderId() {
		
		int maxOrderId = 0;
		
		String hql="SELECT MAX(orderId) FROM OrderBean";
		Session session = factory.getCurrentSession();
		maxOrderId =(Integer)session.createQuery(hql).uniqueResult();
		
		if(maxOrderId==0) {
			System.out.println("取不到值");
		}
		
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
	
	
	

}
