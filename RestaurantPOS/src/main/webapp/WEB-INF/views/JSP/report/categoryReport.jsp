<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>類別銷售分析</title>
</head>
<script>
	jQuery(document).ready(function($) {
		// 結束日大於起始日判斷
		$("#csDate2").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd",
			changeYear : true,
			changeMonth : true
		}).bind("change", function() {
			var maxValue = $(this).val();
			maxValue = $.datepicker.parseDate("yy-mm-dd", maxValue);
			maxValue.setDate(maxValue.getDate());
			$("#csDate1").datepicker("option", "maxDate", maxValue);
		});
		$("#csDate1").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd",
			changeYear : true,
			changeMonth : true
		}).bind("change", function() {
			var minValue = $(this).val();
			minValue = $.datepicker.parseDate("yy-mm-dd", minValue);
			minValue.setDate(minValue.getDate());
			$("#csDate2").datepicker("option", "minDate", minValue);
		})
		//DataTable
		$('#emps_dept').DataTable();
	});

	//轉檔方法
	$(function() {
		$("#csExport")
				.click(
						function() {
							if ($("#csDate1").val() != ""
									&& $("#csDate2").val() != ""
									&& $("#csSelOpt").val() != "--請選擇--") {
								$("#form1")
										.attr("action",
												"/RestaurantPOS/report/categoryReportGetExcel");
								$("#form1").submit();
							} else {
								alert("請選擇日期及類別");
							}
						});
	})
	//轉查詢方法
	$(function() {
		$("#csSelect").click(
				function() {
					if ($("#csDate1").val() != "" && $("#csDate2").val() != ""
							&& $("#csSelOpt").val() != "--請選擇--") {
						$("#form1").attr("action",
								"/RestaurantPOS/report/categoryReportGet");
						$("#form1").submit();
					} else {
						alert("請選擇日期及類別");
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
		<form action="categoryReportGet" method="post" id="form1">
			<!-- 報表版面 -->
			<div class="w3-container" style="margin-left: 160px">
				<div>
					<h3>類別銷售分析</h3>
				</div>
				<div>
					<h5>選擇欲查詢日期</h5>
					<input type="text" id="csDate1" name="csDate1" value="${csDate1}"
						readonly> - <input type="text" id="csDate2" name="csDate2"
						value="${csDate2}" readonly>
					<p>

						<!-- 類別下拉選單 -->
						<select id="csSelOpt" name="csSelOpt">
							<option>--請選擇--</option>
							<c:forEach var="csSel" items="${listMenuCate}">
								<c:if test="${csSelOpt == csSel}">
									<option selected="selected"><c:out value="${csSel}" /></option>
								</c:if>
								<c:if test="${csSelOpt!=csSel}">
									<option><c:out value="${csSel}" /></option>
								</c:if>
							</c:forEach>
						</select> <input type="button" value="查詢" id="csSelect" name="csSelect">
				</div>

				<div>
					<h5>選擇日期：${csDate1}至${csDate2}</h5>
					<input type="button" value="匯出報表" id="csExport" name="csExport">
					<%-- 				<a type="button" href="<c:url value='/report/categoryReportGetExcel?csDate1=${csDate1}&csDate2=${csDate2}&csSelOpt=${csSelOpt}'/>"> TO PDF</a> --%>
					<table id="emps_dept" class="table table-hover">
						<thead>
							<tr>
								<th>日期</th>
								<th>類別名稱</th>
								<th>數量</th>
								<th>銷售金額</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pTable" items="${listCatee}">
								<c:set var="totalQty" value="${totalQty + pTable[1]}" />
								<c:set var="totalPrice" value="${totalPrice + pTable[2]}" />
								<tr>
									<td>${pTable[0]}</td>
									<td>${csSelOpt}</td>
									<td>${pTable[1]}</td>
									<td>${pTable[2]}</td>
								</tr>
							</c:forEach>
							<tr>
								<th>總計</th>
								<td>${csSelOpt}</td>
								<td>${totalQty}</td>
								<td>${totalPrice}</td>
							</tr>
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