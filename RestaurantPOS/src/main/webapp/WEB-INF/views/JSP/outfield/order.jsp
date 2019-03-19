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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mynumkb.css">
<title>點餐</title>
	<style type="text/css">
	.hiddenList {
	display: none;
}
	</style>
</head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script	src="<c:url value="/js/orders.js"/>"></script>
<script>
	 function ShowTime()
     {
         var NowDate = new Date();
         var d = NowDate.getDay();
         var dayNames = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
         document.getElementById('showbox').innerHTML = '目前時間：' + NowDate.toLocaleString() + '（' + dayNames[d] + '）';
         setTimeout('ShowTime()', 1000);
     }
</script>

<body onload="ShowTime()">
	<form:form id="dataForm" method="post" action="/RestaurantPOS/order/payment" modelAttribute="orderForm">
<%-- 	<form action="/RestaurantPOS/order/payment" method="post" > --%>
		<div class="container-fluid">
			<!-- 標頭 -->
			<div class="row">
				<div class="col-md-9">
					<h4 style="text-align: center">點餐頁面</h4>
				</div>
				<div class="col-md-3" id="showbox">
<!-- 					系統時間 -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-9">
					<table style="margin: 0px auto">
						<tr>
							<td>
								<a href="../index.jsp" />
								<input type="button" value="回首頁" id="oIndex"
								name="oIndex">
								</a>
							</td>
							<td><input type="text" value="" id="oPeople" name="cusFlow">人</td>
							<td><input type="text" value="" id="oCall" name="callNo">號</td>
							<td><input type="reset" value="全部清除" id="oReset"
								name="oReset" onclick="deltable()"></td>
							<td><input type="button" value="下一步" id="oNext" name="oNext"></td>
						</tr>
					</table>
				</div>
			</div>

			<!-- 點餐部分 -->
			<div class="row">
				<!-- 左方清單 -->
				<div class="col-md-8">
					<table style="margin: 0px auto" width="100%" border="1"
						style="table-layout:fixed" id="tablelist">
						<tr>
							<th>項目</th>
							<th>價格</th>
							<th>數量</th>
							<th>小計</th>
							<th>備註</th>
							<th>功能</th>
						</tr>
						<tr id="oL1" style="display:none">
						
						</tr>
						<c:forEach var='orderVos1' items='${orderVos1}' varStatus="idx">
							<tr>	
								<td>
									${orderVos1.itemName}
									<input type="hidden" id="itemName${idx.index}" name="orderVos1[${idx.index}].itemName" value="${orderVos.itemName}">
								<td>
								    ${orderVos1.price}
								     <input type="hidden" id="price${idx.index}" name="orderVos1[${idx.index}].price" value="${orderVos.price}">
								</td>
								<td>
									${orderVos1.qty}
									 <input type="hidden" id="qty${idx.index}" name="orderVos1[${idx.index}].qty" value="${orderVos.qty}">
								</td>
								<td>
									${orderVos1.subTotal}
									<input type="hidden" id="subTotal${idx.index}" name="orderVos1[${idx.index}].subTotal" value="${orderVos.subTotal}">
									 <input type="hidden" id="category${idx.index}" name="orderVos1[${idx.index}].category" value="${orderVos.category}">
									 <input type="hidden" id="productNo${idx.index}" name="orderVos1[${idx.index}].productNo" value="${orderVos.productNo}">
							
								<td></td>
								<td>
								<input type="button" value="口味" id="opFlaver' " name="opFlaver">
								<input type="button" value="刪除" id="opDelete' " name="opDelete" onclick="delItem(this)">
								</td>
							</tr>
						</c:forEach>
						<tr>
							
						</tr>
						<tr>
							<th colspan="3" style="text-align: right">總金額：</th>
							<td colspan="3" id="oTotal" name="oTotal">0
							</td>
							<input type="hidden" id="hidoTotal" name="totalAmount" value=""/>
						</tr>
					</table>
				</div>
				<!-- 右方按鈕 -->

				<div class="col-md-4">
					<table style="margin: 0px auto" width="100%" border="1" id="cTable" name="cTable">
						<tr>
							<th colspan="4" style="text-align: center">點餐</th>
						</tr>
						<tr>
							<td><input type="button" value="飯類" onclick="riceList()"></td>
							<td><input type="button" value="麵類" onclick="noodleList()"></td>
							<td><input type="button" value="湯類" onclick="soupList()"></td>
							<td><input type="button" value="菜類" onclick="vegetableList()"></td>
							<td><input type="button" value="小菜類" onclick="sidedishList()"></td>
						</tr>
					</table>
					<div id="riceList" class=""
						style="border-color: #aaaaee; border-width: 3px; border-style: solid; padding: 5px">
						<c:forEach var='menu1' items='${menu}'>
							<input style="width: 100px" type="button" class="pt"
								value="${menu1.productName}">
						</c:forEach>
					</div>
					<div id="noodleList" class="hiddenList"
						style="border-color: #aaaaee; border-width: 3px; border-style: solid; padding: 5px">
						<c:forEach var='noodle' items='${noodle}'>
							<input style="width: 100px" type="button" class="pt"
								value="${noodle.productName}">
						</c:forEach>
					</div>
					<div id="soupList" class="hiddenList"
						style="border-color: #aaaaee; border-width: 3px; border-style: solid; padding: 5px">
						<c:forEach var='soup' items='${soup}'>
							<input style="width: 100px" type="button" class="pt"
								value="${soup.productName}">
						</c:forEach>
					</div>
					<div id="vegetableList" class="hiddenList"
						style="border-color: #aaaaee; border-width: 3px; border-style: solid; padding: 5px">
						<c:forEach var='vegetable' items='${vegetable}'>
							<input style="width: 100px" type="button" class="pt"
								value="${vegetable.productName}">
						</c:forEach>
					</div>
					<div id="sidedishList" class="hiddenList"
						style="border-color: #aaaaee; border-width: 3px; border-style: solid; padding: 5px">
						<c:forEach var='sidedish' items='${sidedish}'>
							<input style="width: 100px" type="button" class="pt"
								value="${sidedish.productName}">
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
<%-- 	</form> --%>
	</form:form>


	<!-- 數字鍵盤script -->
	<script src="${pageContext.request.contextPath}/css/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/css/mynumkb.js"></script>
	<script>
		$("#oPeople").mynumkb();
		$("#oCall").mynumkb();
	</script>
	
</body>
</html>

