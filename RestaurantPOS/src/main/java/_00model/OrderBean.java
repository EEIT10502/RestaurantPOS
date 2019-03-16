package _00model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;


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



	public OrderBean() {
		
	}
	
	public OrderBean(Integer orderId,String orderNo,Integer cusFlow,Timestamp orderTime,Integer totalPrice
			,String callNo) {
		this.orderId=orderId;
		this.orderNo=orderNo;
		this.cusFlow=cusFlow;
		this.orderTime=orderTime;
		this.totalPrice=totalPrice;
		this.callNo=callNo;
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

	@Column(columnDefinition="VARCHAR(14) NOT NULL",name="orderNo",unique=true)
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
	

	@OneToMany(mappedBy="orderBean",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	public Set<OrderDetailBean> getOrderDetailBean() {
		return OrderDetailBean;
	}

	public void setOrderDetailBean(Set<OrderDetailBean> orderDetailBean) {
		OrderDetailBean = orderDetailBean;
	}
	
	

	

	
	
	
	
	
}
