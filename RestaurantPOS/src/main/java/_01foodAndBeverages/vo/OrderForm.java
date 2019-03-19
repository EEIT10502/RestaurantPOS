package _01foodAndBeverages.vo;

import java.util.List;

public class OrderForm {

	private List<OrderVo> orderVos;
	private List<OrderVo> orderVos1;
	
	private String callNo;
	
	private Integer cusFlow;
	
	private Integer totalAmount;

	
	public List<OrderVo> getOrderVos() {
		return orderVos;
	}

	public void setOrderVos(List<OrderVo> orderVos) {
		this.orderVos = orderVos;
	}

	public String getCallNo() {
		return callNo;
	}

	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}

	public Integer getCusFlow() {
		return cusFlow;
	}

	public void setCusFlow(Integer cusFlow) {
		this.cusFlow = cusFlow;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<OrderVo> getOrderVos1() {
		return orderVos1;
	}

	public void setOrderVos1(List<OrderVo> orderVos1) {
		this.orderVos1 = orderVos1;
	}

}
