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
@Table(name = "CumulativeTurnover")
public class CumulativeTurnoverBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	Integer id;
	Date date;
	Integer turnover;
	Integer moneyReceived;
	Integer shortoverAmount;
	Integer cumulativeTurnover;

	public CumulativeTurnoverBean() {

	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	@Column(columnDefinition="date NOT NULL",name="date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@Column(columnDefinition="INT NOT NULL",name="turnOver")
	public Integer getTurnover() {
		return turnover;
	}

	public void setTurnover(Integer turnover) {
		this.turnover = turnover;
	}
	@Column(columnDefinition="INT NOT NULL",name="moneyReceived")
	public Integer getMoneyReceived() {
		return moneyReceived;
	}

	public void setMoneyReceived(Integer moneyReceived) {
		this.moneyReceived = moneyReceived;
	}
	@Column(columnDefinition="INT",name="shortOverAmount")
	public Integer getShortoverAmount() {
		return shortoverAmount;
	}

	public void setShortoverAmount(Integer shortoverAmount) {
		this.shortoverAmount = shortoverAmount;
	}
	@Column(columnDefinition="INT NOT NULL",name="cumulativeTurnover")
	public Integer getCumulativeTurnover() {
		return cumulativeTurnover;
	}

	public void setCumulativeTurnover(Integer cumulativeTurnover) {
		this.cumulativeTurnover = cumulativeTurnover;
	}
	
	
	
}
