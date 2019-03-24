package _05financial.repository.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.CumulativeTurnoverBean;
import _00model.MenuBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _00model.TargetTurnoverBean;
import _05financial.repository.FinancialDao;

@Repository
public class FinancialDaoImpl implements FinancialDao {
//	java.util.Date uDate1 = null;
//	java.util.Date uDate2 = null;
//	java.sql.Date beginDate = null;
//	java.sql.Date endDate = null;

	@Autowired
	SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	// dailyReport
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean[]> getOrderByDate(String Date1, String Date2) {
		// to sql.Date
		String tDate1 = Date1 + " 00:00:00";
		String tDate2 = Date2 + " 23:59:59";
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate1 = null;
		java.util.Date uDate2 = null;
		try {
			uDate1 = fDate.parse(tDate1);
			uDate2 = fDate.parse(tDate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// hql
		Date beginDate = new java.sql.Date(uDate1.getTime());
		Date endDate = new java.sql.Date(uDate2.getTime());
		String hql = "select cast(orderTime as date), count(totalPrice), sum(o.cusFlow), sum(o.totalPrice) FROM OrderBean o WHERE o.orderTime>=:beginDate and o.orderTime<=:endDate GROUP BY cast(o.orderTime as date)";
		Session session = factory.getCurrentSession();
		List<OrderBean[]> listDailyOrder = session.createQuery(hql).setParameter("beginDate", beginDate)
				.setParameter("endDate", endDate).getResultList();

		return listDailyOrder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CumulativeTurnoverBean> getCumulativeTurnoverByDate(String Date1, String Date2) {
		// to sql.Date
		String tDate1 = Date1 + " 00:00:00";
		String tDate2 = Date2 + " 23:59:59";
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate1 = null;
		java.util.Date uDate2 = null;
		try {
			uDate1 = fDate.parse(tDate1);
			uDate2 = fDate.parse(tDate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date beginDate = new java.sql.Date(uDate1.getTime());
		Date endDate = new java.sql.Date(uDate2.getTime());
		// hql
		String hql = "FROM CumulativeTurnoverBean c WHERE c.date>=:beginDate and c.date<=:endDate";
		Session session = factory.getCurrentSession();
		List<CumulativeTurnoverBean> listDailyCumu = session.createQuery(hql).setParameter("beginDate", beginDate)
				.setParameter("endDate", endDate).getResultList();

		return listDailyCumu;
	}

	// categoryReport
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getMenuCate() {
		String hql = "SELECT cate FROM MenuBean GROUP BY cate";
		Session session = factory.getCurrentSession();
		List<MenuBean> listCateMenu = session.createQuery(hql).getResultList();

		return listCateMenu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetailBean[]> getCateByDate(String Date1, String Date2, String Cate) {
		// to sql.Date
		String tDate1 = Date1 + " 00:00:00";
		String tDate2 = Date2 + " 23:59:59";
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate1 = null;
		java.util.Date uDate2 = null;
		try {
			uDate1 = fDate.parse(tDate1);
			uDate2 = fDate.parse(tDate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// hql
		Date beginDate = new java.sql.Date(uDate1.getTime());
		Date endDate = new java.sql.Date(uDate2.getTime());
		String hql = "SELECT cast(d.orderBean.orderTime as date), sum(qty), sum(productPrice) FROM OrderDetailBean d WHERE d.orderBean.orderTime>=:beginDate and d.orderBean.orderTime<=:endDate and category=:Cate GROUP BY cast(d.orderBean.orderTime as date)";
		Session session = factory.getCurrentSession();
		List<OrderDetailBean[]> listCate = session.createQuery(hql).setParameter("beginDate", beginDate)
				.setParameter("endDate", endDate).setParameter("Cate", Cate).getResultList();

		return listCate;
	}

	// productRport
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getMenuProductByCate(String Cate) {
		String hql = "FROM MenuBean WHERE cate=:Cate";
		Session session = factory.getCurrentSession();
		List<MenuBean> listProductMenu = session.createQuery(hql).setParameter("Cate", Cate).getResultList();

		return listProductMenu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean[]> getProductByDate(String Date1, String Date2, String Product) {
		// to sql.Date
		String tDate1 = Date1 + " 00:00:00";
		String tDate2 = Date2 + " 23:59:59";
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate1 = null;
		java.util.Date uDate2 = null;
		try {
			uDate1 = fDate.parse(tDate1);
			uDate2 = fDate.parse(tDate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// hql
		Date beginDate = new java.sql.Date(uDate1.getTime());
		Date endDate = new java.sql.Date(uDate2.getTime());
		String hql = "SELECT cast(d.orderBean.orderTime as date), sum(qty), sum(productPrice) FROM OrderDetailBean d WHERE d.orderBean.orderTime>=:beginDate and d.orderBean.orderTime<=:endDate and productName=:Product GROUP BY cast(d.orderBean.orderTime as date)";
		Session session = factory.getCurrentSession();
		List<OrderBean[]> listProuct = session.createQuery(hql).setParameter("beginDate", beginDate)
				.setParameter("endDate", endDate).setParameter("Product", Product).getResultList();

		return listProuct;
	}

	// goalReport
	@SuppressWarnings("unchecked")
	@Override
	public List<CumulativeTurnoverBean> getCumulativeTurnoverByDate2(String Date1) {
		// to sql.Date
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDate1 = null;
		try {
			uDate1 = fDate.parse(Date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date beginDate = new java.sql.Date(uDate1.getTime());
		// hql
		String hql = "FROM CumulativeTurnoverBean c WHERE c.date=:beginDate";
		Session session = factory.getCurrentSession();
		List<CumulativeTurnoverBean> listgoalCum = session.createQuery(hql).setParameter("beginDate", beginDate)
				.getResultList();

		return listgoalCum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TargetTurnoverBean> getTargetTurnoverBeanByDate(String Date1) {
		String beginDate = Date1.substring(0, 7);
		// hql
		String hql = "FROM TargetTurnoverBean WHERE date=:beginDate";
		Session session = factory.getCurrentSession();
		List<TargetTurnoverBean> listgoalturn = session.createQuery(hql).setParameter("beginDate", beginDate)
				.getResultList();

		return listgoalturn;
	}

}
