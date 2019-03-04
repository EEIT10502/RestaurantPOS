package _00model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Employee")
public class EmployeeBean {
	
	Integer empId;
	String empNo;
	String empName;
	String gender;
	String position;
	String tel;
	String addr;
	String remark;
	String status;
	Blob img;


	public EmployeeBean() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="empId")
	public Integer getEmpId() {
		return empId;
	}


	public void setEmpId(Integer empId) {
		this.empId = empId;
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

	@Column(columnDefinition="NVARCHAR(4) NOT NULL",name="gender")
	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(columnDefinition="NVARCHAR(10) NOT NULL",name="position")
	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}

	@Column(columnDefinition="VARCHAR(12) NOT NULL",name="tel")
	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(columnDefinition="NVARCHAR(50) NOT NULL",name="addr")
	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(columnDefinition="NVARCHAR(50)",name="remark")
	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(columnDefinition="NVARCHAR(10) NOT NULL",name="status")
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Column(columnDefinition="varbinary(MAX) NOT NULL",name="img")
	public Blob getImg() {
		return img;
	}


	public void setImg(Blob img) {
		this.img = img;
	}
	
	
	
	

}
