package _07dailyClosing.service;

import java.sql.Date;

import _00model.CumulativeTurnoverBean;

public interface DailyClosingService {

	Integer getTodayTurnover(String date);

	Integer getCTPrevious();

	void addDailyClosing(CumulativeTurnoverBean cumulativeTurnoverBean);



	Integer completeCheckToday(Date today);

	CumulativeTurnoverBean getAllById(Integer id);



}
