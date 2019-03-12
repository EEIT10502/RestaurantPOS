package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00model.TargetTurnoverBean;

public class TargetTurnoverTableInit {

	private final String UTF8_BOM="\uFEFF";
	SessionFactory factory;
	String line = "";
	
	public TargetTurnoverTableInit(SessionFactory factory) {
		this.factory = factory;
	}
	
	public void initTargetTurnover() {
		
		
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try (FileReader fr = new FileReader("data/TargetTurnover.dat");BufferedReader br = new BufferedReader(fr)){
			
			tx = session.beginTransaction();
			while((line=br.readLine())!=null) {
				if(line.startsWith(UTF8_BOM)) {
					line.substring(1);
				}
				String[] token = line.split("\\|");
				TargetTurnoverBean targetTurnover = new TargetTurnoverBean();
				
				targetTurnover.setDate(token[0]);
				targetTurnover.setTargetTurnover(Integer.valueOf(token[1]));
				
				session.save(targetTurnover);
			}
			tx.commit();
			System.out.println("TargetTurnover資料新增成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("TargetTurnover資料新增失敗"+e.getMessage());
			tx.rollback();
		}
		
		
		
		
	}
	
	
}
