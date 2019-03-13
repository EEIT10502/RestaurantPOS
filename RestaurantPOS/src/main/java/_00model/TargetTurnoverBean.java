package _00model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TargetTurnover")
public class TargetTurnoverBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Integer id;
	String date;
	Integer targetTurnover;

	public TargetTurnoverBean() {

	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(columnDefinition="VARCHAR(7) NOT NULL",name="date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(columnDefinition="INT NOT NULL",name="targetTurnover")
	public Integer getTargetTurnover() {
		return targetTurnover;
	}

	public void setTargetTurnover(Integer targetTurnover) {
		this.targetTurnover = targetTurnover;
	}
	
	
	

}
