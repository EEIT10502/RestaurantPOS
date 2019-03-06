package _00model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Orders")
public class OrderBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Integer orderId;
	String orderNo;
	Integer cusFlow;
	Timestamp  orderTime;
	Integer totalPrice;
	String callNo;
	
	Set<OrderDetailBean> OrderDetailBean = new LinkedHashSet<>();

	public OrderBean(Integer orderId,String orderNo,Integer cusFlow,Timestamp orderTime,Integer totalPrice
			,String callNo) {
		this.orderId=orderId;
		this.orderNo=orderNo;
		this.cusFlow=cusFlow;
		this.orderTime=orderTime;
		this.totalPrice=totalPrice;
		this.callNo=callNo;
	}
	
	
	
	public OrderBean() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderId")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(columnDefinition="VARCHAR(12) NOT NULL",name="orderNo")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Column(columnDefinition="INT NOT NULL",name="cusFlow")
	public Integer getCusFlow() {
		return cusFlow;
	}

	public void setCusFlow(Integer cusFlow) {
		this.cusFlow = cusFlow;
	}
	
	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	@Column(columnDefinition="INT NOT NULL",name="totalPrice")
	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(columnDefinition="VARCHAR(4) NOT NULL",name="callNo")
	public String getCallNo() {
		return callNo;
	}

	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_orderNo", referencedColumnName="orderNo")
	public Set<OrderDetailBean> getOrderDetailBean() {
		return OrderDetailBean;
	}

	public void setOrderDetailBean(Set<OrderDetailBean> orderDetailBean) {
		OrderDetailBean = orderDetailBean;
	}
	
	

	

	
	
	
	
	
}
