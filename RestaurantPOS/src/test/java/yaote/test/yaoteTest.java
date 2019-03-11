package yaote.test;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class yaoteTest {

	public static void main(String[] args) {
		yaoteTest yao =new yaoteTest();
//        new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//		
		
//		String date = new Date().toString();
//		
//		System.out.println("Date:"+date);
//		Date dd1 = yao.strToDate(date);
//		System.out.println("dd1:"+dd1);
		String today=yao.getDateTime();
		System.out.println("today:"+today);
		
		String time1=yao.getDateTime2();
		System.out.println("time1:"+time1);
		
		Date date = yao.strToDate(today);
		System.out.println("date:"+date);
		
		Time time = yao.strToTime(time1);
		System.out.println("time:"+time);
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
	public String getDateTime() {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = format.format(date);
		
		return strDate;
		
	}
	public String getDateTime2() {
		
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		String strDate = format.format(date);
		
		return strDate;
		
	}

}
