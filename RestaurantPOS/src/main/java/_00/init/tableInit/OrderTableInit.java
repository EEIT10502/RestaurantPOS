package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00model.OrderBean;

public class OrderTableInit {

	    public static final String UTF8_BOM="\uFEFF";
		SessionFactory factory;
		String line="";
		
		
		public OrderTableInit(SessionFactory factory) {
			this.factory = factory;
		}
	
        public void initOrder() {
        	Session session  = factory.getCurrentSession();
        	Transaction tx = null;
        	
        	try(FileReader fr = new FileReader("data/Order.dat");BufferedReader br = new BufferedReader(fr)) {
        		tx = session.beginTransaction();
        		while((line=br.readLine()) !=null) {
        			if(line.startsWith(UTF8_BOM)) {
        				line.substring(1);
        			}
        			String[] token = line.split("\\|");
        			
        			
        			String orderNo = token[0];
        			Integer cusFlow = Integer.valueOf(token[1]);
        			
        			Timestamp orderTime = Timestamp.valueOf(token[2]);
        			
        			Integer totalPrice =Integer.valueOf(token[3]);
        			String callNo=token[4];
        			
        			
        			OrderBean order = new OrderBean(null, orderNo, cusFlow, orderTime, totalPrice, callNo);
    
        			
        			session.save(order);
        		}
        		System.out.println("--------------------");
        		tx.commit();
        		System.out.println("新增Order資料成功!");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("新增Order資料失敗"+e.getMessage());
				tx.rollback();
			}
        }
        public Date strToDate(String strDate) {
    		String str = strDate;
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
