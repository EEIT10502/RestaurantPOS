<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/final.css">


<title>結帳</title>
</head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>

<script>
function ShowTime()
{
    var NowDate = new Date();
    var d = NowDate.getDay();
    var dayNames = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    document.getElementById('showbox').innerHTML = '目前時間：' + NowDate.toLocaleString() + '（' + dayNames[d] + '）';
    setTimeout('ShowTime()', 1000);
}

//收現找零計算
$(function(){
	$('input#oReceived').on('blur',function() {
		var received = parseInt($('#oReceived').val());
		var totalAmount = parseInt(${totalAmount});
		var change = received - totalAmount ;
		$('#oChange').html(change + "元");
	});
});


</script>

<body onload="ShowTime()">
	<form:form method="post" action="/RestaurantPOS/order/confirmPayment" modelAttribute="orderForm">
	
		<div class="container-fluid">
			<!-- 標頭 -->
			<div class="row">
				<div class="col-md-1">
					<a href="<spring:url value='/outfield/modifyOrder' />">
					<input type="button" value="上一步" id="pBack" name="pBack">
					</a>
				</div>
				<div class="col-md-8">
					<h4 style="text-align: center">結帳頁面</h4>
					<input type="submit" value="結帳" id="oNext" name="oNext">
				</div>
				<div class="col-md-3" id="showbox">
					<!-- 系統時間 --> 
				</div>
			</div>
			</div>
			<!-- 點餐部分 -->
			<div class="row">
				<!-- 左方清單 -->
				<div class="col-md-8">
					<table style="margin: 0px auto" width="100%" border="1"
						style="table-layout:fixed">
						<tr>
							<th>項目</th>
							<th>價格</th>
							<th>數量</th>
							<th>小計</th>
							<th>備註</th>
						</tr>
						<c:forEach var='orderVos' items='${orderVos}' varStatus="idx">
							<tr>	
								<td>
									${orderVos.itemName}
									<input type="hidden" id="itemName${idx.index}" name="orderVos1[${idx.index}].itemName" value="${orderVos.itemName}">
								</td>
								<td>
								    ${orderVos.price}
								    <input type="hidden" id="price${idx.index}" name="orderVos1[${idx.index}].price" value="${orderVos.price}">
								</td>
								<td>
									${orderVos.qty}
									 <input type="hidden" id="qty${idx.index}" name="orderVos1[${idx.index}].qty" value="${orderVos.qty}">
								</td>
								<td>
									${orderVos.subTotal}
									 <input type="hidden" id="subTotal${idx.index}" name="orderVos1[${idx.index}].subTotal" value="${orderVos.subTotal}">
									 <input type="hidden" id="category${idx.index}" name="orderVos1[${idx.index}].category" value="${orderVos.category}">
									 <input type="hidden" id="productNo${idx.index}" name="orderVos1[${idx.index}].productNo" value="${orderVos.productNo}">
								</td>
							
								<td></td>
							</tr>
						</c:forEach>
						<tr>
							<th colspan="3" style="text-align: right">總金額：</th>
							<td colspan="2" id="oTotal" name="oTotal">
								${totalAmount}
								<input type="hidden" id="totalAmount" name="totalAmount" value="${totalAmount}">
							</td>
						</tr>
						<tr>
							<td colspan="3" style="text-align: right">
								用餐人數：${cusFlow}位
								<input type="hidden" id="cusFlow" name="cusFlow" value="${cusFlow}">
							</td>
							<td colspan="2" style="text-align: right">
								叫號機號碼：${callNo}號
								<input type="hidden" id="callNo" name="callNo" value="${callNo}">
							</td>
						</tr>
						<tr>
							<th colspan="3" style="text-align: right">收：</th>
							<td colspan="2" >
							<input type="text" id="oReceived" name="oReceived">元
							</td>
						</tr>
						<tr>
							<th colspan="3" style="text-align: right">找零：</th>
							<td colspan="2" id="oChange" name="oChange"></td>
						</tr>
					</table>
				</div>
			</div>
	</form:form>

</body>
</html>