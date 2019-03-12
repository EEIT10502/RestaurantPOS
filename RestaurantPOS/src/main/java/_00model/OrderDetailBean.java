package _00model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetail")
public class OrderDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer orderDetailId;
	String  category;
	Integer productNo;
	String  productName;
	Integer qty;
	String  specialReq;
	Integer productPrice;
	
	private OrderBean orderBean;
	
	
	
	
	public OrderDetailBean() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderDetailId")
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	
	


	@Column(columnDefinition="NVARCHAR(50) NOT NULL",name="category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(columnDefinition="INT NOT NULL",name="productNo")
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	
	@Column(columnDefinition="NVARCHAR(50) NOT NULL",name="productName")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(columnDefinition="INT NOT NULL",name="qty")
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	@Column(columnDefinition="NVARCHAR(30)",name="specialReq")
	public String getSpecialReq() {
		return specialReq;
	}
	public void setSpecialReq(String specialReq) {
		this.specialReq = specialReq;
	}
	
	@Column(columnDefinition="INT NOT NULL",name="productPrice")
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FK_OrderBean_Id")  
	public OrderBean getOrderBean() {
		return orderBean;
	}
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	
	
	

}
