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
<title>類別銷售分析</title>
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
		$("#csDate2").datepicker({
			maxDate : new Date,
			dateFormat : "yy-mm-dd",
			changeYear : true,
			changeMonth : true
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
	});

	//轉PDF方法
	$(function() {
		$("#csExport").click(
				function() {
					$("#form1").attr("action",
							"/RestaurantPOS/report/empManage/attendanceGetExcel");
					$("#form1").submit();
				});
	})
	//轉查詢方法
	$(function() {
		$("#csSelect").click(
				function() {
					$("#form1").attr("action",
							"/RestaurantPOS/empManage/attendanceGet");
					$("#form1").submit();
				});
	})
</script>


<body>
	<div class="clearfix">
		<jsp:include page="../headerTime.jsp" flush="true" />
		<jsp:include page="../sideBar.jsp" flush="true" />
		<jsp:include page="../scheduleManage/statusSearchHead.jsp"
			flush="true" />
	</div>
	<fieldset class="w3-container" style="margin-left: 160px">
		<form action="attendanceGet" method="post" id="form1">
			<!-- 報表版面 -->
			<div class="w3-container" style="margin-left: 160px">
				<div>
					<div>
						<h2>出勤查詢</h2>
					</div>
					<div>
						<h3>選擇欲查詢日期</h3>
						<input type="text" id="csDate1" name="csDate1" value="${csDate1}"
							readonly>~ <input type="text" id="csDate2" name="csDate2"
							value="${csDate2}" readonly>
						<p>
					</div>

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
					</select> <input type="submit" value="查詢" id="csSelect" name="csSelect">
				</div>

				<div>
					<h5>選擇日期：${csDate1}至${csDate2}</h5>
					<%-- 				<a type="button" href="<c:url value='/empManage/attendanceGetExcel?csDate1=${csDate1}&csDate2=${csDate2}&csSelOpt=${csSelOpt}'/>"> TO PDF</a> --%>
					<table class="table table-hover">
						<tr>
							<th>序號</th>
							<th>員工編號</th>
							<th>員工姓名</th>
							<th>日期</th>
							<th>狀態</th>
							<th>時間</th>
							<th>備註</th>
						</tr>
						<c:forEach var="Attendence" items="${Attendence}"
							varStatus="status">
							<tr>
								<td>${Attendence.attendenceId}</td>
								<td>${Attendence.empNo}</td>
								<td>${Attendence.empName}</td>
								<td>${Attendence.date}</td>
								<td>${Attendence.checkStatus}</td>
								<td>${Attendence.clockTime}</td>
								<td></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<a href='attendence.xls' type="button" value="匯出報表" id="csExport" name="csExport">匯出EXCEL報表</a>
			</div>
		</form>
	</fieldset>
	<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>