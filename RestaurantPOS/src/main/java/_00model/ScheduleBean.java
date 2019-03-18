package _00model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

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
	Timestamp startTime;
	Timestamp endTime;
	Time restTime;
	Integer totalTime;

	public ScheduleBean() {
	}

	public ScheduleBean(Integer scheduleId, String schedule, String color, Timestamp startTime, Timestamp endTime, Time restTime,
			Integer totalTime) {
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
	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime")
	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
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
