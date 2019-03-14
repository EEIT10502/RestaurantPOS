package _07dailyClosing.service.impl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _07dailyClosing.repository.DailyClosingDao;
import _07dailyClosing.service.DailyClosingService;

@Service
public class DailyClosingServiceImpl implements DailyClosingService {
	@Autowired
	DailyClosingDao productdao;

	@Transactional
	@Override
	public Integer getTodayTurnover(String date) {

		return productdao.getTodayTurnover(date);
	}
//
//	@Transactional
//	@Override
//	public void addProduct(MenuBean menuItem) {
//		productdao.addProduct(menuItem);
//	}
}
