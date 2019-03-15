package _07dailyClosing.repository;

public interface DailyClosingDao {

	void stringToDate(String Date);

	Integer getTodayTurnover(String date);

}
