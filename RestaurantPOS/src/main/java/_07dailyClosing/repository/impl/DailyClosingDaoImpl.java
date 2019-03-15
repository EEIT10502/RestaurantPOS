package _07dailyClosing.repository.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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

		String hql = "SELECT SUM(totalPrice) FROM OrderBean WHERE orderTime>=:beginDate and orderTime<=:endDate";	
		Session session = factory.getCurrentSession();
		long todayTurnoverTypeLong = (long) session.createQuery(hql)
				.setParameter("beginDate", beginDate)
				.setParameter("endDate", endDate)
				.uniqueResult();
		Integer todayTurnover = (int) todayTurnoverTypeLong;
		System.out.println("todayTurnoverDao:" + todayTurnover);
		return todayTurnover;
	}

}
