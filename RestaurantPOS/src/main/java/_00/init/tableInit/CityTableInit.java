package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//import com.funwork.model.City;

public class CityTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public CityTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initCity() {
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/City.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}

				String[] token = line.split("\\|");

				String cityName = token[0];
				String cityArea = token[1];

//				City city = new City();
//				city.setCityName(cityName);
//				city.setCityArea(cityArea);
//
//				session.save(city);
			}
			tx.commit();
			System.out.println("City資料新增成功");
		} catch (Exception e) {
			System.err.println("新建City表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}

	}

}
