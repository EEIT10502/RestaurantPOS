package _07dailyClosing.repository;

import java.sql.Date;

import _00model.CumulativeTurnoverBean;

public interface DailyClosingDao {

	void stringToDate(String Date);

	Integer getTodayTurnover(String date);

	Integer getMaxIdNumber();

	Integer getCTPrevious();

	void addDailyClosing(CumulativeTurnoverBean cumulativeTurnoverBean);



	Integer completeCheckToday(Date today);

	CumulativeTurnoverBean getAllById(Integer id);





}
