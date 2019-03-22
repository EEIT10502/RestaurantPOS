package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00model.CalendarBean;
import _00model.EmployeeBean;

public class CalendarTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public CalendarTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initCalendar() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Calendar.dat"); BufferedReader br = new BufferedReader(fr)) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line.substring(1);
				}
				String[] token = line.split("\\|");

				CalendarBean calendar = new CalendarBean();
				String hql = "FROM EmployeeBean where empName = :empName";
				EmployeeBean cb=(EmployeeBean)session.createQuery(hql)
						.setParameter("empName", token[0].trim())
						.getSingleResult();
				
				calendar.setEmployee(cb);
				
				String day1 = token[1];
				String day2 = token[2];
				String day3 = token[3];
				String day4 = token[4];
				String day5 = token[5];
				String day6 = token[6];
				String day7 = token[7];
				
				calendar.setDay1(day1);
				calendar.setDay2(day2);
				calendar.setDay3(day3);
				calendar.setDay4(day4);
				calendar.setDay5(day5);
				calendar.setDay6(day6);
				calendar.setDay7(day7);
				
				session.save(calendar);

			}
			tx.commit();
			System.out.println("Employee表資料新增成功");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Employee表資料新增失敗");
			tx.rollback();
		}
	}

	public Date strToDate(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(d.getTime());
		return date;
	}
}
