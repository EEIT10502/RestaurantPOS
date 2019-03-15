<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
</head>
<title>員工查詢</title>
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


<body>
	<jsp:include page="../sideBar.jsp" flush="true" />

	<form action="">
		<div class="w3-container" style="margin-left: 160px">
			<h2>員工出勤查詢</h2>

			<!-- 		搜尋列開始 -->
			<div>

				<h3>選擇欲查詢日期</h3>
				<input type="date" id="attendanceSDate1" name="attendanceSDate1">~
				<input type="date" id="attendanceSDate2" name="attendanceSDate2">
				<p>

					<select id="searchEmpType">
						<option>員工姓名</option>
						<option>員工編號</option>
					</select> <input type="text" id="empSearch" name="empSearch"> <input
						type="submit" value="查詢" id="searchBut" name="searchBut">
			</div>
	</form>
	<!-- 搜尋列結束 -->
	<!-- 匯出 -->
	<div>
		<input type="button" value="匯出檔案" id="dExport" name="dExport">
	</div>
	<!-- 		匯出與寄信結束 -->
	<!-- 商品列表開始 -->
	<div>
		<table border="1">
			<tr>
				<th>序號</th>
				<th>員工編號</th>
				<th>員工姓名</th>
				<th>日期</th>
				<th>狀態</th>
				<th>時間</th>
				<th>備註</th>
			</tr>
					<c:forEach var="Attendence" items="${Attendence}" varStatus="status">
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
		<!-- 	商品列表結束 -->
		<input type="button" value="上一頁" id="dBPage" name="dBPage"> <input
			type="button" value="下一頁" id="dNPage" name="dNPage">
	</div>

</body>
</html>
