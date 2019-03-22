package _07dailyClosing.repository.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.CumulativeTurnoverBean;
import _07dailyClosing.repository.DailyClosingDao;

@Repository
public class DailyClosingDaoImpl implements DailyClosingDao {
	@Autowired
	SessionFactory factory;

	java.util.Date uDate1 = null;
	java.util.Date uDate2 = null;
	java.sql.Date beginDate = null;
	java.sql.Date endDate = null;
	
	// String to Date
	@Override
	public void stringToDate(String date) {
		String tDate1 = date + " 00:00:00";
		String tDate2 = date + " 23:59:59";
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// to util.Date

		try {
			uDate1 = fDate.parse(tDate1);
			uDate2 = fDate.parse(tDate2);
			beginDate = new java.sql.Date(uDate1.getTime());
			endDate = new java.sql.Date(uDate2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer getTodayTurnover(String date) {
		stringToDate(date);

		try {
			String hql = "SELECT SUM(totalPrice) FROM OrderBean WHERE orderTime>=:beginDate and orderTime<=:endDate";	
			Session session = factory.getCurrentSession();
			long todayTurnoverTypeLong = (long) session.createQuery(hql)
					.setParameter("beginDate", beginDate)
					.setParameter("endDate", endDate)
					.uniqueResult();
			Integer todayTurnover = (int) todayTurnoverTypeLong;
			return todayTurnover;
		} catch (java.lang.NullPointerException e) {
			System.out.println("本日截至目前沒有賺到錢錢~QQ");
			return null;
		}
	}
	
	@Override
	public Integer getMaxIdNumber() {
		String hql = "SELECT MAX(id) FROM CumulativeTurnoverBean";	
		Session session = factory.getCurrentSession();
		Integer maxIdNumber = (Integer) session.createQuery(hql).uniqueResult();
		
		return maxIdNumber;
	}
	
	@Override
	public Integer getCTPrevious() {
		Integer maxIdNumber = getMaxIdNumber();
		String hql = "SELECT cumulativeTurnover FROM CumulativeTurnoverBean WHERE id = ?0";	
		Session session = factory.getCurrentSession();
		Integer getCTPrevious;
		if(maxIdNumber == null) {
			int zero = 0;
			getCTPrevious = zero;
		}else {
			getCTPrevious = (Integer) session.createQuery(hql)
					.setParameter(0, maxIdNumber)
					.uniqueResult();
		}

		return getCTPrevious;
	}
	
	@Override
	public void addDailyClosing(CumulativeTurnoverBean cumulativeTurnoverBean) {
		Session session = factory.getCurrentSession();
		session.save(cumulativeTurnoverBean);
	}

	@Override
	public Integer completeCheckToday(java.sql.Date today) {
		String hql = "select (id) from CumulativeTurnoverBean where date = '"+today+"'";
		Session session = factory.getCurrentSession();
		Integer yesterdayPK = (Integer) session.createQuery(hql)
//				.setParameter(0, maxIdNumber)
				.uniqueResult();
		return yesterdayPK;
	}
	
	@Override
	public CumulativeTurnoverBean getAllById(Integer id) {
//		Integer maxIdNumber = getMaxIdNumber();
		String hql = "FROM CumulativeTurnoverBean WHERE id = ?0";	
		Session session = factory.getCurrentSession();
		CumulativeTurnoverBean cumulativeTurnoverBean = (CumulativeTurnoverBean) session.createQuery(hql)
				.setParameter(0, id)
				.uniqueResult();

		return cumulativeTurnoverBean;
	}



}
