<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<title>營運銷售分析(日報表)</title>
</head>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>


<body>
<jsp:include page="../sideBar.jsp" flush="true" /> 
<!-- 報表版面 -->
<div class="w3-container" style="margin-left:160px">
<form action="dailyReportGetDate" method="post">

<div>
	<h2>營運銷售分析(日報表)</h2>
</div>
<div>
	<h3>選擇欲查詢日期</h3>
	<input type="date" id="dDate1" name="dDate1">~
	<input type="date" id="dDate2" name="dDate2"><p>
	
	<input type="submit" value="查詢" id="dSel" name="dSel">
</div>
</form>
<div>
	<h4>xxxx年xx月xx日</h4>  <!-- 顯示查詢年月日 -->
	<input type="button" value="匯出報表" id="dExport" name="dExport">

	<table border="1">
		<tr>
			<th>日期</th>
			<th>總單數</th>
			<th>來客數</th>
			<th>原價</th>
			<th>折扣</th>
			<th>銷售金額</th>
			<th>短溢收</th>
			<th>實收金額</th>
		</tr>
<%-- 		<c:forEach var="dTable" items="dailyList"> --%>
<!-- 				<tr> -->
<%-- 					<td>${dTable.orderTime}</td> --%>
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 				</tr> -->
<%-- 		</c:forEach> --%>
		<tr>
			<th>總計</th>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	
	<input type="button" value="上一頁" id="dBPage" name="dBPage">
	<input type="button" value="上一頁" id="dNPage" name="dNPage">
</div>
</div>
</body>
</html>