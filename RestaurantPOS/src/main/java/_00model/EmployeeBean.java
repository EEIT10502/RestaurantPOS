package _00model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Employee")
public class EmployeeBean implements Serializable {



	public EmployeeBean(Integer empId, Integer empNo, String empName,  String position,String gender, String tel,
			String addr,String status, String remark,  Blob img, MultipartFile empImg) {
		super();
		this.empId = empId;
		this.empNo = empNo;
		this.empName = empName;
		this.gender = gender;
		this.position = position;
		this.tel = tel;
		this.addr = addr;
		this.remark = remark;
		this.status = status;
		this.img = img;
		this.empImg = empImg;
	}

	private static final long serialVersionUID = 1L;

	Integer empId;
	Integer empNo;
	String empName;
	String gender;
	String position;
	String tel;
	String addr;
	String remark;
	String status;
	Blob img;
	private MultipartFile empImg;
	//Calendar
	Set<CalendarBean> CalendarBean = new LinkedHashSet<>();
	
	
	private String  	fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@XmlTransient
	@Transient
	public MultipartFile getEmpImg() {
		return empImg;
	}

	public void setEmpImg(MultipartFile empImg) {
		this.empImg = empImg;
	}

	public EmployeeBean() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empId")
	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	@Column(columnDefinition = "INT NOT NULL", name = "empNo")
	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	@Column(columnDefinition = "NVARCHAR(50) NOT NULL", name = "empName")
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(columnDefinition = "NVARCHAR(4) NOT NULL", name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(columnDefinition = "NVARCHAR(10) NOT NULL", name = "position")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(columnDefinition = "VARCHAR(12) NOT NULL", name = "tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(columnDefinition = "NVARCHAR(50) NOT NULL", name = "addr")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(columnDefinition = "NVARCHAR(50)", name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(columnDefinition = "NVARCHAR(10) NOT NULL", name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(columnDefinition = "varbinary(MAX) NOT NULL", name = "img")
	public Blob getImg() {
		return img;
	}

	public void setImg(Blob img) {
		this.img = img;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeBean [empId=");
		builder.append(empId);
		builder.append(", empNo=");
		builder.append(empNo);
		builder.append(", empName=");
		builder.append(empName);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", position=");
		builder.append(position);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", addr=");
		builder.append(addr);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", status=");
		builder.append(status);
		builder.append(", img=");
		builder.append(img);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", empImg=");
		builder.append(empImg);
		builder.append("]");
		return builder.toString();
	}
	
	//對應到Calendar
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="empId")
	public Set<CalendarBean> getCalendarBean() {
		return CalendarBean;
	}

	public void setCalendarBean(Set<CalendarBean> calendarBean) {
		CalendarBean = calendarBean;
	}

	

	

	
	
	
	
	

}
