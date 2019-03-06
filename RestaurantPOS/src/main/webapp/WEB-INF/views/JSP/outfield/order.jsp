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
<link rel="stylesheet" href="../../css/final.css">
<link rel="stylesheet" href="../../css/mynumkb.css">
<style>
* {
	padding: 0;
	margin: 0;
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
	// 選類別
	$(function() {
		$("#oc1").click(function() {
			$("#cTable").hide();
			$("#pTable").show();
		})
	})
	// 上一步回類別
	$(function() {
		$("#oBPage").click(function() {
			$("#pTable").hide();
			$('#cTable').show();
		})
	})
	// 炒飯點餐
	$(function() {
		$("#op1").click(function() {
			$("#opItem1").html($("#op1").val());
			$("#opPrice1").html($("#opHidden1").val());
			$("#opSubtotal1").html($("#opHidden1").val() * $("#opQty1").val());

		})
	})
	// 折扣修改
	$(function() {
		$("#opQMod1").click(function() {
			$("#opSubtotal1").html($("#opHidden1").val() * $("#opQty1").val());
		})
	})
	// 刪除單品
	$(function() {
		$("#opDelete1").click(function() {
			$("#ol1").remove();
		})
	})

	// 口味調整
	$(function() {
		$("#opFlaver1").click(function() {
			$("#fDiv").popover("toggle");
		})
	})
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
					<h5>系統時間：</h5>
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
						<tr id="ol1" name="ol1">
							<td id="opItem1" name="opItem1"></td>
							<td id="opPrice1" name="opPrice1"></td>
							<td><input type="number" value="1" min="1" max="10"
								id="opQty1" name="opQty1"> <input type="button"
								value="修改" id="opQMod1" name="opQMod1"></td>
							<td id="opSubtotal1" name="opSubtotal1"></td>
							<td id="" name=""></td>
							<td><input type="button" value="口味" id="opFlaver1"
								name="opFlaver1"> <input type="button" value="刪除"
								id="opDelete1" name="opDelete1"></td>
						</tr>
						<tr>
							<th colspan="3" style="text-align: right">總金額：</th>
							<td colspan="3" id="oTotal" name="oTotal"></td>
						</tr>
					</table>
				</div>

				<!-- 右方按鈕 -->
				<div class="col-md-4">
					<table style="margin: 0px auto" width="100%" border="1" id="cTable"
						name="cTable">
						<tr>
							<th colspan="3" style="text-align: center">點餐</th>
						</tr>
						<tr>
							<td><input type="button" value="飯類" onclick="location.href='order'"></td>
							<td><input type="button" value="麵類" onclick="location.href='noodle'"></td>
							<td><input type="button" value="湯類" onclick="location.href='soup'"></td>
						</tr>
						</table>
							<div style= "border-color:#aaaaee;border-width:3px;border-style:solid;padding:5px">
							<c:forEach var='menu1' items='${menu}'>
							
								<input style="width:100px" type="button" value="${menu1.productName}" >
							
							</c:forEach>
							
							</div>
						
							
					
					
					
				</div>
			</div>
		</div>
	</form>

	<!-- 數字鍵盤script -->
	<script src="../../css/jquery.min.js"></script>
	<script src="../../css/mynumkb.js"></script>
	<script>
		$("#oPeople").mynumkb();
		$("#oCall").mynumkb();
	</script>
</body>
</html>