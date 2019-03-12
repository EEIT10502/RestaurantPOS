package _06manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.ManagerBean;
import _06manager.repository.ManagerDao;


@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	ManagerDao dao;
	
	public ManagerServiceImpl() {
		
	}

	@Transactional
	@Override
	public ManagerBean checkIDPassword(String mAccount, String mPwd) {
		
		ManagerBean managerBean = null;
		
		managerBean = dao.checkIDPassword(mAccount, mPwd);
		
		return managerBean;
	}

}
