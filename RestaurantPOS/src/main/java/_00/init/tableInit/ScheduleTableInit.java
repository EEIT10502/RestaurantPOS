package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00model.ScheduleBean;

public class ScheduleTableInit {

	private final String UTF8_BOM = "\uFEFF";
	SessionFactory factory;
	String line = "";

	public ScheduleTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initSchedule() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Schedule.dat"); BufferedReader br = new BufferedReader(fr)) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line.substring(1);
				}
				String[] token = line.split("\\|");

				String schedule = token[0];
//				String color = token[1];
				String SstartTime = token[1];
				String SendTime = token[2];//
				String SrestTime = token[3];
//				String StotalTime = token[5];

				Time startTime = strToTime(SstartTime);
				Time endTime = strToTime(SendTime);
				Float restTime = strToFloat(SrestTime);
//				Float totalTime = strToFloat(StotalTime);

				ScheduleBean scheduleBean = new ScheduleBean();

				scheduleBean.setSchedule(schedule);
//				scheduleBean.setColor(color);
				scheduleBean.setStartTime(startTime);
				scheduleBean.setEndTime(endTime);
				scheduleBean.setRestTime(restTime);
//				scheduleBean.setTotalTime(totalTime);

				session.save(scheduleBean);

			}
			tx.commit();
			System.out.println("Schedule資料新建成功!");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Schedule資料新建失敗!" + e.getMessage());
			tx.rollback();
		}

	}

	public Time strToTime(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Time date = new java.sql.Time(d.getTime());
		return date;
	}

	public Float strToFloat(String strDouble) {
		String str = strDouble;
		float value = Float.parseFloat(str);
		return value;
	}

	
}
