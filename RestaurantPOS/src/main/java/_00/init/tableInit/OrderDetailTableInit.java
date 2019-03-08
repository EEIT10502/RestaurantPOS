package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00model.OrderDetailBean;

public class OrderDetailTableInit {

	private final String UTF8_BOM = "\uFEFF";
	SessionFactory factory;
	String line ="";
	
	
	public OrderDetailTableInit(SessionFactory factory) {
		this.factory = factory;
		
		
	}

	public void initOrderDetail() {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try(FileReader fr=new FileReader("data/OrderDetail.dat");BufferedReader br= new BufferedReader(fr)){
			 tx = session.beginTransaction();
			while((line=br.readLine())!=null) {
				if(line.startsWith(UTF8_BOM)) {
					line.substring(1);
				}
				String[] token = line.split("\\|");
				OrderDetailBean orderDetail = new OrderDetailBean();
				orderDetail.setOrderNo(Integer.valueOf(token[0]));
				orderDetail.setCategory(token[1]);
				orderDetail.setProductNo(Integer.valueOf(token[2]));
				orderDetail.setProductName(token[3]);
				orderDetail.setQty(Integer.valueOf(token[4]));
				orderDetail.setSpecialReq(token[5]);
				orderDetail.setProductPrice(Integer.valueOf(token[6]));
				
				session.save(orderDetail);
				
			}
			tx.commit();
			System.out.println("OrderDetail資料新建成功!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("OrderDetail資料新建失敗"+e.getMessage());
			tx.rollback();
		}
	}
}
