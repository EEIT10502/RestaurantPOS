<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>營運目標查詢</title>
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
		$("#gMonth1").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd",
			changeYear : true,
			changeMonth : true
		});
	});
	//轉PDF帶值
	$(function() {
		$("#gExport").click(
				function() {
					$("#form1").attr("action",
							"/RestaurantPOS/report/goalReportGetPDF");
					$("#form1").submit();
				});
	})
</script>


<body>
	<div  class="clearfix">
	<jsp:include page="../headerTime.jsp" flush="true" />	
	<jsp:include page="../sideBar.jsp" flush="true" />
	</div>
	<jsp:include page="../report/reportSearchHead.jsp" flush="true" />
	<fieldset class="w3-container" style="margin-left: 160px">
	<form action="goalReportGet" method="post" id="form1">
		<!-- 報表版面 -->
		<div class="w3-container" style="margin-left: 160px">
			<div>
				<h2>營運目標查詢</h2>
			</div>
			<div>
				<h3>選擇欲查詢日期</h3>
				<input type="text" id="gMonth1" name="gMonth1" readonly>
				<p>

					<input type="submit" value="查詢" id="gSel" name="gSel">
			</div>
			<div>
				<h5>${gMonth1}</h5>
				<input type="button" value="匯出報表" id="gExport" name="gExport">
				<table class="table table-hover">
					<tr>
						<th>日期</th>
						<th>目標營業額</th>
						<th>累計營業額</th>
						<th>差額</th>
						<th>達成率</th>
					</tr>
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
				</table>
			</div>
		</div>
	</form>
	</fieldset>
	<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>