package _02employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.ManagerBean;
import _02employee.repository.EmployeeDao;
import _02employee.service.EmployeeService;


@Service
public class EmployeeServiceImpl  implements EmployeeService{
	
	
	@Autowired
	EmployeeDao dao;
	
	public EmployeeServiceImpl() {
		
	}
	
	@Transactional
	@Override
	public ManagerBean checkIDPassword(String mAccount, String mPwd) {
		
		ManagerBean managerBean =null;
		
		managerBean = dao.checkIDPassword(mAccount, mPwd);
		
		return managerBean;
	}

}
