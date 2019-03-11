package _02employee.repository;

import _00model.ManagerBean;

public interface  EmployeeDao {

	public ManagerBean checkIDPassword(String mAccount,String mPwd);
	
	
	
}
