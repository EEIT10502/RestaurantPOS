<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<title>單品銷售分析</title>
</head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- <script src="http://code.jquery.com/jquery-3.3.1.min.js" -->
<!-- 	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" -->
<!-- 	crossorigin="anonymous"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
<script>
	//結束日大於起始日判斷
	jQuery(document).ready(function($) {
		$("#pDate2").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd"
		});
		$("#pDate1").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd"
		}).bind("change", function() {
			var minValue = $(this).val();
			minValue = $.datepicker.parseDate("yy-mm-dd", minValue);
			minValue.setDate(minValue.getDate());
			$("#pDate2").datepicker("option", "minDate", minValue);
		})
	});

	//單品選項	
	function showCodeSubCate() {
		var target = $("#pcSelOpt option:selected").text();
		$.ajax({
			url : "productReportPro",
			data : {
				"pcSelOpt" : target
			},
			async : false, //請求是否非同步，預設為非同步，這也是ajax重要特性
			type : "post", //請求方式
			success : function(result) {
				$("#pSelOpt").empty();
				var str = "";
				for (var x = 0; x < result.length; x++) {
					// 						alert(result[x].productName);
					str = '<option value="' + result[x].productName +'">'
							+ result[x].productName + '</option>';
					$("#pSelOpt").append(str);
				}

			}
		});
	}
</script>
<body>
	<jsp:include page="../sideBar.jsp" flush="true" />
	<form action="productReportGet" method="post">
		<!-- 報表版面 -->
		<div class="w3-container" style="margin-left: 160px">
			<div>
				<h2>單品銷售分析</h2>
			</div>
			<div>
				<h3>選擇欲查詢日期</h3>
				<input type="text" id="pDate1" name="pDate1">- <input
					type="text" id="pDate2" name="pDate2">
				<p>

					<!-- 類別下拉選單 -->
					<select id="pcSelOpt" name="pcSelOpt" onchange="showCodeSubCate()">
						<option>--請選擇--</option>
						<c:forEach var="pcSel" items="${listMenuCate}">
							<option>${pcSel}</option>
						</c:forEach>
					</select>
					<!-- 單品下拉選單 -->
					<select id="pSelOpt" name="pSelOpt">
						<option value="">--請選擇--</option>
					</select> <input type="submit" value="查詢" id="pSel" name="pSel">
			</div>

			<div>
				<h5>選擇日期：${pDate1}至${pDate2}</h5>
				<input type="button" value="匯出報表" id="pExport" name="pExport">

				<table border="1">
					<tr>
						<th>日期</th>
						<th>類別名稱</th>
						<th>單品名稱</th>
						<th>數量</th>
						<th>銷售金額</th>
					</tr>
					<c:forEach var="pTable" items="${listProuct}">
						<c:set var="totalQty" value="${totalQty + pTable[1]}" />
						<c:set var="totalPrice" value="${totalPrice + pTable[2]}" />
						<tr>
							<td>${pTable[0]}</td>
							<td>${pcSelOpt}</td>
							<td>${pSelOpt}</td>
							<td>${pTable[1]}</td>
							<td>${pTable[2]}</td>
						</tr>
					</c:forEach>
					<tr>
						<th>總計</th>
						<td>${pcSelOpt}</td>
						<td>${pSelOpt}</td>
						<td>${totalQty}</td>
						<td>${totalPrice}</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>