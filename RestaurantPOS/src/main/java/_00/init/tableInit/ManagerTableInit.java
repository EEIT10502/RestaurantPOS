package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00model.ManagerBean;

public class ManagerTableInit {

	
	SessionFactory factroy;
	String line="";
	public static final String UTF8_BOM="\uFEFF";
	
	
	public ManagerTableInit(SessionFactory factroy) {
		this.factroy = factroy;
	}
	public void initManager() {
		Session session = factroy.getCurrentSession();
		Transaction tx = null;
		
		
		try (FileReader fr = new FileReader("data/Manager.dat");BufferedReader br = new BufferedReader(fr)){
			tx = session.beginTransaction();
			while((line = br.readLine())!=null) {
				if(line.startsWith(UTF8_BOM)) {
					line.substring(1);
					
				}
				String[] token = line.split("\\|");
				
				String account = token[0];
				String password = token[1];
				
				ManagerBean manager = new ManagerBean();
				manager.setAccount(account);
				manager.setPassword(password);
				session.save(manager);
				
			}
			tx.commit();
			System.out.println("Manager 資料新增成功");
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			System.out.println("Manager 資料新增失敗");
		}
		
	}
	
}
