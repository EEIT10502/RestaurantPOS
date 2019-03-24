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
<title>營運銷售分析(日報表)</title>
</head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
<script>
	// 結束日大於起始日判斷
	jQuery(document).ready(function($) {
		$("#dDate2").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd"
		}).bind("change", function() {
			var maxValue = $(this).val();
			maxValue = $.datepicker.parseDate("yy-mm-dd", maxValue);
			maxValue.setDate(maxValue.getDate());
			$("#dDate1").datepicker("option", "maxDate", maxValue)
			});
		$("#dDate1").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd"
		}).bind("change", function() {
			var minValue = $(this).val();
			minValue = $.datepicker.parseDate("yy-mm-dd", minValue);
			minValue.setDate(minValue.getDate());
			$("#dDate2").datepicker("option", "minDate", minValue);
		})
	});

	//轉檔方法
	$(function() {
		$("#dExport").click(
				function() {
					$("#form1").attr("action",
							"/RestaurantPOS/report/DailyReportGetExcel");
					$("#form1").submit();
				});
	})
	//轉查詢方法
	$(function() {
		$("#dSelect").click(function() {
			
			if($("#dDate2").val < $("#dDate1").val){
				alert("日期選擇錯誤");
				}
			
			$("#form1").attr("action", "/RestaurantPOS/report/dailyReportGet");
			$("#form1").submit();
		});
	})
</script>

<body>
	<div class="clearfix">
		<jsp:include page="../headerTime.jsp" flush="true" />
		<jsp:include page="../sideBar.jsp" flush="true" />
	</div>
	<jsp:include page="../report/reportSearchHead.jsp" flush="true" />
	<fieldset class="w3-container" style="margin-left: 160px">
		<!-- 報表版面 -->
		<form action="dailyReportGet" method="post" id="form1">
			<div class="w3-container" style="margin-left: 160px">
				<div>
					<h3>日報表</h3>
				</div>
				<div>
					<h5>選擇欲查詢日期</h5>
					<input type="text" id="dDate1" name="dDate1" value="${dDate1}"
						readonly> - <input type="text" id="dDate2" name="dDate2" value="${dDate2}"
						 readonly>
					<p>

						<input type="submit" value="查詢" id="dSelect" name="dSelect">
				</div>

				<div>
					<h5>選擇日期：${dDate1}至${dDate2}</h5>
					<!-- 顯示查詢年月日 -->
					<input type="button" value="匯出報表" id="dExport" name="dExport">

					<table class="table table-hover">
						<tr>
							<th>日期</th>
							<th>總單數</th>
							<th>來客數</th>
							<th>銷售金額</th>
							<th>短溢收</th>
							<th>實收金額</th>
						</tr>
						<c:forEach var="dTableO" items="${listDailyOrder}"
							varStatus="loop">
							<c:set var="totalList" value="${totalList + dTableO[1]}" />
							<c:set var="totalCusFlow" value="${totalCusFlow + dTableO[2]}" />
							<c:set var="totalMoney" value="${totalMoney + dTableO[3]}" />
							<c:set var="totalShort"
								value="${totalShort + listDailyCumu[loop.count-1].shortoverAmount}" />
							<c:set var="totalReceived"
								value="${totalReceived + listDailyCumu[loop.count-1].moneyReceived}" />
							<tr>
								<td>${dTableO[0]}</td>
								<td>${dTableO[1]}</td>
								<td>${dTableO[2]}</td>
								<td>${dTableO[3]}</td>
								<td>${listDailyCumu[loop.count-1].shortoverAmount}</td>
								<td>${listDailyCumu[loop.count-1].moneyReceived}</td>
							</tr>
						</c:forEach>
						<tr>
							<th>總計</th>
							<td>${totalList}</td>
							<td>${totalCusFlow}</td>
							<td>${totalMoney}</td>
							<td>${totalShort}</td>
							<td>${totalReceived}</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</fieldset>
	<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>