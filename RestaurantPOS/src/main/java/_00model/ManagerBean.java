package _00model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Manager")
public class ManagerBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Integer managerNo;
	String account;
	String password;


	public ManagerBean() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="managerNo")
	public Integer getManagerNo() {
		return managerNo;
	}


	public void setManagerNo(Integer managerNo) {
		this.managerNo = managerNo;
	}

	@Column(columnDefinition="VARCHAR(12) NOT NULL",name="account")
	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}

	@Column(columnDefinition="VARCHAR(12) NOT NULL",name="password")
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
