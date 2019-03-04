package _00model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.crypto.Data;


@Entity
@Table(name = "Salary")
public class SalaryBean {
	
	Integer salaryId;
	Date date;	
	String empNo;	
	String empName;	
	Date totalHours	;
	Integer hourlyWage;
	Integer salary;

	

	public SalaryBean() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getSalaryId() {
		return salaryId;
	}



	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}


	@Column(columnDefinition="date NOT NULL",name="date")
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


	@Column(columnDefinition="Nvarchar(50) NOT NULL",name="empName")
	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}


	@Column(columnDefinition="DATE NOT NULL",name="totalHours")
	public Date getTotalHours() {
		return totalHours;
	}



	public void setTotalHours(Date totalHours) {
		this.totalHours = totalHours;
	}


	@Column(columnDefinition="INT NOT NULL",name="hourlyWage")
	public Integer getHourlyWage() {
		return hourlyWage;
	}



	public void setHourlyWage(Integer hourlyWage) {
		this.hourlyWage = hourlyWage;
	}


	@Column(columnDefinition="INT NOT NULL",name="salary")
	public Integer getSalary() {
		return salary;
	}



	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	
	

}
