<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>營運目標查詢</title>
</head>
<script>
	jQuery(document).ready(function($) {
		//DaePicker		
		$("#gMonth1").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd",
			changeYear : true,
			changeMonth : true
		});
		//DataTable
		$('#emps_dept').DataTable();
	});
	//轉檔方法
	$(function() {
		$("#gExport").click(
				function() {
					if ($("#gMonth1").val() != "") {
						$("#form1").attr("action",
								"/RestaurantPOS/report/goalReportGetExcel");
						$("#form1").submit();
					} else {
						alert("請選擇日期");
					}
				});
	})
	//轉查詢方法
	$(function() {
		$("#gSelect").click(
				function() {
					if ($("#gMonth1").val() != "") {
						$("#form1").attr("action",
								"/RestaurantPOS/report/goalReportGet");
						$("#form1").submit();
					} else {
						alert("請選擇日期");
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
		<form action="goalReportGet" method="post" id="form1">
			<!-- 報表版面 -->
			<div class="w3-container" style="margin-left: 160px">
				<div>
					<h3>營運目標查詢</h3>
				</div>
				<div>
					<h5>選擇欲查詢日期</h5>
					<input type="text" id="gMonth1" name="gMonth1" readonly
						value="${gMonth1}">
					<p>

						<input type="button" value="查詢" id="gSelect" name="gSelect">
				</div>
				<div>
					<h5>${gMonth1}</h5>
					<input type="button" value="匯出報表" id="gExport" name="gExport">
					<table id="emps_dept" class="table table-hover">
						<thead>
							<tr>
								<th>日期</th>
								<th>目標營業額</th>
								<th>累計營業額</th>
								<th>差額</th>
								<th>達成率</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="gTable" items="${listgoalCum}" varStatus="loop">
								<tr>
									<td>${gTable.date}</td>
									<td>${listgoalturn[loop.count-1].targetTurnover}</td>
									<td>${gTable.cumulativeTurnover}</td>
									<td>${listgoalturn[loop.count-1].targetTurnover - gTable.cumulativeTurnover}</td>
									<td><fmt:formatNumber type="number"
											value="${gTable.cumulativeTurnover / listgoalturn[loop.count-1].targetTurnover * 100}"
											maxFractionDigits="2" />%</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</fieldset>
	<jsp:include page="../footer.jsp" flush="true" />

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>
</body>
</html>