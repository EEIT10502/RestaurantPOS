package _00model;

import java.io.Serializable;
import java.sql.Time;
import java.util.LinkedHashSet;
import java.util.Set;

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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer scheduleId;
	String schedule;
	String color;
	Time startTime;
	Time endTime;
	Time restTime;
	Integer totalTime;

	/*-----------------------------------新增班表資料-----------------------------------*/

	public ScheduleBean(Integer scheduleId,
	String schedule,
	String color,
	Time startTime,
	Time endTime,
	Time restTime,
	Integer totalTime) {
		this.scheduleId = scheduleId;
		this.schedule = schedule;
		this.color = color;
		this.startTime = startTime;
		this.endTime = endTime;
		this.restTime = restTime;
		this.totalTime = totalTime;
	}


	
	public ScheduleBean() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getscheduleId() {
		return scheduleId;
	}

	public void setscheduleId(Integer scheduleId) {
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

	@Column(columnDefinition = "TIME NOT NULL", name = "startTime")
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@Column(columnDefinition = "TIME NOT NULL", name = "endTime")
	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	@Column(columnDefinition = "TIME NOT NULL", name = "restTime")
	public Time getRestTime() {
		return restTime;
	}

	public void setRestTime(Time restTime) {
		this.restTime = restTime;
	}

	@Column(columnDefinition = "Integer NOT NULL", name = "totalTime")
	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

}
