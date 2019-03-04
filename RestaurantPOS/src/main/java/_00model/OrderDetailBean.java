package _00model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetail")
public class OrderDetailBean {

	
	Integer orderDetailId;
	String itemNo;
	Integer orderNo;
	String category;
	Integer productNo;
	String productName;
	Integer qty;
	String specialReq;
	Integer productPrice;
	
	
	
	
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
	
	@Column(columnDefinition="VARCHAR(4) NOT NULL",name="itemNo")
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String orderDetailNo) {
		this.itemNo = orderDetailNo;
	}
	
	@Column(columnDefinition="VARCHAR(12) NOT NULL",name="orderNo")
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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

	
	
	

}
