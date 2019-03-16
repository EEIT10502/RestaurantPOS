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
	<style>
	* {
		padding: 0;
		margin: 0;
	}
	
	.hiddenList {
		display: none;
	}
	</style>
	<title>點餐</title>
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
	var itemNo = 0;
	
	$(function() {
		// 選類別
		$("#oc1").click(function() {
			$("#cTable").hide();
			$("#pTable").show();
		});
		
		// 上一步回類別
		$("#oBPage").click(function() {
			$("#pTable").hide();
			$('#cTable').show();
		});
		
		// 炒飯點餐
		$("#op1").click(function() {
			$("#opItem1").html($("#op1").val());
			$("#opPrice1").html($("#opHidden1").val());
			$("#opSubtotal1").html($("#opHidden1").val() * $("#opQty1").val());
		});
		
		// 折扣修改
		$("#opQMod1").click(function() {
			$("#opSubtotal1").html($("#opHidden1").val() * $("#opQty1").val());
		});
		
		// 刪除單品
		$("#opDelete1").click(function() {
			$("#ol1").remove();
		});
		
		// 口味調整
		$("#opFlaver1").click(function() {
			$("#fDiv").popover("toggle");
		});
		
		$(".pt").click(function() {			
			var v = $(this).val();
			var total=0;
			var total1=0;
			var total1 = parseInt($("#oTotal").html());
			
			
			$.ajax({
			    url: '/RestaurantPOS/order/getPrice',
			    type: 'post',
			    data: {"product" : v},
			    error: function(xhr) {
			      	alert('Ajax request 發生錯誤');
			    },
			    success: function(response) {
			        var vPrice = response.price;
			        var vCategory = response.cate;
			        var vProductNo = response.productNo;
				    var subTotal = vPrice * 1
				
			        var row = '';
					row += '<tr>';
					row += '	<td id="opItem' + itemNo + '" name="opItem' + itemNo + '">' + v + '</td>';					
					row += '	<td id="opPrice' + itemNo + '" name="opPrice' + itemNo + '">' + vPrice + '</td>';					
					row += '	<td>';
					row += '		<input type="number" value="1" min="1" max="10" id="opQty' + itemNo + '" name="orderVos[' + itemNo + '].qty">';
					row += '		<input type="button" value="修改" id="opQMod' + itemNo + '" name="opQMod' + itemNo + '" onclick="modifyQty('+itemNo+',' + vPrice + ');"></td>';
					row += '	</td>';
					row += '	<td id="opSubtotal' + itemNo + '" name="opSubtotal' + itemNo + '">' + subTotal + '</td>';					
					row += '	<td id="" name=""></td>';
					row += '	<td>';
					row += '		<input type="button" value="口味" id="opFlaver' + itemNo + '" name="opFlaver' + itemNo + '">';
					row += '		<input type="button" value="刪除" id="opDelete' + itemNo + '" name="opDelete' + itemNo + '" onclick="delItem(this);">';
					row += '	</td>';
					
					row += '	<input type="hidden" id="hidOpItem' + itemNo + '" name="orderVos[' + itemNo + '].itemName" value="' + v + '" />';
					row += '	<input type="hidden" id="hidOpPrice' + itemNo + '" name="orderVos[' + itemNo + '].price" value="' + vPrice + '" />';
					row += '	<input type="hidden" id="hidSubtotal' + itemNo + '" name="orderVos[' + itemNo + '].subTotal" value="' + subTotal + '" />';
					row += '	<input type="hidden" id="hidCategory' + itemNo + '" name="orderVos[' + itemNo + '].category" value="' + vCategory + '" />';
					row += '	<input type="hidden" id="hidProductNo' + itemNo + '" name="orderVos[' + itemNo + '].productNo" value="' + vProductNo + '" />';
					row += '</tr>';
				
					$('#oL1').after(row);
					var subTotal1 = parseInt($("#opSubtotal" + itemNo).html());
					total= total1 + subTotal1; //總金額加總
					$("#oTotal").html(total);
					$('#hidoTotal').attr("value",total);
					itemNo++;
				
			    }
			});
			
		});
			
		$('#oNext').click(function() {
			var people = $('#oPeople').val();
			var call = $('#oCall').val();
			if (!people) {
				alert('「請輸入人數」');
				return;
			}
			if(!call){
				alert('「請輸入叫號機號碼」');
				return;
			}
			$('#dataForm').submit();
		});
	});
	
	function deltable(){
		$("#oTotal").html("0");
		$("#tablelist  tr:not(:first):not(:last)").empty("");
	}
	
	function delItem(obj) {
		$(obj).closest('tr').remove();
	}
	
	function modifyQty(itemNo, price){
		var qty = parseInt($("#opQty"+ itemNo).val());//修改單品數量，小計連動。
		var subTotal = qty*price;
		$('#opSubtotal' + itemNo).html(subTotal);
		$('#hidSubtotal' + itemNo).val(subTotal);
		
		var y = parseInt($("#oTotal").html()); //修改單品數量，總金額連動。
		var totalAmount=0;
		totalAmount = y + (qty-1)*price;
		$("#oTotal").html(totalAmount);
		$('#hidoTotal').attr("value",totalAmount);
		
	}
	
	function riceList() {
		var x = document.getElementById("riceList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function soupList() {
		var x = document.getElementById("soupList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function noodleList() {
		var x = document.getElementById("noodleList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	function vegetableList() {
		var x = document.getElementById("vegetableList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function sidedishList() {
		var x = document.getElementById("sidedishList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function hiddenAllList() {
		var rice = document.getElementById("riceList");
		var noodle = document.getElementById("noodleList");
		var soup = document.getElementById("soupList");
		var vegetable = document.getElementById("vegetableList");
		var sidedish = document.getElementById("sidedishList");
		
		// 		if (allProduct.className.indexOf("hiddenList") == -1) {
		// 			allProduct.className += "hiddenList";
		// 		} 
		if (rice.className.indexOf("hiddenList") == -1) {
			rice.className += "hiddenList";
		}
		
		if (soup.className.indexOf("hiddenList") == -1) {
			soup.className += "hiddenList";
		}
		
		if (noodle.className.indexOf("hiddenList") == -1) {
			noodle.className += "hiddenList";
		}
		
		if (vegetable.className.indexOf("hiddenList") == -1) {
			vegetable.className += "hiddenList";
		}
		
		if (sidedish.className.indexOf("hiddenList") == -1) {
			sidedish.className += "hiddenList";
		}
	}
	
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
							<td><input type="button" value="回首頁" id="oIndex"
								name="oIndex"></td>
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

