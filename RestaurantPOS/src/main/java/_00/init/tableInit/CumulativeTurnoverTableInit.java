package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00model.CumulativeTurnoverBean;

public class CumulativeTurnoverTableInit {

	public static final String UTF8_BOM = "\uFEFF";
	SessionFactory factory;
	String line = "";
	
	
	public CumulativeTurnoverTableInit(SessionFactory factory) {
	this.factory = factory;
	}
	
	public void initCumulativeTurnover() {
		
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try(FileReader fr = new FileReader("data/CumulativeTurnover.dat");BufferedReader br = new BufferedReader(fr)) {
			tx = session.beginTransaction();
			while((line=br.readLine())!=null) {
				if(line.startsWith(UTF8_BOM)) {
					line.substring(1);
				}
				String[] token= line.split("\\|");
				CumulativeTurnoverBean cumulativeTurnover = new CumulativeTurnoverBean();
				
				cumulativeTurnover.setCumulativeTurnover(Integer.valueOf(token[0]));
				
				String Sdate = token[1];
				Date date = strToDate(Sdate);
				cumulativeTurnover.setDate(date);
				
				cumulativeTurnover.setMoneyReceived(Integer.valueOf(token[2]));
				cumulativeTurnover.setShortoverAmount(Integer.valueOf(token[3]));
				cumulativeTurnover.setTurnover(Integer.valueOf(token[4]));
				
				session.save(cumulativeTurnover);
			
			}
			tx.commit();
			System.out.println("CumulativeTurnover資料新增成功!");	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CumulativeTurnover資料新增失敗!"+e.getMessage());
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
