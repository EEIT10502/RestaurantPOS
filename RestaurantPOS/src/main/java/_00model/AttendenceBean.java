package _00model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Attendence")
public class AttendenceBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Integer attendenceId;
	Date date;
	String empNo;
	String empName;
	Date clockIn;
	Date clockOut;
	String totalHours;


	public AttendenceBean() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	public Integer getAttendenceId() {
		return attendenceId;
	}


	public void setAttendenceId(Integer attendenceId) {
		this.attendenceId = attendenceId;
	}

	@Column(columnDefinition="Date NOT NULL",name="date")
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	@Column(columnDefinition="VARCHAR(10) NOT NULL",name="empNO")
	public String getEmpNo() {
		return empNo;
	}


	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	@Column(columnDefinition="NVARCHAR(50) NOT NULL",name="empName")
	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(columnDefinition="datetime",name="clockIn")
	public Date getClockIn() {
		return clockIn;
	}


	public void setClockIn(Date clockIn) {
		this.clockIn = clockIn;
	}

	@Column(columnDefinition="datetime",name="clockOut")
	public Date getClockOut() {
		return clockOut;
	}


	public void setClockOut(Date clockOut) {
		this.clockOut = clockOut;
	}
	
	@Column(columnDefinition="time",name="totalHours")
	public String getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}

	
	
	
}
