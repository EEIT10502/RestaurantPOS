package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00model.AttendenceBean;

public class AttendenceTableInit {

	private final String UTF8_BOM="\uFEFF";
	SessionFactory factory;
	String line ="";
	
	
	public AttendenceTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initAttendence() {
		
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try (FileReader fr= new FileReader("data/Attendence.dat");BufferedReader br = new BufferedReader(fr)){
			tx = session.beginTransaction();
			while((line=br.readLine())!=null) {
				if(line.startsWith(UTF8_BOM)) {
					line.substring(1);
				}
				String[] token = line.split("\\|");
				
				
				String empNo = token[0];
				String Sdate= token[1];
				String checkStatus = token[2];
				String SclockTime = token[3];//
				String empName=token[4];
				
				Date date = strToDate(Sdate);
				Time clockTime = strToTime(SclockTime);
				
				AttendenceBean attendence = new AttendenceBean();
				
				attendence.setEmpNo(empNo);//
				attendence.setDate(date);
				attendence.setCheckStatus(checkStatus);
				attendence.setClockTime(clockTime);
				attendence.setEmpName(empName);
				
				
				session.save(attendence);
				
			}
			tx.commit();
			System.out.println("Attendence資料新建成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Attendence資料新建失敗!"+e.getMessage());
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
}
