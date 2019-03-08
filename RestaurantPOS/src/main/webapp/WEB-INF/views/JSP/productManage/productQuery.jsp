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
<title>sideBar</title>
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


<body>
	<jsp:include page="../sideBar.jsp" flush="true" /> 
<form action="" method='POST'>
	<div class="w3-container" style="margin-left: 160px">
		<h2>商品查詢/修改</h2>
		<div>
			<label for="searchType"></label>
			<select id="searchType">
				<option>商品名稱</option>
				<option>商品編號</option>
				<option>類別</option>
			</select>
			<label for=""></label>
			<select id="">
				<option>已上架</option>
				<option>未上架</option>
			</select>
			<input type="text" id="goodsSearch" name="goodsSearch">
			<input type="submit" value="查詢" id="dSel" name="dSel">
		</div>
		<div>
		<input type="button" value="匯出檔案" id="dExport" name="dExport">
		</div>
		<table border="1">
		<tr>
			<th>序號</th>
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>類別</th>
			<th>價格</th>
			<th>狀態</th>
			
		</tr>
<%--		<c:forEach var="gTable" items="">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
		</c:forEach> --%>
	</table>
	
	<input type="button" value="上一頁" id="dBPage" name="dBPage">
	<input type="button" value="下一頁" id="dNPage" name="dNPage">
</div>
</form>
	</div>
</form>


</body>
</html>
