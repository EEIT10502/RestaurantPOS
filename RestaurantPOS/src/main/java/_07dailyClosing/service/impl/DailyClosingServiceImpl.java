package _07dailyClosing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00model.CumulativeTurnoverBean;
import _00model.MenuBean;
import _07dailyClosing.repository.DailyClosingDao;
import _07dailyClosing.service.DailyClosingService;

@Service
public class DailyClosingServiceImpl implements DailyClosingService {
	@Autowired
	DailyClosingDao dailyClosingdao;

	@Transactional
	@Override
	public Integer getTodayTurnover(String date) {

		return dailyClosingdao.getTodayTurnover(date);
	}

	@Transactional
	@Override
	public Integer getCTPrevious() {

		return dailyClosingdao.getCTPrevious();
	}

	@Transactional
	@Override
	public void addDailyClosing(CumulativeTurnoverBean cumulativeTurnoverBean) {
		dailyClosingdao.addDailyClosing(cumulativeTurnoverBean);
	}

	@Transactional
	@Override
	public Integer completeCheckToday(java.sql.Date today) {
		return dailyClosingdao.completeCheckToday(today);
	}
	
	@Transactional
	@Override
	public CumulativeTurnoverBean getAllById(Integer id) {
		return dailyClosingdao.getAllById(id);
	}
}
