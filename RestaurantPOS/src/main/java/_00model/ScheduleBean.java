package _00model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule")
public class ScheduleBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer scheduleId;
	Date date;
	String empNo;
	String empName;
	Integer choose;
	Time startTime;
	Time endTime;

	public ScheduleBean() {

	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Column(columnDefinition = "Date NOT NULL", name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(columnDefinition = "VARCHAR(10) NOT NULL", name = "empNo")
	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	@Column(columnDefinition = "NVARCHAR(50) NOT NULL", name = "empName")
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(columnDefinition = "INT NOT NULL", name = "choose")
	public Integer getChoose() {
		return choose;
	}

	public void setChoose(Integer choose) {
		this.choose = choose;
	}

	@Column(columnDefinition = "TIME NOT NULL", name = "startTime")
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

}
