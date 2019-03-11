package _05financial.repository.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.CumulativeTurnoverBean;
import _00model.OrderBean;
import _05financial.repository.FinancialDao;

@Repository
public class FinancialDaoImpl implements FinancialDao {
	java.util.Date uDate1 = null;
	java.util.Date uDate2 = null;

	@Autowired
	SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	// String to Date
	@Override
	public void stringToDate(String Date1, String Date2) {
		String tDate1 = Date1 + " 00:00:00";
		String tDate2 = Date2 + " 23:59:59";
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// to util.Date
		try {
			uDate1 = fDate.parse(tDate1);
			uDate2 = fDate.parse(tDate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// dailyReport
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean[]> getOrderByDate(String Date1, String Date2) {
		// to sql.Date
		stringToDate(Date1, Date2);
		java.sql.Date beginDate = new java.sql.Date(uDate1.getTime());
		java.sql.Date endDate = new java.sql.Date(uDate2.getTime());
		// hql
		String hql = "select cast(orderTime as date) as orderTime, count(totalPrice), sum(o.cusFlow), sum(o.totalPrice) FROM OrderBean o WHERE o.orderTime>=:beginDate and o.orderTime<=:endDate GROUP BY cast(o.orderTime as date)";
		Session session = factory.getCurrentSession();

		List<OrderBean[]> listDailyOrder = session.createQuery(hql).setParameter("beginDate", beginDate)
				.setParameter("endDate", endDate).getResultList();

		return listDailyOrder;

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CumulativeTurnoverBean[]> getCumulativeTurnover(String Date1, String Date2) {
		// to sql.Date
		stringToDate(Date1, Date2);
		java.sql.Date beginDate = new java.sql.Date(uDate1.getTime());
		java.sql.Date endDate = new java.sql.Date(uDate2.getTime());
		// hql
		String hql = "select cast(orderTime as date) as orderTime, count(totalPrice), sum(o.cusFlow), sum(o.totalPrice) FROM OrderBean o WHERE o.orderTime>=:beginDate and o.orderTime<=:endDate GROUP BY cast(o.orderTime as date)";
		Session session = factory.getCurrentSession();

		List<CumulativeTurnoverBean[]> listDailyCumu = session.createQuery(hql).setParameter("beginDate", beginDate)
				.setParameter("endDate", endDate).getResultList();

		return listDailyCumu;
	}

}
