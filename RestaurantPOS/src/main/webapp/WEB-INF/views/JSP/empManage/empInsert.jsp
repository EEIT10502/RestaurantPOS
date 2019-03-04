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
<title>員工新增</title>
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
	<div class="w3-container" style=" margin-left: 160px">
		<h2>員工新增</h2>
		<!-- 		搜尋列結束 -->

<label for="empId">員工編號</label>
			<input type="text" id="empId" name="empId"><br>
<label for="empName">員工姓名</label>
			<input type="text" id="empName" name="empName"><br>
<label for="empSex">性別</label>
			<select id="empSex">
				<option>男性</option>
				<option>女性</option>
			</select><br>
<label for="empPosition">職位</label>
			<select id="empPosition">
				<option>服務生</option>
				<option>廚師</option>
				<option>經理</option>
				<option>其他</option>
			</select><br>
<label for="empTel">電話</label>
			<input type="text" id="empTel" name="empTel"><br>
<label for="empAddr">地址</label>
			<input type="text" id="empAddr" name="empAddr"><br>
<label for="empStatus">在職狀況</label>
			<select id="empStatus">
				<option>在職</option>
				<option>離職</option>
				<option>留職停薪</option>
				<option>其他</option>
			</select><br>
<label for="empMark">備註</label>
			<input type="text" id="empMark" name="empMark"><br>


</form>

	<input type="submit" value="確定新增" id="empInsert" name="empInsert">
			<input type="reset" value="全部清除" id="resetEmp" name="resetEmp">
</div>

</body>
</html>
