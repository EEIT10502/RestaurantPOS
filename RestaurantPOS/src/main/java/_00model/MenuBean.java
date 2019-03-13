package _00model;


import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Menu")
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer pId;
	Integer productNo;
	String productName;
	Integer price;
	String cate;
	String productStatus;
	
	
	
	public MenuBean() {
		
	}
	
	public MenuBean(Integer pId, Integer productNo, String productName, Integer price, String cate,
			String productStatus) {
		super();
		this.pId = pId;
		this.productNo = productNo;
		this.productName = productName;
		this.price = price;
		this.cate = cate;
		this.productStatus = productStatus;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pId")
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
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
	
	@Column(columnDefinition="INT NOT NULL",name="price")
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Column(columnDefinition="NVARCHAR(50) NOT NULL",name="cate")
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	
	@Column(columnDefinition="NVARCHAR(10) NOT NULL",name="productStatus")
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

}
