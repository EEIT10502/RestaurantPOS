package _00model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "Attendence")
@XmlRootElement(name="attendence")
@XmlAccessorType(XmlAccessType.FIELD)
public class AttendenceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	Integer attendenceId;
	String empNo;
	String empName;
	Date date;
	String checkStatus;
	Time clockTime;

	public AttendenceBean() {

	}

	public AttendenceBean(String empNo, Date date, String checkStatus, Time clockTime) {
		this.empNo = empNo;
		this.date = date;
		this.checkStatus = checkStatus;
		this.clockTime = clockTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAttendenceId() {
		return attendenceId;
	}

	public void setAttendenceId(Integer attendenceId) {
		this.attendenceId = attendenceId;
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
	
	@Column(columnDefinition = "VARCHAR(10) NOT NULL", name = "empName")
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Time getClockTime() {
		return clockTime;
	}

	public void setClockTime(Time clockTime) {
		this.clockTime = clockTime;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

}
