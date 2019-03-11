package _01foodAndBeverages.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.MenuBean;
import _01foodAndBeverages.repository.FbDao;
import _01foodAndBeverages.service.FbService;


@Service
public class FbServiceImpl implements FbService {

	@Autowired
	FbDao dao;
	
	@Transactional
	public List<MenuBean> getAllProducts() {
		List<MenuBean> list = null;
		list = dao.getAllProducts();
		return  list;
	}

	@Transactional
	@Override
	public List<MenuBean> getProductByCategory(String cate) {
		
		return dao.getProductByCategory(cate);
	}

	@Transactional
	@Override
	public MenuBean getProductById(Integer pId) {
		return dao.getProductById(pId);
	}
	
	@Transactional
	@Override
	public Integer getProductPriceByName(String productName) {
		
		return dao.getProductPriceByName(productName);
	}
	
	

	
}
