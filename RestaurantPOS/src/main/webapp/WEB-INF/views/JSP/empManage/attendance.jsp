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
<title>出勤查詢</title>
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
		$("#aDate2").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd"
		}).bind("change", function() {
			var maxValue = $(this).val();
			maxValue = $.datepicker.parseDate("yy-mm-dd", maxValue);
			maxValue.setDate(maxValue.getDate());
			$("#aDate1").datepicker("option", "maxDate", maxValue);
		});
		$("#aDate1").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd"
		}).bind("change", function() {
			var minValue = $(this).val();
			minValue = $.datepicker.parseDate("yy-mm-dd", minValue);
			minValue.setDate(minValue.getDate());
			$("#aDate2").datepicker("option", "minDate", minValue);
		})
	});

	//轉檔方法
	$(function() {
		$("#dExport").click(
				function() {
					if ($("#aDate1").val() != "" && $("#aDate2").val() != "") {
						$("#form1").attr("action",
								"/RestaurantPOS/report/DailyReportGetExcel");
						$("#form1").submit();
					} else {
						alert("請選擇日期");
					}
				});
	})
	//轉查詢方法 
	$(function() {
		$("#dSelect").click(
				function() {
					if ($("#aDate1").val() != "" && $("#aDate2").val() != "") {
						$("#form1").submit();
					} else {
						alert("請輸入日期");
					}
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
		<form action="${pageContext.request.contextPath}/empManage/attendance/update" method="POST" id="form1">
			<div class="w3-container" style="margin-left: 160px">
				<div>
					<h3>出勤表</h3>
				</div>
				<div>
					<h5>選擇欲查詢日期</h5>
					<input type="text" id="aDate1" name="aDate1" value="${aDate1}"
						readonly> - <input type="text" id="aDate2" name="aDate2"
						value="${aDate2}" readonly>
					<p>

						<input type="button" value="查詢" id="dSelect" name="dSelect">
				</div>

				<div>
					<h5>選擇日期：${aDate1}至${aDate2}</h5>
					<!-- 顯示查詢年月日 -->
					<input type="button" value="匯出報表" id="dExport" name="dExport">

					<table class="table table-hover">
						<tr>
							<th>員工編號</th>
							<th>員工姓名</th>
							<th>日期</th>
							<th>狀態</th>
							<th>時間</th>
							<th>備註</th>
						</tr>
						<c:forEach var="dTableO" items="${listDailyAtt}"
							varStatus="loop">
<%-- 							<c:set var="totalList" value="${totalList + dTableO[1]}" /> --%>
<%-- 							<c:set var="totalCusFlow" value="${totalCusFlow + dTableO[2]}" /> --%>
<%-- 							<c:set var="totalMoney" value="${totalMoney + dTableO[3]}" /> --%>
<%-- 							<c:set var="totalShort" --%>
<%-- 								value="${totalShort + listDailyCumu[loop.count-1].shortoverAmount}" /> --%>
<%-- 							<c:set var="totalReceived" --%>
<%-- 								value="${totalReceived + listDailyCumu[loop.count-1].moneyReceived}" /> --%>
							<tr>
								<td>${dTableO.attendenceId}</td>
								<td>${dTableO.empName}</td>
								<td>${dTableO.date}</td>
								<td>${dTableO.checkStatus}</td>
								<td>${dTableO.clockTime}</td>
							</tr>
						</c:forEach>
						<tr>
<!-- 							<th>總計</th> -->
<%-- 							<td>${totalList}</td> --%>
<%-- 							<td>${totalCusFlow}</td> --%>
<%-- 							<td>${totalMoney}</td> --%>
<%-- 							<td>${totalShort}</td> --%>
<%-- 							<td>${totalReceived}</td> --%>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</fieldset>
	<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>