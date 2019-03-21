package _00model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;


@Entity
@Table(name = "Calendar")
public class CalendarBean implements Serializable {

	private static final long serialVersionUID = 1L;

	Integer calendarId;
	Date date;
	EmployeeBean employee;
	ScheduleBean schedule;
	String day1;
	String day2;
	String day3;
	String day4;
	String day5;
	String day6;
	String day7;
	
	public CalendarBean() {

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@ManyToOne //(cascade=CascadeType.ALL)不加，會要求update employee表單所有值
	@JoinColumn(name="empId")
	public EmployeeBean getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}
	@ManyToOne
	@JoinColumn(name="scheduleId")
	public ScheduleBean getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduleBean schedule) {
		this.schedule = schedule;
	}

	public String getDay1() {
		return day1;
	}

	public void setDay1(String day1) {
		this.day1 = day1;
	}

	public String getDay2() {
		return day2;
	}

	public void setDay2(String day2) {
		this.day2 = day2;
	}

	public String getDay3() {
		return day3;
	}

	public void setDay3(String day3) {
		this.day3 = day3;
	}

	public String getDay4() {
		return day4;
	}

	public void setDay4(String day4) {
		this.day4 = day4;
	}

	public String getDay5() {
		return day5;
	}

	public void setDay5(String day5) {
		this.day5 = day5;
	}

	public String getDay6() {
		return day6;
	}

	public void setDay6(String day6) {
		this.day6 = day6;
	}

	public String getDay7() {
		return day7;
	}

	public void setDay7(String day7) {
		this.day7 = day7;
	}
	
}
	