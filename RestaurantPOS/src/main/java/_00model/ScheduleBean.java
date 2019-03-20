package _00model;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule")
public class ScheduleBean implements Serializable {
	private static final long serialVersionUID = 1L;

	Integer scheduleId;
	String schedule;
	String color;
	Time startTime;
	Time endTime;
	Double restTime;
	Double totalTime;

	public ScheduleBean() {
	}

	public ScheduleBean(Integer scheduleId, String schedule, String color, Time startTime, Time endTime, Double restTime,
			Double totalTime) {
		this.scheduleId = scheduleId;
		this.schedule = schedule;
		this.color = color;
		this.startTime = startTime;
		this.endTime = endTime;
		this.restTime = restTime;
		this.totalTime = totalTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Column(columnDefinition = "NVARCHAR(50) NOT NULL", name = "schedule")
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@Column(columnDefinition = "NVARCHAR(50) NOT NULL", name = "color")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "startTime")
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime")
	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Column(name = "restTime")
	public Double getRestTime() {
		return restTime;
	}

	public void setRestTime(Double restTime) {
		this.restTime = restTime;
	}

	@Column(name = "totalTime")
	public Double getTotalTime()throws ParseException {
//		SimpleDateFormat simpleFormat = new SimpleDateFormat("HH:mm");
//		String endTime=simpleFormat.format(getEndTime());
//		String startTime=simpleFormat.format(getStartTime());
//		long end=simpleFormat.parse(endTime).getTime();
//		long start=simpleFormat.parse(startTime).getTime();
//		long diff=(end-start);
//		double days=diff/(1000*60*60*24);
//		double hours=(diff-days*(1000*60*60*24)/(1000*60*60));
//		double dayhours=hours-getRestTime();
//		
//		return dayhours;
		return totalTime;
	}

	public void setTotalTime(Double totalTime)throws ParseException {
		this.totalTime = totalTime;
	}

}
