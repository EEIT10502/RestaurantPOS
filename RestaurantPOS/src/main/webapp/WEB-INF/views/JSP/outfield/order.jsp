<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			$.ajax({
			    url: '/RestaurantPOS/order/getPrice',
			    type: 'post',
			    data: {"product" : v},
			    error: function(xhr) {
			      	alert('Ajax request 發生錯誤');
			    },
			    success: function(response) {
			        var vPrice = response;
			        var row = '';
					row += '<tr>';
					row += '	<td id="opItem' + itemNo + '" name="opItem' + itemNo + '">' + v + '</td>';
					row += '	<td id="opPrice' + itemNo + '" name="opPrice' + itemNo + '">' + vPrice + '</td>';
					row += '	<td>';
					row += '		<input type="number" value="1" min="1" max="10" id="opQty' + itemNo + '" name="opQty' + itemNo + '">';
					row += '		<input type="button" value="修改" id="opQMod' + itemNo + '" name="opQMod' + itemNo + '"></td>';
					row += '	</td>';
					row += '	<td id="opSubtotal' + itemNo + '" name="opSubtotal' + itemNo + '"></td>';
					row += '	<td id="" name=""></td>';
					row += '	<td>';
					row += '		<input type="button" value="口味" id="opFlaver' + itemNo + '" name="opFlaver' + itemNo + '">';
					row += '		<input type="button" value="刪除" id="opDelete' + itemNo + '" name="opDelete' + itemNo + '" onclick="delItem(this);">';
					row += '	</td>';
					row += '</tr>';
					
					$('#oL1').after(row);
					itemNo++;
			    }
			});
			
			
		});
	});

	function delItem(obj) {
		$(obj).closest('tr').remove();
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

	function dumpList() {
		var x = document.getElementById("dumpList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function hiddenAllList() {
		var rice = document.getElementById("riceList");
		var noodle = document.getElementById("noodleList");
		var soup = document.getElementById("soupList");
		var dump = document.getElementById("dumpList");
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
		
		if (dump.className.indexOf("hiddenList") == -1) {
			dump.className += "hiddenList";
		}
	}
	
</script>

<body>
	<form>
		<div class="container-fluid">
			<!-- 標頭 -->
			<div class="row">
				<div class="col-md-9">
					<h4 style="text-align: center">點餐頁面</h4>
				</div>
				<div class="col-md-3">
					<h5>系統時間:</h5>
				</div>
			</div>
			<div class="row">
				<div class="col-md-9">
					<table style="margin: 0px auto">
						<tr>
							<td><input type="button" value="回首頁" id="oIndex"
								name="oIndex"></td>
							<td><input type="text" value="" id="oPeople" name="oPeople">人</td>
							<td><input type="text" value="" id="oCall" name="oCall">號</td>
							<td><input type="reset" value="全部清除" id="oReset"
								name="oReset"></td>
							<td><input type="submit" value="下一步" id="oNext" name="oNext"></td>
						</tr>
					</table>
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
							<th>功能</th>
						</tr>
						<tr id="oL1" style="display:none">
						
						</tr>
						<tr>

						</tr>
						<tr>
							<th colspan="3" style="text-align: right">總金額：</th>
							<td colspan="3" id="oTotal" name="oTotal"></td>
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
							<td><input type="button" value="餃類" onclick="dumpList()"></td>
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
					<div id="dumpList" class="hiddenList"
						style="border-color: #aaaaee; border-width: 3px; border-style: solid; padding: 5px">
						<c:forEach var='dump' items='${dump}'>
							<input style="width: 100px" type="button" class="pt"
								value="${dump.productName}">
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</form>


	<!-- 數字鍵盤script -->
	<script src="${pageContext.request.contextPath}/css/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/css/mynumkb.js"></script>
	<script>
		$("#oPeople").mynumkb();
		$("#oCall").mynumkb();
	</script>
</body>
</html>