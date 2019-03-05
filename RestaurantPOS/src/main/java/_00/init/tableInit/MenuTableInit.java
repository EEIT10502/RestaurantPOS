package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00model.MenuBean;

public class MenuTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public MenuTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initMenu() {
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Menu.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
					
				
				
				String SproductNo = token[0];
				Integer productNo = Integer.parseInt(SproductNo);
				
				String productName =  token[1];
				
				String Sprice = token[2];
				Integer price = Integer.parseInt(Sprice);
				
				String cate = token[3];
				
				String productStatus = token[4];
				
				MenuBean menuBean = new MenuBean();
				
				
				menuBean.setProductNo(productNo);
				menuBean.setProductName(productName);
				menuBean.setPrice(price);
				menuBean.setCate(cate);
				menuBean.setProductStatus(productStatus);
				
				session.save(menuBean);
//				City city = new City();
//				city.setCityName(cityName);
//				city.setCityArea(cityArea);
//
//				session.save(city);

			}
			tx.commit();
			System.out.println("Menu資料新增成功");
		} catch (Exception e) {
			System.err.println("新建Menu表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}

	}

}
