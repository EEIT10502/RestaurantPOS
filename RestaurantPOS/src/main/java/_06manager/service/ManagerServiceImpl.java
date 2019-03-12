package _06manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.ManagerBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
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
	
	@Transactional
	@Override
	public List<OrderDetailBean> queryODetailById(int orderId) {
		
		return dao.queryODetailById(orderId);
	}
	
	@Transactional
	@Override
	public int getMaxOrderId() {
		
		return dao.getMaxOrderId();
	}
	
	@Transactional
	@Override
	public OrderBean getLastOrderBean() {
		
		return dao.getLastOrderBean();
	}

}
