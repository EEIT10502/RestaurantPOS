package _00model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Schedule")
public class ScheduleBean {
	
	Integer scheduleId;	
	Date date;	
	String empNo;	
	String empName;	
	Integer choose;	
	Date startTime;	
	Date endTime;	

	

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


	@Column(columnDefinition="Date NOT NULL",name="date")
	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}


	@Column(columnDefinition="VARCHAR(10) NOT NULL",name="empNo")
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



	@Column(columnDefinition="INT NOT NULL",name="choose")
	public Integer getChoose() {
		return choose;
	}



	public void setChoose(Integer choose) {
		this.choose = choose;
	}



	@Column(columnDefinition="TIME NOT NULL",name="startTime")
	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	@Column(columnDefinition="TIME NOT NULL",name="endTime")
	public Date getEndTime() {
		return endTime;
	}



	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

}
