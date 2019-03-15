package _00.init.tableInit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Blob;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.init.util.SystemUtils2018;
import _00model.EmployeeBean;

public class EmployeeTableInit {

	
	SessionFactory factory;
	String line ="";
	public static final String UTF8_BOM = "\uFEFF";
	
	public EmployeeTableInit(SessionFactory factory) {
		this.factory = factory;
	}
	
	public void InitEmployee() {
		
		
		 Session session = factory.getCurrentSession();
		 Transaction tx = null;
		 
		 
		 try (FileReader fr = new FileReader("data/Employee.dat");BufferedReader br = new BufferedReader(fr)){
			tx = session.beginTransaction();
			while((line=br.readLine())!=null) {
				if(line.startsWith(UTF8_BOM)) {
					line.substring(1);
				}
				 String[] token= line.split("\\|");
				 
				 String addr = token[0];
				 String empName = token[1];
				 
				 String empNo = token[2];
//				 Integer empNo = Integer.parseInt(SempNo);
//				 Integer empNo = Integer.parseInt(SempNo);
				 
				 String gender = token[3];
				 String Simg = token[4];
				 String position = token[5];
				 String remark = token[6];
				 String status = token[7];
				 String tel = token[8];
				
			     Blob img = SystemUtils2018.fileToBlob(Simg.trim());
				
			     EmployeeBean employee = new EmployeeBean();
			     
				employee.setAddr(addr);
				employee.setEmpName(empName);
				employee.setEmpNo(empNo);
				employee.setGender(gender);
			    employee.setImg(img);
			    employee.setPosition(position);
			    employee.setRemark(remark);
			    employee.setStatus(status);
			    employee.setTel(tel);
			    
			    session.save(employee);
			
				 
				 
			}
			 tx.commit();
			 System.out.println("Employee表資料新增成功");
			 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Employee表資料新增失敗");
			 tx.rollback();
		}
	}
}
